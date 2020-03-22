package org.nanotek.configuration.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.ReleaseBean;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.Language;
import org.nanotek.beans.entity.Release;
import org.nanotek.beans.entity.ReleaseBarCode;
import org.nanotek.beans.entity.ReleaseComment;
import org.nanotek.beans.entity.ReleaseGroup;
import org.nanotek.beans.entity.ReleasePackaging;
import org.nanotek.beans.entity.ReleaseStatus;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.LanguageRepository;
import org.nanotek.repository.jpa.ReleaseBarCodeRepository;
import org.nanotek.repository.jpa.ReleaseCommentRepository;
import org.nanotek.repository.jpa.ReleaseGroupRepository;
import org.nanotek.repository.jpa.ReleasePackagingRepository;
import org.nanotek.repository.jpa.ReleaseStatusRepository;
import org.nanotek.service.CsvMessageHandler;
import org.nanotek.service.jpa.ArtistCreditJpaService;
import org.nanotek.service.jpa.ReleaseJpaService;
import org.nanotek.service.parser.BaseMapParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.bean.CsvToBean;

@Configuration
@EnableIntegration
@EnableConfigurationProperties
@IntegrationComponentScan(basePackageClasses = {ReleaseIntegrationConfiguration.class})
public class ReleaseIntegrationConfiguration {

	private final Logger logger = LoggerFactory.getLogger(ReleaseIntegrationConfiguration.class);

	@Value("${server.port}")
	private String serverPort;
	
