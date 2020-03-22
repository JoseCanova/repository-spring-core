package org.nanotek.configuration.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.ReleaseGroupBean;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.ReleaseGroup;
import org.nanotek.beans.entity.ReleaseGroupPrimaryType;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.ReleaseGroupPrimaryTypeRepository;
import org.nanotek.service.CsvMessageHandler;
import org.nanotek.service.jpa.ArtistCreditJpaService;
import org.nanotek.service.jpa.ReleaseGroupJpaService;
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
@IntegrationComponentScan(basePackageClasses = {ReleaseGroupIntegrationConfiguration.class})
public class ReleaseGroupIntegrationConfiguration {

	private final Logger logger = LoggerFactory.getLogger(ReleaseGroupIntegrationConfiguration.class);

	@Value("${server.port}")
	private String serverPort;


	@Bean
	@Qualifier(value="releaseGroupChannel")
	MessageChannel releaseGroupChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releaseGroupReplyChannel")
	MessageChannel releaseGroupReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releaseGroupIntegrationStartChannel")
	MessageChannel releaseGroupIntegrationStartChannel() {
		return new ExecutorChannel(releaseGroupTaskExecutor());
	}

	@Bean(name = "releaseGroupTaskExecutor")
	@Qualifier(value = "releaseGroupTaskExecutor")
	public ThreadPoolTaskExecutor releaseGroupTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(2500000);
		executor.setThreadNamePrefix("releaseGroupTaskExecutor");
		executor.initialize();
		return executor;
	}
	
	@Bean
	RequestMapping initReleaseGroup() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initReleaseGroup");
		return mapping;
	}

	@Bean
	@Qualifier("releaseGroupGate")
	public HttpRequestHandlingMessagingGateway releaseGroupGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initReleaseGroup());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(releaseGroupChannel());
		gateway.setReplyChannel(releaseGroupReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "releasegroup")
	@Qualifier(value="releaseGroupMapStrategy")
	BaseMapColumnStrategy<ReleaseGroupBean> releaseGroupMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="releaseGroupCsvToBean")
	CsvToBean<ReleaseGroupBean> releaseGroupCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="releaseGroupParser")
	BaseMapParser<ReleaseGroupBean> releaseGroupParser() { 
		return new BaseMapParser<>(releaseGroupMapStrategy());
	}

	@Service
	class ReleaseGroupProcessor extends CsvBaseProcessor<ReleaseGroupBean, BaseMapParser<ReleaseGroupBean>>{

		public ReleaseGroupProcessor
		(@Autowired @Qualifier("releaseGroupParser") BaseMapParser<ReleaseGroupBean> parser,
				@Autowired @Qualifier("releaseGroupCsvToBean") CsvToBean<ReleaseGroupBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="releaseGroupMessageHander")
	CsvMessageHandler releaseGroupMessageHander(@Autowired ReleaseGroupProcessor processor) { 
		return new CsvMessageHandler(releaseGroupIntegrationStartChannel() , releaseGroupReplyChannel() , processor);
	}

	@Bean
	IntegrationFlow releaseGroupFlowRequestHttp
	(@Autowired @Qualifier("releaseGroupMessageHander") CsvMessageHandler handler) { 
		return IntegrationFlows
				.from(releaseGroupChannel())
				.handle(handler)
				.get();
	}

	@Bean IntegrationFlow processReleaseGroupRequest
	(@Autowired ReleaseGroupHandler handler ,
	@Autowired ReleaseGroupTransformer transformer) { 
		return IntegrationFlows
				.from(releaseGroupIntegrationStartChannel())
				.transform(transformer)
				.handle(handler)
				.get();
	}

	
	@MessageEndpoint
	class ReleaseGroupHandler implements MessageHandler{ 
		
		@Autowired 
		ReleaseGroupJpaService service;
		
		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			service.save((ReleaseGroup) message.getPayload());
		}
	}
	
	@MessageEndpoint
	class ReleaseGroupTransformer implements GenericTransformer<ReleaseGroupBean , ReleaseGroup>{

		@Autowired
		ArtistCreditJpaService creditService; 
		
		@Autowired
		ReleaseGroupPrimaryTypeRepository repository;
		
		@Override
		public ReleaseGroup transform(ReleaseGroupBean source) {
			Optional<ArtistCredit> optCredit = creditService.findById(source.getArtistCredit());
			Optional<ReleaseGroupPrimaryType> ptype = Base.NULL_VALUE(ReleaseGroupPrimaryType.class);
			ReleaseGroupPrimaryType type ; 
			if (!optCredit.isPresent())
				throw new MessagingException ("ArtistCredit is not present " + source.getArtistCredit());
			ArtistCredit credit = optCredit.get();
			if (source.getType() !=null) {
				ptype = repository.findById(new Long(source.getType()));
				type = ptype.isPresent() ? ptype.get() : null;
			}else {
				type = repository.findByNameContainingIgnoreCase("Other").iterator().next();
			}
			return new ReleaseGroup(source.getId() , source.getGid() , source.getName() , credit , type);
		} 
		
	}
	
}
