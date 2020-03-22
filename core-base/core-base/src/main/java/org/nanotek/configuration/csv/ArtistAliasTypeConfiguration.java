package org.nanotek.configuration.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.ArtistAliasTypeBean;
import org.nanotek.beans.entity.ArtistAliasType;
import org.nanotek.beans.entity.BaseTypeDescription;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.ArtistAliasTypeRepository;
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
@IntegrationComponentScan(basePackageClasses = {ArtistAliasTypeConfiguration.class})
public class ArtistAliasTypeConfiguration {

	private final Logger logger = LoggerFactory.getLogger(ArtistAliasTypeConfiguration.class);

	@Value("${server.port}")
	private String serverPort;

	@Bean
	@Qualifier(value="artistAliasTypeChannel")
	MessageChannel artistAliasTypeChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="artistAliasTypeReplyChannel")
	MessageChannel artistAliasTypeReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="artistAliasTypeIntegrationStartChannel")
	MessageChannel artistAliasTypeIntegrationStartChannel(
			@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}

	@Bean
	RequestMapping initArtistAliasType() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initArtistAliasType");
		return mapping;
	}

	@Bean
	@Qualifier("artistAliasTypeGate")
	public HttpRequestHandlingMessagingGateway artistAliasTypeGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initArtistAliasType());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(artistAliasTypeChannel());
		gateway.setReplyChannel(artistAliasTypeReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "artistaliastype")
	@Qualifier(value="artistAliasTypeMapStrategy")
	BaseMapColumnStrategy<ArtistAliasTypeBean> artistAliasTypeMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="artistAliasTypeCsvToBean")
	CsvToBean<ArtistAliasTypeBean> artistAliasTypeCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="artistAliasTypeParser")
	BaseMapParser<ArtistAliasTypeBean> artistAliasTypeParser() { 
		return new BaseMapParser<>(artistAliasTypeMapStrategy());
	}

	@Service
	class ArtistAliasTypeProcessor extends CsvBaseProcessor<ArtistAliasTypeBean, BaseMapParser<ArtistAliasTypeBean>>{

		public ArtistAliasTypeProcessor
		(@Autowired @Qualifier("artistAliasTypeParser") BaseMapParser<ArtistAliasTypeBean> parser,
				@Autowired @Qualifier("artistAliasTypeCsvToBean") CsvToBean<ArtistAliasTypeBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="artistAliasTypeMessageHander")
	CsvMessageHandler artistAliasTypeMessageHander
	(@Autowired @Qualifier("artistAliasTypeIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("artistAliasTypeReplyChannel") MessageChannel replyChannel,
			@Autowired ArtistAliasTypeProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}

	@Bean
	IntegrationFlow artistAliasTypeFlowRequestHttp
	(@Autowired @Qualifier("artistAliasTypeMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("artistAliasTypeChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}

	@MessageEndpoint
	class ArtistAliasTypeHandler implements MessageHandler{ 

		@Autowired
		ArtistAliasTypeRepository repository;

		@Autowired
		BaseTypeDescriptionRepository descRep;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			ArtistAliasTypeHolder holder = (ArtistAliasTypeHolder) message.getPayload();
			ArtistAliasType type = holder.getType();
			Optional<BaseTypeDescription> optDesc = holder.getOptDesc();
			optDesc.ifPresent(d -> {
				descRep.save(d);
				type.setDescription(d);
			});
			repository.save(type);
		}
	}

	@Bean IntegrationFlow processArtistAliasTypeRequest
	(@Autowired ArtistAliasTypeHandler handler , 
			@Autowired @Qualifier("artistAliasTypeIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired ArtistAliasTypeTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class ArtistAliasTypeTransformer implements GenericTransformer<ArtistAliasTypeBean, ArtistAliasTypeHolder>{

		@Override
		public ArtistAliasTypeHolder transform(ArtistAliasTypeBean source) {
			ArtistAliasType type = new ArtistAliasType(source.getGid() , source.getName());
			type.setTypeId(source.getId());
			type.setChildOrder(source.getChildOrder());
			type.setParent(source.getParent());
			Optional<BaseTypeDescription> optDesc = Base.NULL_VALUE(BaseTypeDescription.class);
			if (notEmpty(source.getDescription())){ 
				optDesc = Optional.of(new BaseTypeDescription(source.getDescription()));
			}
			return new ArtistAliasTypeHolder(type,optDesc);
		} 
		
		private boolean notEmpty(String str) {
			return str !=null && !"".contentEquals(str.trim());
		} 
		
	}
	
	class ArtistAliasTypeHolder{ 
		ArtistAliasType type; 
		Optional<BaseTypeDescription> optDesc;
		public ArtistAliasTypeHolder(ArtistAliasType type, Optional<BaseTypeDescription> optDesc) {
			super();
			this.type = type;
			this.optDesc = optDesc;
		}
		public ArtistAliasType getType() {
			return type;
		}
		public Optional<BaseTypeDescription> getOptDesc() {
			return optDesc;
		}
		
	}
}
