package org.nanotek.configuration.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.RecordingAliasBean;
import org.nanotek.beans.entity.Recording;
import org.nanotek.beans.entity.RecordingAlias;
import org.nanotek.beans.entity.RecordingAliasType;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.RecordingAliasTypeRepository;
import org.nanotek.service.CsvMessageHandler;
import org.nanotek.service.jpa.RecordingAliasJpaService;
import org.nanotek.service.jpa.RecordingJpaService;
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
@IntegrationComponentScan(basePackageClasses = {RecordingAliasIntegrationConfiguration.class})
public class RecordingAliasIntegrationConfiguration {

	private final Logger logger = LoggerFactory.getLogger(RecordingAliasIntegrationConfiguration.class);

	@Value("${server.port}")
	private String serverPort;


	@Bean
	@Qualifier(value="recordingAliasChannel")
	MessageChannel recordingAliasChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="recordingAliasReplyChannel")
	MessageChannel recordingAliasReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="recordingAliasIntegrationStartChannel")
	MessageChannel recordingAliasIntegrationStartChannel() {
		return new ExecutorChannel(recordingAliasTaskExecutor());
	}

	@Bean(name = "recordingAliasTaskExecutor")
	@Qualifier(value = "recordingAliasTaskExecutor")
	public ThreadPoolTaskExecutor recordingAliasTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(250000);
		executor.setThreadNamePrefix("recordingAliasTaskExecutor");
		executor.initialize();
		return executor;
	}
	
	@Bean
	RequestMapping initRecordingAlias() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initRecordingAlias");
		return mapping;
	}

	@Bean
	@Qualifier("recordingAliasGate")
	public HttpRequestHandlingMessagingGateway recordingAliasGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initRecordingAlias());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(recordingAliasChannel());
		gateway.setReplyChannel(recordingAliasReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "recordingalias")
	@Qualifier(value="recordingAliasMapStrategy")
	BaseMapColumnStrategy<RecordingAliasBean> recordingAliasMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="recordingAliasCsvToBean")
	CsvToBean<RecordingAliasBean> recordingAliasCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="recordingAliasParser")
	BaseMapParser<RecordingAliasBean> recordingAliasParser() { 
		return new BaseMapParser<>(recordingAliasMapStrategy());
	}

	@Service
	class RecordingAliasProcessor extends CsvBaseProcessor<RecordingAliasBean, BaseMapParser<RecordingAliasBean>>{

		public RecordingAliasProcessor
		(@Autowired @Qualifier("recordingAliasParser") BaseMapParser<RecordingAliasBean> parser,
				@Autowired @Qualifier("recordingAliasCsvToBean") CsvToBean<RecordingAliasBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="recordingAliasMessageHander")
	CsvMessageHandler recordingAliasMessageHander(@Autowired RecordingAliasProcessor processor) { 
		return new CsvMessageHandler(recordingAliasIntegrationStartChannel() , recordingAliasReplyChannel() , processor);
	}

	@Bean
	IntegrationFlow recordingAliasFlowRequestHttp
	(@Autowired @Qualifier("recordingAliasMessageHander") CsvMessageHandler handler) { 
		return IntegrationFlows
				.from(recordingAliasChannel())
				.handle(handler)
				.get();
	}

	@Bean IntegrationFlow processRecordingAliasRequest
	(@Autowired RecordingAliasHandler handler ,
	@Autowired RecordingAliasTransformer transformer) { 
		return IntegrationFlows
				.from(recordingAliasIntegrationStartChannel())
				.transform(transformer)
				.handle(handler)
				.get();
	}

	
	@MessageEndpoint
	class RecordingAliasHandler implements MessageHandler{ 
		
		@Autowired 
		RecordingAliasJpaService service;
		
		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			service.save((RecordingAlias) message.getPayload());
		}
	}
	
	@MessageEndpoint
	class RecordingAliasTransformer implements GenericTransformer<RecordingAliasBean , RecordingAlias>{

		@Autowired
		RecordingJpaService recService; 
		
		@Autowired
		RecordingAliasTypeRepository repository;
		
		@Override
		public RecordingAlias transform(RecordingAliasBean source) {
			Optional<Recording> optRecording = recService.findById(source.getRecording());
			Optional<RecordingAliasType> ptype = Base.NULL_VALUE(RecordingAliasType.class);
			RecordingAliasType type = null; 
			if (!optRecording.isPresent())
				throw new MessagingException ("Recording is not present " + source.getRecording());
			Recording recording = optRecording.get();
			if (source.getType() !=null) {
				ptype = repository.findById(source.getType());
				if (ptype.isPresent())
					type =  ptype.get();
			} 
			
			return new RecordingAlias(source.getId() ,  source.getName() , source.getLocale() , recording,
					type , source.getSortName() , source.getBeginDateYear() , source.getBeginDateMonth() , source.getBeginDateDay(),
					source.getEndDateYear() , source.getEndDateMonth() , source.getEndDateDay());
			
		} 
		
	}
	
}
