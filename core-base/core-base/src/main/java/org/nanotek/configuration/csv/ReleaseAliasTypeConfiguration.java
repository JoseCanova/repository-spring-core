package org.nanotek.configuration.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.ReleaseAliasTypeBean;
import org.nanotek.beans.entity.BaseTypeDescription;
import org.nanotek.beans.entity.ReleaseAliasType;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.BaseTypeDescriptionRepository;
import org.nanotek.repository.jpa.ReleaseAliasTypeRepository;
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
@IntegrationComponentScan(basePackageClasses = {ReleaseAliasTypeConfiguration.class})
public class ReleaseAliasTypeConfiguration {

	private final Logger logger = LoggerFactory.getLogger(ReleaseAliasTypeConfiguration.class);

	@Value("${server.port}")
	private String serverPort;

	@Bean
	@Qualifier(value="releaseAliasTypeChannel")
	MessageChannel releaseAliasTypeChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releaseAliasTypeReplyChannel")
	MessageChannel releaseAliasTypeReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="releaseAliasTypeIntegrationStartChannel")
	MessageChannel releaseAliasTypeIntegrationStartChannel(
			@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}

	@Bean
	RequestMapping initReleaseAliasType() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initReleaseAliasType");
		return mapping;
	}

	@Bean
	@Qualifier("releaseAliasTypeGate")
	public HttpRequestHandlingMessagingGateway releaseAliasTypeGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initReleaseAliasType());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(releaseAliasTypeChannel());
		gateway.setReplyChannel(releaseAliasTypeReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "releasealiastype")
	@Qualifier(value="releaseAliasTypeMapStrategy")
	BaseMapColumnStrategy<ReleaseAliasTypeBean> releaseAliasTypeMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="releaseAliasTypeCsvToBean")
	CsvToBean<ReleaseAliasTypeBean> releaseAliasTypeCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="releaseAliasTypeParser")
	BaseMapParser<ReleaseAliasTypeBean> releaseAliasTypeParser() { 
		return new BaseMapParser<>(releaseAliasTypeMapStrategy());
	}

	@Service
	class ReleasePrimaryGroupProcessor extends CsvBaseProcessor<ReleaseAliasTypeBean, BaseMapParser<ReleaseAliasTypeBean>>{

		public ReleasePrimaryGroupProcessor
		(@Autowired @Qualifier("releaseAliasTypeParser") BaseMapParser<ReleaseAliasTypeBean> parser,
				@Autowired @Qualifier("releaseAliasTypeCsvToBean") CsvToBean<ReleaseAliasTypeBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="releaseAliasTypeMessageHander")
	CsvMessageHandler releaseAliasTypeMessageHander
	(@Autowired @Qualifier("releaseAliasTypeIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("releaseAliasTypeReplyChannel") MessageChannel replyChannel,
			@Autowired ReleasePrimaryGroupProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}

	@Bean
	IntegrationFlow releaseAliasTypeFlowRequestHttp
	(@Autowired @Qualifier("releaseAliasTypeMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("releaseAliasTypeChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}

	@MessageEndpoint
	class ReleasePrimaryTypeGroupHandler implements MessageHandler{ 

		@Autowired
		ReleaseAliasTypeRepository repository;

		@Autowired
		BaseTypeDescriptionRepository descRep;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			ReleaseAliasTypeHolder holder = (ReleaseAliasTypeHolder) message.getPayload();
			ReleaseAliasType type = holder.getType();
			Optional<BaseTypeDescription> optDesc = holder.getOptDesc();
			optDesc.ifPresent(d -> {
				descRep.save(d);
				type.setDescription(d);
			});
			repository.save(type);
		}
	}

	@Bean IntegrationFlow processReleaseAliasTypeRequest
	(@Autowired ReleasePrimaryTypeGroupHandler handler , 
			@Autowired @Qualifier("releaseAliasTypeIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired ReleaseAliasTypeTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}

	@MessageEndpoint
	class ReleaseAliasTypeTransformer implements GenericTransformer<ReleaseAliasTypeBean, ReleaseAliasTypeHolder>{

		@Override
		public ReleaseAliasTypeHolder transform(ReleaseAliasTypeBean source) {
			ReleaseAliasType type = new ReleaseAliasType(source.getGid() , source.getName());
			Optional<BaseTypeDescription> optDesc = Base.NULL_VALUE(BaseTypeDescription.class);
			type.setTypeId(source.getId());
			source.setParent(source.getParent());
			source.setChildOrder(source.getChildOrder());
			if (notEmpty(source.getDescription())) { 
				optDesc = Optional.of(new BaseTypeDescription(source.getDescription()));
			}
			return new ReleaseAliasTypeHolder (type, optDesc);
		} 

		private boolean notEmpty(String str) {
			return str !=null && !"".contentEquals(str.trim());
		} 

	}

	class ReleaseAliasTypeHolder { 

		ReleaseAliasType type; 
		Optional<BaseTypeDescription> optDesc;
		public ReleaseAliasTypeHolder(ReleaseAliasType type, Optional<BaseTypeDescription> optDesc) {
			super();
			this.type = type;
			this.optDesc = optDesc;
		}
		public ReleaseAliasType getType() {
			return type;
		}
		public Optional<BaseTypeDescription> getOptDesc() {
			return optDesc;
		}
	}

}
