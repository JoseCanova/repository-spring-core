package org.nanotek.configuration.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.RecordingAliasTypeBean;
import org.nanotek.beans.entity.BaseTypeDescription;
import org.nanotek.beans.entity.RecordingAliasType;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.BaseTypeDescriptionRepository;
import org.nanotek.repository.jpa.RecordingAliasTypeRepository;
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
@IntegrationComponentScan(basePackageClasses = {RecordingAliasTypeConfiguration.class})
public class RecordingAliasTypeConfiguration {

	private final Logger logger = LoggerFactory.getLogger(RecordingAliasTypeConfiguration.class);

	@Value("${server.port}")
	private String serverPort;

	@Bean
	@Qualifier(value="recordingAliasTypeChannel")
	MessageChannel recordingAliasTypeChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="recordingAliasTypeReplyChannel")
	MessageChannel recordingAliasTypeReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="recordingAliasTypeIntegrationStartChannel")
	MessageChannel recordingAliasTypeIntegrationStartChannel(
			@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}

	@Bean
	RequestMapping initRecordingAliasType() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initRecordingAliasType");
		return mapping;
	}

	@Bean
	@Qualifier("recordingAliasTypeGate")
	public HttpRequestHandlingMessagingGateway recordingAliasTypeGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initRecordingAliasType());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(recordingAliasTypeChannel());
		gateway.setReplyChannel(recordingAliasTypeReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "recordingaliastype")
	@Qualifier(value="recordingAliasTypeMapStrategy")
	BaseMapColumnStrategy<RecordingAliasTypeBean> recordingAliasTypeMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="recordingAliasTypeCsvToBean")
	CsvToBean<RecordingAliasTypeBean> recordingAliasTypeCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="recordingAliasTypeParser")
	BaseMapParser<RecordingAliasTypeBean> recordingAliasTypeParser() { 
		return new BaseMapParser<>(recordingAliasTypeMapStrategy());
	}

	@Service
	class ReleasePrimaryGroupProcessor extends CsvBaseProcessor<RecordingAliasTypeBean, BaseMapParser<RecordingAliasTypeBean>>{

		public ReleasePrimaryGroupProcessor
		(@Autowired @Qualifier("recordingAliasTypeParser") BaseMapParser<RecordingAliasTypeBean> parser,
				@Autowired @Qualifier("recordingAliasTypeCsvToBean") CsvToBean<RecordingAliasTypeBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="recordingAliasTypeMessageHander")
	CsvMessageHandler recordingAliasTypeMessageHander
	(@Autowired @Qualifier("recordingAliasTypeIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("recordingAliasTypeReplyChannel") MessageChannel replyChannel,
			@Autowired ReleasePrimaryGroupProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}

	@Bean
	IntegrationFlow recordingAliasTypeFlowRequestHttp
	(@Autowired @Qualifier("recordingAliasTypeMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("recordingAliasTypeChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}

	@MessageEndpoint
	class ReleasePrimaryTypeGroupHandler implements MessageHandler{ 

		@Autowired
		RecordingAliasTypeRepository repository;
		
		@Autowired
		BaseTypeDescriptionRepository descRep;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			RecordingAliasTypeHolder holder = (RecordingAliasTypeHolder) message.getPayload();
			RecordingAliasType type = holder.getType();
			Optional<BaseTypeDescription> optDesc = holder.getOptDesc();
			optDesc.ifPresent(d -> {
				descRep.save(d);
				type.setDescription(d);
			});
			repository.save(type);
		}
	}

	@Bean IntegrationFlow processRecordingAliasTypeRequest
	(@Autowired ReleasePrimaryTypeGroupHandler handler , 
			@Autowired @Qualifier("recordingAliasTypeIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired RecordingAliasTypeTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class RecordingAliasTypeTransformer implements GenericTransformer<RecordingAliasTypeBean, RecordingAliasTypeHolder>{

		@Override
		public RecordingAliasTypeHolder transform(RecordingAliasTypeBean source) {
			
			RecordingAliasType type = new RecordingAliasType(source.getGid() , source.getName());
			Optional<BaseTypeDescription> optDesc = Base.NULL_VALUE(BaseTypeDescription.class);
			type.setTypeId(source.getId());
			source.setParent(source.getParent());
			source.setChildOrder(source.getChildOrder());
			if (notEmpty(source.getDescription())) { 
				optDesc = Optional.of(new BaseTypeDescription(source.getDescription()));
			}
			return new RecordingAliasTypeHolder (type, optDesc);
		} 
		
		private boolean notEmpty(String str) {
			return str !=null && !"".contentEquals(str.trim());
		} 
	}
	
	class RecordingAliasTypeHolder { 
		
		RecordingAliasType type; 
		Optional<BaseTypeDescription> optDesc;
		public RecordingAliasTypeHolder(RecordingAliasType type, Optional<BaseTypeDescription> optDesc) {
			super();
			this.type = type;
			this.optDesc = optDesc;
		}
		public RecordingAliasType getType() {
			return type;
		}
		public Optional<BaseTypeDescription> getOptDesc() {
			return optDesc;
		}
	}
	
}