	@Bean
	@Qualifier(value="releaseChannel")
	MessageChannel releaseChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releaseReplyChannel")
	MessageChannel releaseReplyChannel() {
		return new DirectChannel();
	}

	
	@Bean(name = "releaseTaskExecutor")
	@Qualifier(value = "releaseTaskExecutor")
	public ThreadPoolTaskExecutor releaseTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(2500000);
		executor.setThreadNamePrefix("releaseTaskExecutor");
		executor.initialize();
		return executor;
	}
	
	
	@Bean
	@Qualifier(value="releaseIntegrationStartChannel")
	MessageChannel releaseIntegrationStartChannel(
			@Autowired @Qualifier("releaseTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}
	
	@Bean
	RequestMapping initRelease() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initRelease");
		return mapping;
	}
	
	@Bean
	@Qualifier("releaseGate")
	HttpRequestHandlingMessagingGateway releaseGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initRelease());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(releaseChannel());
		gateway.setReplyChannel(releaseReplyChannel());
		return gateway;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "releasebean")
	@Qualifier(value="releaseMapStrategy")
	BaseMapColumnStrategy<ReleaseBean> releaseMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="releaseCsvToBean")
	CsvToBean<ReleaseBean> releaseCsvToBean() {
		return new CsvToBean<>();
	}
	
	@Bean
	@Qualifier(value="releaseParser")
	BaseMapParser<ReleaseBean> releaseParser() { 
		return new BaseMapParser<>(releaseMapStrategy());
	}
	
	
	@Service
	class ReleaseProcessor extends CsvBaseProcessor<ReleaseBean, BaseMapParser<ReleaseBean>>{

		public ReleaseProcessor
		(@Autowired @Qualifier("releaseParser") BaseMapParser<ReleaseBean> parser,
				@Autowired @Qualifier("releaseCsvToBean") CsvToBean<ReleaseBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}
	
	@Bean
	@Qualifier(value="releaseMessageHander")
	CsvMessageHandler releaseMessageHander
	(@Autowired @Qualifier("releaseIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("releaseReplyChannel") MessageChannel replyChannel,
			@Autowired ReleaseProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}
	
	@Bean
	IntegrationFlow releaseFlowRequestHttp
	(@Autowired @Qualifier("releaseMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("releaseChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class ReleaseTransformer implements GenericTransformer<ReleaseBean , ReleaseHolder>{

		@Autowired
		ArtistCreditJpaService creditService;
		
		@Autowired
		ReleaseStatusRepository statusRep; 
		
		@Autowired
		ReleaseGroupRepository groupRep;
		
		@Autowired
		ReleasePackagingRepository packRep;
		
		@Autowired
		LanguageRepository langRep; 
		
		@Override
		public ReleaseHolder transform(ReleaseBean source) {
			
			Release release = new Release(source.getId() , source.getGid() , source.getName());
			
			Optional<ReleaseStatus> optStatus = Base.NULL_VALUE(ReleaseStatus.class);
			
			Optional<ReleasePackaging> optPack = Base.NULL_VALUE(ReleasePackaging.class);
			
			Optional<ReleaseGroup> optGroup = Base.NULL_VALUE(ReleaseGroup.class);
			
			Optional<Language> optLanguage = Base.NULL_VALUE(Language.class);
			
			Optional<ArtistCredit> optCredit = Base.NULL_VALUE(ArtistCredit.class);
			
			Optional<ReleaseBarCode> optBarCode = Base.NULL_VALUE(ReleaseBarCode.class);
			
			Optional<ReleaseComment> optComment = Base.NULL_VALUE(ReleaseComment.class);
			
			if (source.getStatus() !=null) {
				optStatus = statusRep.findByReleaseStatusId(source.getStatus());
			}
			
			if (source.getPackaging() !=null) { 
				optPack = packRep.findByReleasePackagingId(source.getPackaging());
			}
			
			if(source.getReleaseGroup() !=null) { 
				optGroup = groupRep.findByReleaseGroupId(source.getReleaseGroup());
			}
			
			if (source.getLanguage() !=null) { 
				optLanguage  = langRep.findByLanguageId(source.getLanguage());
			}
			
			if (source.getArtistCreditId() !=null){ 
				optCredit = creditService.findByArtistCreditId(source.getArtistCreditId());
			}
			
			if (notEmpty(source.getBarcode())){ 
				optBarCode = Optional.of(new ReleaseBarCode(source.getBarcode()));
			}
			
			if (notEmpty(source.getComment())){ 
				optComment = Optional.of(new ReleaseComment(source.getComment()));
			}
			
			return new ReleaseHolder(release,optStatus,optPack,optGroup,optLanguage,optCredit,optBarCode, optComment);
		}

		private boolean notEmpty(String str) {
			return str !=null && !"".contentEquals(str.trim());
		} 
	}
	
	class ReleaseHolder{ 
		
		Release release;
		
		Optional<ReleaseStatus> optStatus;
		
		Optional<ReleasePackaging> optPack;
		
		Optional<ReleaseGroup> optGroup;
		
		Optional<Language> optLanguage;
		
		Optional<ArtistCredit> optCredit;
		
		Optional<ReleaseBarCode> optBarCode;
		
		Optional<ReleaseComment> optComment;

		public ReleaseHolder(Release release, 
								Optional<ReleaseStatus> optStatus, 
								Optional<ReleasePackaging> optPack,
								Optional<ReleaseGroup> optGroup, 
								Optional<Language> optLanguage, 
								Optional<ArtistCredit> optCredit,
								Optional<ReleaseBarCode> optBarCode,
								Optional<ReleaseComment> optComment) {
			super();
			this.release = release;
			this.optStatus = optStatus;
			this.optPack = optPack;
			this.optGroup = optGroup;
			this.optLanguage = optLanguage;
			this.optCredit = optCredit;
			this.optBarCode = optBarCode;
			this.optComment = optComment;
		}

		public Optional<Language> getOptLanguage() {
			return optLanguage;
		}

		public void setOptLanguage(Optional<Language> optLanguage) {
			this.optLanguage = optLanguage;
		}

		public Release getRelease() {
			return release;
		}

		public Optional<ReleaseStatus> getOptStatus() {
			return optStatus;
		}

		public Optional<ReleasePackaging> getOptPack() {
			return optPack;
		}

		public Optional<ReleaseGroup> getOptGroup() {
			return optGroup;
		}

		public Optional<ArtistCredit> getOptCredit() {
			return optCredit;
		}

		public Optional<ReleaseBarCode> getOptBarCode() {
			return optBarCode;
		}

		public Optional<ReleaseComment> getOptComment() {
			return optComment;
		}
		
	}
	
	@MessageEndpoint
	class ReleaseHandler implements MessageHandler{ 

		@Autowired
		ReleaseJpaService service;
		
		@Autowired
		ReleaseBarCodeRepository barCodeRepo;
		
		@Autowired
		ReleaseCommentRepository commentRepo;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			
			ReleaseHolder holder = (ReleaseHolder) message.getPayload();
			
			Release release = holder.getRelease();
			
			holder.getOptGroup().ifPresent(g -> release.setReleaseGroup(g));
			
			holder.getOptCredit().ifPresent(c -> release.setArtistCredit(c));
			
			holder.getOptStatus().ifPresent(s -> release.setStatus(s));
			
			holder.getOptLanguage().ifPresent(l -> release.setLanguage(l));
			
			service.save(release);
			
			Optional<ReleaseBarCode> optBarCode = holder.getOptBarCode();
			
			optBarCode.ifPresent(b -> {
				b.setRelease(release);
				barCodeRepo.save(b);
			});
			
			Optional<ReleaseComment> optComment = holder.getOptComment();
			
			optComment.ifPresent(rc -> {
				rc.setRelease(release);
				commentRepo.save(rc);
			});
		}
	}
	
	@Bean IntegrationFlow processReleaseRequest
	(@Autowired ReleaseHandler handler , 
			@Autowired @Qualifier("releaseIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired ReleaseTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
}
