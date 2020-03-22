package org.nanotek.configuration.csv;

import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.LanguageBean;
import org.nanotek.beans.entity.Language;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.service.CsvMessageHandler;
import org.nanotek.service.jpa.LanguageJpaService;
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
@IntegrationComponentScan(basePackageClasses = {LanguageIntegrationConfiguration.class})
public class LanguageIntegrationConfiguration {

	
	private final Logger logger = LoggerFactory.getLogger(LanguageIntegrationConfiguration.class);

	@Value("${server.port}")
	private String serverPort;
	
	@Bean
	@Qualifier(value="languageChannel")
	MessageChannel languageChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="languageReplyChannel")
	MessageChannel languageReplyChannel() {
		return new DirectChannel();
	}
	
	@Bean(name = "languageTaskExecutor")
	@Qualifier(value = "languageTaskExecutor")
	ThreadPoolTaskExecutor languageTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(10000);
		executor.setThreadNamePrefix("languageTaskExecutor");
		executor.initialize();
		return executor;
	}
	
	
	@Bean
	@Qualifier(value="languageIntegrationStartChannel")
	MessageChannel languageIntegrationStartChannel(
			@Autowired @Qualifier("languageTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}
	
	@Bean
	RequestMapping initLanguage() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initLanguage");
		return mapping;
	}
	
	@Bean
	@Qualifier("languageGate")
	HttpRequestHandlingMessagingGateway languageGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initLanguage());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(languageChannel());
		gateway.setReplyChannel(languageReplyChannel());
		return gateway;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "language")
	@Qualifier(value="languageMapStrategy")
	BaseMapColumnStrategy<LanguageBean> languageMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="languageCsvToBean")
	CsvToBean<LanguageBean> languageCsvToBean() {
		return new CsvToBean<>();
	}
	
	@Bean
	@Qualifier(value="languageParser")
	BaseMapParser<LanguageBean> languageParser() { 
		return new BaseMapParser<>(languageMapStrategy());
	}
	
	
	@Service
	class LanguageProcessor extends CsvBaseProcessor<LanguageBean, BaseMapParser<LanguageBean>>{

		public LanguageProcessor
		(@Autowired @Qualifier("languageParser") BaseMapParser<LanguageBean> parser,
				@Autowired @Qualifier("languageCsvToBean") CsvToBean<LanguageBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}
	
	@Bean
	@Qualifier(value="languageMessageHander")
	CsvMessageHandler languageMessageHander
	(@Autowired @Qualifier("languageIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("languageReplyChannel") MessageChannel replyChannel,
			@Autowired LanguageProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}
	
	@Bean
	IntegrationFlow languageFlowRequestHttp
	(@Autowired @Qualifier("languageMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("languageChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class LanguageTransformer implements GenericTransformer<LanguageBean , Language>{

		@Override
		public Language transform(LanguageBean source) {
			return new Language(source.getId(),source.getIsoCode2t(),source.getIsoCode2b(),source.getIsoCode1(),source.getName(),source.getFrequency(),source.getIsoCode3());
		} 
	}
	
	@MessageEndpoint
	class LanguageHandler implements MessageHandler{ 

		@Autowired
		LanguageJpaService service;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			logger.info(message.getPayload().toString());
			service.save((Language) message.getPayload());
		}
	}
	
	@Bean IntegrationFlow processLanguageRequest
	(@Autowired LanguageHandler handler , 
			@Autowired @Qualifier("languageIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired LanguageTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
}
