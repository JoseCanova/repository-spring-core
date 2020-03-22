package org.nanotek.configuration.csv;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.RecordingBean;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.Recording;
import org.nanotek.beans.entity.RecordingLength;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.service.CsvMessageHandler;
import org.nanotek.service.jpa.ArtistCreditJpaService;
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
import org.springframework.expression.spel.standard.SpelExpressionParser;
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
import org.springframework.validation.annotation.Validated;

import au.com.bytecode.opencsv.bean.CsvToBean;

@Configuration
@EnableIntegration
@EnableConfigurationProperties
@IntegrationComponentScan(basePackageClasses = {RecordingIntegrationConfiguration.class})
public class RecordingIntegrationConfiguration {

	private final Logger logger = LoggerFactory.getLogger(RecordingIntegrationConfiguration.class);

	@Value("${server.port}")
	private String serverPort;
	
	@Bean
	@Qualifier(value="recordingChannel")
	MessageChannel recordingChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="recordingReplyChannel")
	MessageChannel recordingReplyChannel() {
		return new DirectChannel();
	}

	
	@Bean(name = "recordingTaskExecutor")
	@Qualifier(value = "recordingTaskExecutor")
	public ThreadPoolTaskExecutor recordingTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(2500000);
		executor.setThreadNamePrefix("recordingTaskExecutor");
		executor.initialize();
		return executor;
	}
	
	
	@Bean
	@Qualifier(value="recordingIntegrationStartChannel")
	MessageChannel recordingIntegrationStartChannel(
			@Autowired @Qualifier("recordingTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}
	
	@Bean
	RequestMapping initRecording() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initRecording/{fileId}");
		return mapping;
	}
	
	@Bean
	@Qualifier("recordingGate")
	HttpRequestHandlingMessagingGateway recordingGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initRecording());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(recordingChannel());
		gateway.setReplyChannel(recordingReplyChannel());
		gateway.setPayloadExpression(new SpelExpressionParser().parseRaw("#pathVariables.fileId"));
		return gateway;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "recordingbean")
	@Qualifier(value="recordingMapStrategy")
	BaseMapColumnStrategy<RecordingBean> recordingMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="recordingCsvToBean")
	CsvToBean<RecordingBean> recordingCsvToBean() {
		return new CsvToBean<>();
	}
	
	@Bean
	@Qualifier(value="recordingParser")
	BaseMapParser<RecordingBean> recordingParser() { 
		return new BaseMapParser<>(recordingMapStrategy());
	}
	
	
	@Service
	class RecordingProcessor extends CsvBaseProcessor<RecordingBean, BaseMapParser<RecordingBean>>{

		public RecordingProcessor
		(@Autowired @Qualifier("recordingParser") BaseMapParser<RecordingBean> parser,
				@Autowired @Qualifier("recordingCsvToBean") CsvToBean<RecordingBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}
	
	@Bean
	@Qualifier(value="recordingMessageHander")
	CsvMessageHandler recordingMessageHander
	(@Autowired @Qualifier("recordingIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("recordingReplyChannel") MessageChannel replyChannel,
			@Autowired RecordingProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor) { 
			public void setNewFileName(String newFileName) { 
				processor.getBaseParser().setFileName(newFileName);
			}
			
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				setNewFileName((String) message.getPayload()); 
				super.handleMessage(message);
			}
		};
	}
	
	@Bean
	IntegrationFlow recordingFlowRequestHttp
	(@Autowired @Qualifier("recordingMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("recordingChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class RecordingTransformer implements GenericTransformer<RecordingBean , Recording>{

		@Autowired 
		private ArtistCreditJpaService artistCreditJpaService;
		
		@Override
		public Recording transform(RecordingBean i) {
			Recording recording = new Recording();
			recording.setRecordingId(i.getId());
			recording.setGid(i.getGid());
			recording.setName(i.getName());
			Optional<ArtistCredit> ac = findArtistCredit(i.getArtistCredit());
			ArtistCredit artistCredit = ac.isPresent() ? ac.get() : null;
			recording.setArtistCredit(artistCredit);
			Optional<RecordingLength> optRl =  verifyRecordingLength(recording , i.getLength());
			if (optRl.isPresent()) recording.setRecordingLenght(optRl.get());
			return recording;
		}

		private Optional<RecordingLength> verifyRecordingLength(Recording recording , Long length) {
			return length !=null && length > 0 ? Optional.of(new RecordingLength(recording , length)) : Base.NULL_VALUE(RecordingLength.class);
		}

		private Optional<ArtistCredit> findArtistCredit(@Validated @NotNull Long artistCredit) {
			return artistCreditJpaService.findByArtistCreditId(artistCredit);
		}
	}
	
	@MessageEndpoint
	class RecordingHandler implements MessageHandler{ 

		@Autowired
		RecordingJpaService service;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			service.save((Recording) message.getPayload());
		}
	}
	
	@Bean IntegrationFlow processRecordingRequest
	(@Autowired RecordingHandler handler , 
			@Autowired @Qualifier("recordingIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired RecordingTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
}