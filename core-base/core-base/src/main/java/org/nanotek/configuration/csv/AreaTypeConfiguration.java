package org.nanotek.configuration.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.AreaTypeBean;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.BaseTypeDescription;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.AreaTypeRepository;
import org.nanotek.repository.jpa.BaseTypeDescriptionRepository;
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
@IntegrationComponentScan(basePackageClasses = {AreaTypeConfiguration.class})
public class AreaTypeConfiguration {

	private final Logger logger = LoggerFactory.getLogger(AreaTypeConfiguration.class);

	@Value("${server.port}")
	private String serverPort;

	@Bean
	@Qualifier(value="areaTypeChannel")
	MessageChannel areaTypeChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="areaTypeReplyChannel")
	MessageChannel areaTypeReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="areaTypeIntegrationStartChannel")
	MessageChannel areaTypeIntegrationStartChannel(
			@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}

	@Bean
	RequestMapping initAreaType() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initAreaType");
		return mapping;
	}

	@Bean
	@Qualifier("areaTypeGate")
	public HttpRequestHandlingMessagingGateway areaTypeGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initAreaType());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(areaTypeChannel());
		gateway.setReplyChannel(areaTypeReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "areatype")
	@Qualifier(value="areaTypeMapStrategy")
	BaseMapColumnStrategy<AreaTypeBean> areaTypeMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="areaTypeCsvToBean")
	CsvToBean<AreaTypeBean> areaTypeCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="areaTypeParser")
	BaseMapParser<AreaTypeBean> areaTypeParser() { 
		return new BaseMapParser<>(areaTypeMapStrategy());
	}

	@Service
	class AreaTypeProcessor extends CsvBaseProcessor<AreaTypeBean, BaseMapParser<AreaTypeBean>>{

		public AreaTypeProcessor
		(@Autowired @Qualifier("areaTypeParser") BaseMapParser<AreaTypeBean> parser,
				@Autowired @Qualifier("areaTypeCsvToBean") CsvToBean<AreaTypeBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="areaTypeMessageHander")
	CsvMessageHandler areaTypeMessageHander
	(@Autowired @Qualifier("areaTypeIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("areaTypeReplyChannel") MessageChannel replyChannel,
			@Autowired AreaTypeProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}

	@Bean
	IntegrationFlow areaTypeFlowRequestHttp
	(@Autowired @Qualifier("areaTypeMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("areaTypeChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}

	@MessageEndpoint
	class AreaTypeHandler implements MessageHandler{ 

		@Autowired
		AreaTypeRepository repository;

		@Autowired
		BaseTypeDescriptionRepository descRep;
		
		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			AreaTypeHolder typeHolder = (AreaTypeHolder) message.getPayload();
			AreaType type = typeHolder.getAreaType();
			Optional<BaseTypeDescription> optDesc = typeHolder.getOptDescription();
			optDesc.ifPresent(d -> {
				descRep.save(d);
				type.setDescription(d);
			});
			repository.save(type);
		}
	}

	@Bean IntegrationFlow processAreaTypeRequest
	(@Autowired AreaTypeHandler handler , 
			@Autowired @Qualifier("areaTypeIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired AreaTypeTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class AreaTypeTransformer implements GenericTransformer<AreaTypeBean, AreaTypeHolder>{

		@Override
		public AreaTypeHolder transform(AreaTypeBean source) {
			
			AreaType areaType = new AreaType(source.getGid() , source.getName());
			areaType.setParent(source.getParent());
			areaType.setTypeId(source.getId());
			areaType.setParent(source.getParent());
			Optional<BaseTypeDescription> typeDescription = Base.NULL_VALUE(BaseTypeDescription.class);
			
			if (notEmpty(source.getDescription())) { 
				typeDescription = Optional.of(new BaseTypeDescription(source.getDescription()));
			}
			return new AreaTypeHolder(areaType, typeDescription);
		}
		private boolean notEmpty(String str) {
			return str !=null && !"".contentEquals(str.trim());
		} 
	}
	
	class AreaTypeHolder{
		
		AreaType areaType; 
		Optional<BaseTypeDescription> optDescription;

		public AreaTypeHolder(AreaType areaType, Optional<BaseTypeDescription> optDescription) {
			super();
			this.areaType = areaType;
			this.optDescription = optDescription;
		}

		public AreaType getAreaType() {
			return areaType;
		}

		public Optional<BaseTypeDescription> getOptDescription() {
			return optDescription;
		}
	}
}
