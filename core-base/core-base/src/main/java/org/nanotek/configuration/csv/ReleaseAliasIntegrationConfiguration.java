package org.nanotek.configuration.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.ReleaseAliasBean;
import org.nanotek.beans.entity.Release;
import org.nanotek.beans.entity.ReleaseAlias;
import org.nanotek.beans.entity.ReleaseAliasBeginDate;
import org.nanotek.beans.entity.ReleaseAliasEndDate;
import org.nanotek.beans.entity.ReleaseAliasLocale;
import org.nanotek.beans.entity.ReleaseAliasSortName;
import org.nanotek.beans.entity.ReleaseAliasType;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.ReleaseAliasBeginDateRepository;
import org.nanotek.repository.jpa.ReleaseAliasEndDateRepository;
import org.nanotek.repository.jpa.ReleaseAliasLocaleRepository;
import org.nanotek.repository.jpa.ReleaseAliasSortNameRepository;
import org.nanotek.repository.jpa.ReleaseAliasTypeRepository;
import org.nanotek.service.CsvMessageHandler;
import org.nanotek.service.jpa.ReleaseAliasJpaService;
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
@IntegrationComponentScan(basePackageClasses = {ReleaseAliasIntegrationConfiguration.class})
public class ReleaseAliasIntegrationConfiguration {

	private final Logger logger = LoggerFactory.getLogger(ReleaseAliasIntegrationConfiguration.class);

	@Value("${server.port}")
	private String serverPort;


	@Bean
	@Qualifier(value="releaseAliasChannel")
	MessageChannel releaseAliasChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releaseAliasReplyChannel")
	MessageChannel releaseAliasReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releaseAliasIntegrationStartChannel")
	MessageChannel releaseAliasIntegrationStartChannel() {
		return new ExecutorChannel(releaseAliasTaskExecutor());
	}

