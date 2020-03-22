package org.nanotek.configuration.csv;

import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.ReleaseStatusBean;
import org.nanotek.beans.entity.ReleaseStatus;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.ReleaseStatusRepository;
import org.nanotek.service.CsvMessageHandler;
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
@IntegrationComponentScan(basePackageClasses = {ReleaseStatusIntegrationConfiguration.class})
public class ReleaseStatusIntegrationConfiguration {

	
	private final Logger logger = LoggerFactory.getLogger(ReleaseStatusIntegrationConfiguration.class);

	@Value("${server.port}")
	private String serverPort;
	
	@Bean
	@Qualifier(value="releaseStatusChannel")
	MessageChannel releaseStatusChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releaseStatusReplyChannel")
	MessageChannel releaseStatusReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releaseStatusIntegrationStartChannel")
	MessageChannel releaseStatusIntegrationStartChannel(
			@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}
	
	@Bean
	RequestMapping initReleaseStatus() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initReleaseStatus");
		return mapping;
	}
	
	@Bean
	@Qualifier("releaseStatusGate")
	HttpRequestHandlingMessagingGateway releaseStatusGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initReleaseStatus());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(releaseStatusChannel());
		gateway.setReplyChannel(releaseStatusReplyChannel());
		return gateway;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "releasestatus")
	@Qualifier(value="releaseStatusMapStrategy")
	BaseMapColumnStrategy<ReleaseStatusBean> releaseStatusMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="releaseStatusCsvToBean")
	CsvToBean<ReleaseStatusBean> releaseStatusCsvToBean() {
		return new CsvToBean<>();
	}
	
	@Bean
	@Qualifier(value="releaseStatusParser")
	BaseMapParser<ReleaseStatusBean> releaseStatusParser() { 
		return new BaseMapParser<>(releaseStatusMapStrategy());
	}
	
	
	@Service
	class ReleaseStatusProcessor extends CsvBaseProcessor<ReleaseStatusBean, BaseMapParser<ReleaseStatusBean>>{

		public ReleaseStatusProcessor
		(@Autowired @Qualifier("releaseStatusParser") BaseMapParser<ReleaseStatusBean> parser,
				@Autowired @Qualifier("releaseStatusCsvToBean") CsvToBean<ReleaseStatusBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}
	
	@Bean
	@Qualifier(value="releaseStatusMessageHander")
	CsvMessageHandler releaseStatusMessageHander
	(@Autowired @Qualifier("releaseStatusIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("releaseStatusReplyChannel") MessageChannel replyChannel,
			@Autowired ReleaseStatusProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}
	
	@Bean
	IntegrationFlow releaseStatusFlowRequestHttp
	(@Autowired @Qualifier("releaseStatusMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("releaseStatusChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class ReleaseStatusTransformer implements GenericTransformer<ReleaseStatusBean , ReleaseStatus>{

		@Override
		public ReleaseStatus transform(ReleaseStatusBean source) {
			return new ReleaseStatus(source.getId() , source.getName() , source.getGid());
		} 
	}
	
	@MessageEndpoint
	class ReleaseStatusHandler implements MessageHandler{ 

		@Autowired
		ReleaseStatusRepository repository;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			logger.info(message.getPayload().toString());
			repository.save((ReleaseStatus) message.getPayload());
		}
	}
	
	@Bean IntegrationFlow processReleaseStatusRequest
	(@Autowired ReleaseStatusHandler handler , 
			@Autowired @Qualifier("releaseStatusIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired ReleaseStatusTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
}
