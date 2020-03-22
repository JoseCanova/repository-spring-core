package org.nanotek.configuration.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.GenderBean;
import org.nanotek.beans.entity.BaseTypeDescription;
import org.nanotek.beans.entity.Gender;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.BaseTypeDescriptionRepository;
import org.nanotek.repository.jpa.GenderRepository;
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
@IntegrationComponentScan(basePackageClasses = {GenderIntegrationConfiguration.class})
public class GenderIntegrationConfiguration {

	private final Logger logger = LoggerFactory.getLogger(GenderIntegrationConfiguration.class);

	@Value("${server.port}")
	private String serverPort;

	@Bean
	@Qualifier(value="genderChannel")
	MessageChannel genderChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="genderReplyChannel")
	MessageChannel genderReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="genderIntegrationStartChannel")
	MessageChannel genderIntegrationStartChannel(
			@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}

	@Bean
	RequestMapping initGender() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initGender");
		return mapping;
	}

	@Bean
	@Qualifier("genderGate")
	public HttpRequestHandlingMessagingGateway genderGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initGender());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(genderChannel());
		gateway.setReplyChannel(genderReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "gendertype")
	@Qualifier(value="genderMapStrategy")
	BaseMapColumnStrategy<GenderBean> genderMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="genderCsvToBean")
	CsvToBean<GenderBean> genderCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="genderParser")
	BaseMapParser<GenderBean> genderParser() { 
		return new BaseMapParser<>(genderMapStrategy());
	}

	@Service
	class GenderProcessor extends CsvBaseProcessor<GenderBean, BaseMapParser<GenderBean>>{

		public GenderProcessor
		(@Autowired @Qualifier("genderParser") BaseMapParser<GenderBean> parser,
				@Autowired @Qualifier("genderCsvToBean") CsvToBean<GenderBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="genderMessageHander")
	CsvMessageHandler genderMessageHander
	(@Autowired @Qualifier("genderIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("genderReplyChannel") MessageChannel replyChannel,
			@Autowired GenderProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}

	@Bean
	IntegrationFlow genderFlowRequestHttp
	(@Autowired @Qualifier("genderMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("genderChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}

	@MessageEndpoint
	class GenderHandler implements MessageHandler{ 

		@Autowired
		GenderRepository repository;
		
		@Autowired
		BaseTypeDescriptionRepository descRep;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			GenderHolder holder = (GenderHolder) message.getPayload();
			Gender type = holder.getGender();
			Optional<BaseTypeDescription> optDesc = holder.getOptDesc();
			optDesc.ifPresent(d -> {
				descRep.save(d);
				type.setDescription(d);
			});
			repository.save(type);
		}
	}

	@Bean IntegrationFlow processGenderRequest
	(@Autowired GenderHandler handler , 
			@Autowired @Qualifier("genderIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired GenderTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
	@MessageEndpoint
	class GenderTransformer implements GenericTransformer<GenderBean, GenderHolder>{

		@Override
		public GenderHolder transform(GenderBean source) {
			Gender type = new Gender(source.getGid() , source.getName());
			Optional<BaseTypeDescription> optDesc = Base.NULL_VALUE(BaseTypeDescription.class);
			type.setTypeId(source.getId());
			source.setParent(source.getParent());
			source.setChildOrder(source.getChildOrder());
			if (notEmpty(source.getDescription())) { 
				optDesc = Optional.of(new BaseTypeDescription(source.getDescription()));
			}
			return new GenderHolder (type, optDesc);
		} 

		private boolean notEmpty(String str) {
			return str !=null && !"".contentEquals(str.trim());
		}  
		
	}
	
	class GenderHolder{ 
		Gender gender; 
		Optional<BaseTypeDescription> optDesc;
		public GenderHolder(Gender gender, Optional<BaseTypeDescription> optDesc) {
			super();
			this.gender = gender;
			this.optDesc = optDesc;
		}
		public Gender getGender() {
			return gender;
		}
		public Optional<BaseTypeDescription> getOptDesc() {
			return optDesc;
		}
	}
	
}