	@Bean(name = "releaseAliasTaskExecutor")
	@Qualifier(value = "releaseAliasTaskExecutor")
	public ThreadPoolTaskExecutor releaseAliasTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(250000);
		executor.setThreadNamePrefix("releaseAliasTaskExecutor");
		executor.initialize();
		return executor;
	}
	
	@Bean
	RequestMapping initReleaseAlias() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initReleaseAlias");
		return mapping;
	}

	@Bean
	@Qualifier("releaseAliasGate")
	public HttpRequestHandlingMessagingGateway releaseAliasGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initReleaseAlias());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(releaseAliasChannel());
		gateway.setReplyChannel(releaseAliasReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "releasealias")
	@Qualifier(value="releaseAliasMapStrategy")
	BaseMapColumnStrategy<ReleaseAliasBean> releaseAliasMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="releaseAliasCsvToBean")
	CsvToBean<ReleaseAliasBean> releaseAliasCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="releaseAliasParser")
	BaseMapParser<ReleaseAliasBean> releaseAliasParser() { 
		return new BaseMapParser<>(releaseAliasMapStrategy());
	}

	@Service
	class ReleaseAliasProcessor extends CsvBaseProcessor<ReleaseAliasBean, BaseMapParser<ReleaseAliasBean>>{

		public ReleaseAliasProcessor
		(@Autowired @Qualifier("releaseAliasParser") BaseMapParser<ReleaseAliasBean> parser,
				@Autowired @Qualifier("releaseAliasCsvToBean") CsvToBean<ReleaseAliasBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="releaseAliasMessageHander")
	CsvMessageHandler releaseAliasMessageHander(@Autowired ReleaseAliasProcessor processor) { 
		return new CsvMessageHandler(releaseAliasIntegrationStartChannel() , releaseAliasReplyChannel() , processor);
	}

	@Bean
	IntegrationFlow releaseAliasFlowRequestHttp
	(@Autowired @Qualifier("releaseAliasMessageHander") CsvMessageHandler handler) { 
		return IntegrationFlows
				.from(releaseAliasChannel())
				.handle(handler)
				.get();
	}

	@Bean IntegrationFlow processReleaseAliasRequest
	(@Autowired ReleaseAliasHandler handler ,
	@Autowired ReleaseAliasTransformer transformer) { 
		return IntegrationFlows
				.from(releaseAliasIntegrationStartChannel())
				.transform(transformer)
				.handle(handler)
				.get();
	}

	@MessageEndpoint
	class ReleaseAliasHandler implements MessageHandler{ 
		
		@Autowired 
		ReleaseAliasJpaService service;
		
		@Autowired 
		ReleaseAliasBeginDateRepository beginRepo;
		
		@Autowired 
		ReleaseAliasEndDateRepository endRepo;
		
		@Autowired
		ReleaseAliasLocaleRepository localeRepo;
		
		@Autowired
		ReleaseAliasSortNameRepository sortRep;
		
		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			ReleaseAliasHolder holder = (ReleaseAliasHolder) message.getPayload();
			ReleaseAlias transientAlias = holder.getAlias();
			Optional<ReleaseAliasBeginDate> optBegin = holder.getOptBeginDate();
			Optional<ReleaseAliasEndDate> optEnd = holder.getOptEndDate();
			Optional<ReleaseAliasLocale> optLocale = holder.getOptLocale();
			Optional<ReleaseAliasSortName> optSortName = holder.getOptSortName();
			
			optSortName.ifPresent(s -> {
				ReleaseAliasSortName newSort = sortRep.save(s);
				transientAlias.setSortName(newSort);
			});
			
			ReleaseAlias releaseAlias = service.save(transientAlias);
			
			optBegin.ifPresent(
								b -> 
								{
									b.setReleaseAlias(releaseAlias); 
									beginRepo.save(b);
								});
			optEnd.ifPresent(
								e ->{
									e.setReleaseAlias(releaseAlias);
									endRepo.save(e);
								});
			optLocale.ifPresent(
									l -> {
											l.setReleaseAlias(releaseAlias);
											localeRepo.save(l);
									});
		}
		
	}
	
	@MessageEndpoint
	class ReleaseAliasTransformer implements GenericTransformer<ReleaseAliasBean , ReleaseAliasHolder>{

		@Autowired
		ReleaseJpaService releaseService; 
		
		@Autowired
		ReleaseAliasTypeRepository repository;
		
		@Override
		public ReleaseAliasHolder transform(ReleaseAliasBean source) {
			
			if (source.getRelease() == null)
				throw new MessagingException ("Release is not present " + source.getRelease());
			
			ReleaseAlias alias = new ReleaseAlias(source.getId() ,  source.getName());
			
			Optional<ReleaseAliasSortName> optSortName = Optional.of(new ReleaseAliasSortName(source.getSortName()));
			
			Optional<Release> optRelease = releaseService.findByReleaseId(source.getRelease());
			Optional<ReleaseAliasType> ptype = Base.NULL_VALUE(ReleaseAliasType.class);
			Optional<ReleaseAliasBeginDate> optBeginDate = Base.NULL_VALUE(ReleaseAliasBeginDate.class);
			Optional<ReleaseAliasEndDate> optEndDate = Base.NULL_VALUE(ReleaseAliasEndDate.class);
			Optional<ReleaseAliasLocale> optLocale = Base.NULL_VALUE(ReleaseAliasLocale.class);
			
			if (!optRelease.isPresent())
				throw new MessagingException ("Release is not present " + source.getRelease());
			
			alias.setRelease(optRelease.get());
			
			if(NotEmpty(source.getLocale())) { 
				ReleaseAliasLocale locale = new ReleaseAliasLocale(source.getLocale());
				optLocale = Optional.of(locale);
			}
			
			if (source.getType() !=null) {
				repository
					.findById(source.getType())
					.ifPresent(p -> alias.setType(p));
			} 
			
			if(source.getBeginDateYear() !=null) { 
				ReleaseAliasBeginDate dt = new ReleaseAliasBeginDate(source.getBeginDateYear() , source.getBeginDateMonth() , source.getBeginDateDay());
				optBeginDate = Optional.of(dt);
			}
			
			if(source.getEndDateYear() != null) { 
				ReleaseAliasEndDate dt = new ReleaseAliasEndDate(source.getEndDateYear() , source.getEndDateMonth() , source.getEndDateDay());
				optEndDate = Optional.of(dt);
			}
			
			return new ReleaseAliasHolder(alias , optRelease , ptype , optBeginDate , optEndDate , optLocale , optSortName );
			
		}

		private boolean NotEmpty(String locale) {
			return locale != null && !"".contentEquals(locale.trim());
		} 
		
	}
	
	class ReleaseAliasHolder { 
		
		private ReleaseAlias alias;
		private Optional<Release> optRelease ;
		private Optional<ReleaseAliasType> ptype ;
		private Optional<ReleaseAliasBeginDate> optBeginDate ;
		private Optional<ReleaseAliasEndDate> optEndDate ;
		private Optional<ReleaseAliasLocale> optLocale ;
		Optional<ReleaseAliasSortName> optSortName;
		
		public ReleaseAliasHolder(ReleaseAlias alias, Optional<Release> optRelease, Optional<ReleaseAliasType> ptype,
				Optional<ReleaseAliasBeginDate> optBeginDate, Optional<ReleaseAliasEndDate> optEndDate,
				Optional<ReleaseAliasLocale> optLocale, Optional<ReleaseAliasSortName> optSortName) {
			super();
			this.alias = alias;
			this.optRelease = optRelease;
			this.ptype = ptype;
			this.optBeginDate = optBeginDate;
			this.optEndDate = optEndDate;
			this.optLocale = optLocale;
			this.optSortName = optSortName;
		}

		public ReleaseAlias getAlias() {
			return alias;
		}

		public Optional<Release> getOptRelease() {
			return optRelease;
		}

		public Optional<ReleaseAliasType> getPtype() {
			return ptype;
		}

		public Optional<ReleaseAliasBeginDate> getOptBeginDate() {
			return optBeginDate;
		}

		public Optional<ReleaseAliasEndDate> getOptEndDate() {
			return optEndDate;
		}

		public Optional<ReleaseAliasLocale> getOptLocale() {
			return optLocale;
		}

		public Optional<ReleaseAliasSortName> getOptSortName() {
			return optSortName;
		}
		
	}
	
}
