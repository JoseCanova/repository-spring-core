package org.nanotek.configuration.csv;

import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.ReleasePackagingBean;
import org.nanotek.beans.entity.ReleasePackaging;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.ReleasePackagingRepository;
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
@IntegrationComponentScan(basePackageClasses = {ReleasePackagingIntegrationConfiguration.class})
public class ReleasePackagingIntegrationConfiguration {

	
	private final Logger logger = LoggerFactory.getLogger(ReleasePackagingIntegrationConfiguration.class);

	@Value("${server.port}")
	private String serverPort;
	
	@Bean
	@Qualifier(value="releasePackagingChannel")
	MessageChannel releasePackagingChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releasePackagingReplyChannel")
	MessageChannel releasePackagingReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releasePackagingIntegrationStartChannel")
	MessageChannel releasePackagingIntegrationStartChannel(
			@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}
	
	@Bean
	RequestMapping initReleasePackaging() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initReleasePackaging");
		return mapping;
	}
	
	@Bean
	@Qualifier("releasePackagingGate")
	HttpRequestHandlingMessagingGateway releasePackagingGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initReleasePackaging());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(releasePackagingChannel());
		gateway.setReplyChannel(releasePackagingReplyChannel());
		return gateway;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "releasepackaging")
	@Qualifier(value="releasePackagingMapStrategy")
	BaseMapColumnStrategy<ReleasePackagingBean> releasePackagingMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="releasePackagingCsvToBean")
	CsvToBean<ReleasePackagingBean> releasePackagingCsvToBean() {
		return new CsvToBean<>();
	}
	
	@Bean
	@Qualifier(value="releasePackagingParser")
	BaseMapParser<ReleasePackagingBean> releasePackagingParser() { 
		return new BaseMapParser<>(releasePackagingMapStrategy());
	}
	
	
	@Service
	class ReleasePackagingProcessor extends CsvBaseProcessor<ReleasePackagingBean, BaseMapParser<ReleasePackagingBean>>{

		public ReleasePackagingProcessor
		(@Autowired @Qualifier("releasePackagingParser") BaseMapParser<ReleasePackagingBean> parser,
				@Autowired @Qualifier("releasePackagingCsvToBean") CsvToBean<ReleasePackagingBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}
	
	@Bean
	@Qualifier(value="releasePackagingMessageHander")
	CsvMessageHandler releasePackagingMessageHander
	(@Autowired @Qualifier("releasePackagingIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("releasePackagingReplyChannel") MessageChannel replyChannel,
			@Autowired ReleasePackagingProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}
	
	@Bean
	IntegrationFlow releasePackagingFlowRequestHttp
	(@Autowired @Qualifier("releasePackagingMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("releasePackagingChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class ReleasePackagingTransformer implements GenericTransformer<ReleasePackagingBean , ReleasePackaging>{

		@Override
		public ReleasePackaging transform(ReleasePackagingBean source) {
			return new ReleasePackaging(source.getId() , source.getName() , source.getGid());
		} 
	}
	
	@MessageEndpoint
	class ReleasePackagingHandler implements MessageHandler{ 

		@Autowired
		ReleasePackagingRepository repository;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			logger.info(message.getPayload().toString());
			repository.save((ReleasePackaging) message.getPayload());
		}
	}
	
	@Bean IntegrationFlow processReleasePackagingRequest
	(@Autowired ReleasePackagingHandler handler , 
			@Autowired @Qualifier("releasePackagingIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired ReleasePackagingTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
}
