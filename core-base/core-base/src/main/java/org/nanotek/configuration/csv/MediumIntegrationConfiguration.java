package org.nanotek.configuration.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.MediumBean;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.Language;
import org.nanotek.beans.entity.Medium;
import org.nanotek.beans.entity.MediumFormat;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.LanguageRepository;
import org.nanotek.repository.jpa.MediumFormatRepository;
import org.nanotek.service.CsvMessageHandler;
import org.nanotek.service.jpa.MediumJpaService;
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
@IntegrationComponentScan(basePackageClasses = {MediumIntegrationConfiguration.class})
public class MediumIntegrationConfiguration {

	private final Logger logger = LoggerFactory.getLogger(MediumIntegrationConfiguration.class);

	@Value("${server.port}")
	private String serverPort;
	
	@Bean
	@Qualifier(value="mediumChannel")
	MessageChannel mediumChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="mediumReplyChannel")
	MessageChannel mediumReplyChannel() {
		return new DirectChannel();
	}

	
	@Bean(name = "mediumTaskExecutor")
	@Qualifier(value = "mediumTaskExecutor")
	public ThreadPoolTaskExecutor mediumTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(2500000);
		executor.setThreadNamePrefix("mediumTaskExecutor");
		executor.initialize();
		return executor;
	}
	
	
	@Bean
	@Qualifier(value="mediumIntegrationStartChannel")
	MessageChannel mediumIntegrationStartChannel(
			@Autowired @Qualifier("mediumTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}
	
	@Bean
	RequestMapping initMedium() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initMedium");
		return mapping;
	}
	
	@Bean
	@Qualifier("mediumGate")
	HttpRequestHandlingMessagingGateway mediumGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initMedium());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(mediumChannel());
		gateway.setReplyChannel(mediumReplyChannel());
		return gateway;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "mediumbean")
	@Qualifier(value="mediumMapStrategy")
	BaseMapColumnStrategy<MediumBean> mediumMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="mediumCsvToBean")
	CsvToBean<MediumBean> mediumCsvToBean() {
		return new CsvToBean<>();
	}
	
	@Bean
	@Qualifier(value="mediumParser")
	BaseMapParser<MediumBean> mediumParser() { 
		return new BaseMapParser<>(mediumMapStrategy());
	}
	
	
	@Service
	class MediumProcessor extends CsvBaseProcessor<MediumBean, BaseMapParser<MediumBean>>{

		public MediumProcessor
		(@Autowired @Qualifier("mediumParser") BaseMapParser<MediumBean> parser,
				@Autowired @Qualifier("mediumCsvToBean") CsvToBean<MediumBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}
	
	@Bean
	@Qualifier(value="mediumMessageHander")
	CsvMessageHandler mediumMessageHander
	(@Autowired @Qualifier("mediumIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("mediumReplyChannel") MessageChannel replyChannel,
			@Autowired MediumProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}
	
	@Bean
	IntegrationFlow mediumFlowRequestHttp
	(@Autowired @Qualifier("mediumMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("mediumChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class MediumTransformer implements GenericTransformer<MediumBean , Medium>{

		@Autowired
		MediumFormatRepository formatRepository;
		
		@Autowired
		LanguageRepository langRep; 
		
		@Override
		public Medium transform(MediumBean source) {
			
			Optional<MediumFormat> optFormat = Base.NULL_VALUE(MediumFormat.class);
			MediumFormat format = null; 
			
			Optional<Language> optLanguage = Base.NULL_VALUE(Language.class);
			Language language = null;
			
			Optional<ArtistCredit> optCredit = Base.NULL_VALUE(ArtistCredit.class);
			ArtistCredit credit = null;
			
			if (source.getFormat() !=null) {
				optFormat = formatRepository.findById(source.getFormat());
				 if (optFormat.isPresent())
					 format =  optFormat.get();
			}
			
			return new Medium();
		} 
	}
	
	@MessageEndpoint
	class MediumHandler implements MessageHandler{ 

		@Autowired
		MediumJpaService service;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			service.save((Medium) message.getPayload());
		}
	}
	
	@Bean IntegrationFlow processMediumRequest
	(@Autowired MediumHandler handler , 
			@Autowired @Qualifier("mediumIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired MediumTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
}
