package org.nanotek.configuration.csv;

import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.ReleaseGroupPrimaryTypeBean;
import org.nanotek.beans.entity.ReleaseGroupPrimaryType;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.ReleaseGroupPrimaryTypeRepository;
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
@IntegrationComponentScan(basePackageClasses = {ReleaseGroupPrimaryTypeConfiguration.class})
public class ReleaseGroupPrimaryTypeConfiguration {

	private final Logger logger = LoggerFactory.getLogger(ReleaseGroupPrimaryTypeConfiguration.class);

	@Value("${server.port}")
	private String serverPort;


	@Bean
	@Qualifier(value="releasePrimaryTypeGroupChannel")
	MessageChannel releasePrimaryTypeGroupChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releasePrimaryTypeGroupReplyChannel")
	MessageChannel releasePrimaryTypeGroupReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releasePrimaryTypeGroupIntegrationStartChannel")
	MessageChannel releasePrimaryTypeGroupIntegrationStartChannel(
			@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}

	@Bean
	RequestMapping initReleaseGroupPrimaryType() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initReleaseGroupPrimaryType");
		return mapping;
	}

	@Bean
	@Qualifier("releasePrimaryTypeGroupGate")
	public HttpRequestHandlingMessagingGateway releasePrimaryTypeGroupGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initReleaseGroupPrimaryType());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(releasePrimaryTypeGroupChannel());
		gateway.setReplyChannel(releasePrimaryTypeGroupReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "releasegroupprimarytype")
	@Qualifier(value="releasePrimaryTypeGroupMapStrategy")
	BaseMapColumnStrategy<ReleaseGroupPrimaryTypeBean> releasePrimaryTypeGroupMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="releasePrimaryTypeGroupCsvToBean")
	CsvToBean<ReleaseGroupPrimaryTypeBean> releasePrimaryTypeGroupCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="releasePrimaryTypeGroupParser")
	BaseMapParser<ReleaseGroupPrimaryTypeBean> releasePrimaryTypeGroupParser() { 
		return new BaseMapParser<>(releasePrimaryTypeGroupMapStrategy());
	}

	@Service
	class ReleasePrimaryGroupProcessor extends CsvBaseProcessor<ReleaseGroupPrimaryTypeBean, BaseMapParser<ReleaseGroupPrimaryTypeBean>>{

		public ReleasePrimaryGroupProcessor
		(@Autowired @Qualifier("releasePrimaryTypeGroupParser") BaseMapParser<ReleaseGroupPrimaryTypeBean> parser,
				@Autowired @Qualifier("releasePrimaryTypeGroupCsvToBean") CsvToBean<ReleaseGroupPrimaryTypeBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="releasePrimaryTypeGroupMessageHander")
	CsvMessageHandler releasePrimaryTypeGroupMessageHander
	(@Autowired @Qualifier("releasePrimaryTypeGroupIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("releasePrimaryTypeGroupReplyChannel") MessageChannel replyChannel,
			@Autowired ReleasePrimaryGroupProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}

	@Bean
	IntegrationFlow releasePrimaryTypeGroupFlowRequestHttp
	(@Autowired @Qualifier("releasePrimaryTypeGroupMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("releasePrimaryTypeGroupChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}

	@MessageEndpoint
	class ReleasePrimaryTypeGroupHandler implements MessageHandler{ 

		@Autowired
		ReleaseGroupPrimaryTypeRepository repository;
		/*
		 * @Autowired InstrumentJpaService service;
		 */

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			logger.info(message.getPayload().toString());
			repository.save((ReleaseGroupPrimaryType) message.getPayload());
		}
	}

	@Bean IntegrationFlow processReleaseGroupPrimaryTypeRequest
	(@Autowired ReleasePrimaryTypeGroupHandler handler , 
			@Autowired @Qualifier("releasePrimaryTypeGroupIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired ReleaseGroupPrimaryTypeTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class ReleaseGroupPrimaryTypeTransformer implements GenericTransformer<ReleaseGroupPrimaryTypeBean, ReleaseGroupPrimaryType>{

		@Override
		public ReleaseGroupPrimaryType transform(ReleaseGroupPrimaryTypeBean source) {
			return new ReleaseGroupPrimaryType(source.getId() , source.getName() , source.getGid());
		} 
		
	}
	
}
