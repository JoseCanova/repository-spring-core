package org.nanotek.configuration.csv;

import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.MediumFormatBean;
import org.nanotek.beans.entity.MediumFormat;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.MediumFormatRepository;
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
@IntegrationComponentScan(basePackageClasses = {MediumFormatConfiguration.class})
public class MediumFormatConfiguration {

	private final Logger logger = LoggerFactory.getLogger(MediumFormatConfiguration.class);

	@Value("${server.port}")
	private String serverPort;

	@Bean
	@Qualifier(value="mediumFormatChannel")
	MessageChannel mediumFormatChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="mediumFormatReplyChannel")
	MessageChannel mediumFormatReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="mediumFormatIntegrationStartChannel")
	MessageChannel mediumFormatIntegrationStartChannel(
			@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}

	@Bean
	RequestMapping initMediumFormat() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initMediumFormat");
		return mapping;
	}

	@Bean
	@Qualifier("mediumFormatGate")
	public HttpRequestHandlingMessagingGateway mediumFormatGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initMediumFormat());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(mediumFormatChannel());
		gateway.setReplyChannel(mediumFormatReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "mediumformat")
	@Qualifier(value="mediumFormatMapStrategy")
	BaseMapColumnStrategy<MediumFormatBean> mediumFormatMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="mediumFormatCsvToBean")
	CsvToBean<MediumFormatBean> mediumFormatCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="mediumFormatParser")
	BaseMapParser<MediumFormatBean> mediumFormatParser() { 
		return new BaseMapParser<>(mediumFormatMapStrategy());
	}

	@Service
	class ReleasePrimaryGroupProcessor extends CsvBaseProcessor<MediumFormatBean, BaseMapParser<MediumFormatBean>>{

		public ReleasePrimaryGroupProcessor
		(@Autowired @Qualifier("mediumFormatParser") BaseMapParser<MediumFormatBean> parser,
				@Autowired @Qualifier("mediumFormatCsvToBean") CsvToBean<MediumFormatBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="mediumFormatMessageHander")
	CsvMessageHandler mediumFormatMessageHander
	(@Autowired @Qualifier("mediumFormatIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("mediumFormatReplyChannel") MessageChannel replyChannel,
			@Autowired ReleasePrimaryGroupProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}

	@Bean
	IntegrationFlow mediumFormatFlowRequestHttp
	(@Autowired @Qualifier("mediumFormatMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("mediumFormatChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}

	@MessageEndpoint
	class ReleasePrimaryTypeGroupHandler implements MessageHandler{ 

		@Autowired
		MediumFormatRepository repository;
		/*
		 * @Autowired InstrumentJpaService service;
		 */

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			logger.info(message.getPayload().toString());
			repository.save((MediumFormat) message.getPayload());
		}
	}

	@Bean IntegrationFlow processMediumFormatRequest
	(@Autowired ReleasePrimaryTypeGroupHandler handler , 
			@Autowired @Qualifier("mediumFormatIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired MediumFormatTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class MediumFormatTransformer implements GenericTransformer<MediumFormatBean, MediumFormat>{

		@Override
		public MediumFormat transform(MediumFormatBean source) {
			return new MediumFormat(source.getId() , source.getName(), 
									source.getParent() , source.getYear(), 
									source.getHasDiscIds() , source.getGid(), 
									source.getDescription() );
		} 
	}
}