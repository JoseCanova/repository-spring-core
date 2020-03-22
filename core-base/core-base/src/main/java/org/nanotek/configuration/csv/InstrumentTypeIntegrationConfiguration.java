package org.nanotek.configuration.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.InstrumentTypeBean;
import org.nanotek.beans.entity.BaseTypeDescription;
import org.nanotek.beans.entity.InstrumentType;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.BaseTypeDescriptionRepository;
import org.nanotek.service.jpa.InstrumentTypeJpaService;
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
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import au.com.bytecode.opencsv.bean.CsvToBean;

@Configuration
@EnableIntegration
@EnableConfigurationProperties
@IntegrationComponentScan(basePackageClasses = {InstrumentTypeIntegrationConfiguration.class})
public class InstrumentTypeIntegrationConfiguration {

	
	private final Logger logger = LoggerFactory.getLogger(InstrumentTypeIntegrationConfiguration.class);

	
	@Value("${server.port}")
	private String serverPort;

	@Value("${csv-endpoint.instrument-type}${csv-endpoint.next}")
	private String nextEndpoint;
	
	public InstrumentTypeIntegrationConfiguration() {
	}
	
	@Bean
	@Qualifier(value="baseRestTemplate")
	RestTemplate baseRestTemplate() { 
		RestTemplate restTemplate = new RestTemplate(); 
		return restTemplate;
	}
	
	@Bean
	HttpRequestExecutingMessageHandler csvNextMessageHandler
	(@Autowired @Qualifier("baseRestTemplate") RestTemplate baseTemplate) {
		String uriLocation = new StringBuilder()
				.append("http://127.0.0.1")
				.append(":")
				.append(serverPort)
				.append(nextEndpoint).toString();
		return new HttpRequestExecutingMessageHandler(uriLocation , baseTemplate);
	}
	
	@Bean
    RequestMapping mapping() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/init");
		return mapping;
    }
    
	@Bean
	@Qualifier(value="startChannel")
	MessageChannel startChannel() {
		return new DirectChannel();
	}
	
	@Bean
	@Qualifier(value="responseChannel")
	MessageChannel responseChannel() {
		return new DirectChannel();
	}
	
	@Bean
	@Qualifier(value="integrationStartChannel")
	ExecutorChannel integrationStartChannel(@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}
	
    @Bean
    @Qualifier("httpGate")
	public HttpRequestHandlingMessagingGateway httpGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(mapping());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(startChannel());
		gateway.setReplyChannel(responseChannel());
		return gateway;
	}

    @Bean
	public IntegrationFlow flowRequestHttp(@Autowired InstrumentTypeBeanProcessor handler) { 
		return IntegrationFlows
				.from("startChannel")
				.handle(handler)
				.get();
	}
    
    @Bean IntegrationFlow processRequest
    		(@Autowired InstrumentTypeHandler handler , 
    				@Autowired @Qualifier("integrationStartChannel") ExecutorChannel executorChannel,
    				@Autowired InstrumentTypeTransformer transformer) { 
    	return IntegrationFlows
    			.from(executorChannel)
    			.transform(transformer)
    			.handle(handler)
    			.get();
    }
    
    @Bean
	@ConfigurationProperties(prefix = "instrumenttype")
    @Qualifier(value="instypeMapStrategy")
	BaseMapColumnStrategy<InstrumentTypeBean> intrumentTypeBaseMap(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="insCsvToBean")
	CsvToBean<InstrumentTypeBean> instrumentTypeCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="instTypeParser")
	BaseMapParser<InstrumentTypeBean> parser() { 
		return new BaseMapParser<>(intrumentTypeBaseMap());
	}
	
	@Service
	class IntrumentTypeProcessor extends CsvBaseProcessor<InstrumentTypeBean, BaseMapParser<InstrumentTypeBean>>{

		public IntrumentTypeProcessor
				(@Autowired @Qualifier("instTypeParser") BaseMapParser<InstrumentTypeBean> parser,
				 @Autowired @Qualifier("insCsvToBean") CsvToBean<InstrumentTypeBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}
	
    @MessageEndpoint
    class InstrumentTypeBeanProcessor implements MessageHandler{ 
    
    	@Autowired
    	@Qualifier("integrationStartChannel")
    	private ExecutorChannel dispatcherChannel;
    	
    	@Autowired
    	@Qualifier("responseChannel")
    	private MessageChannel responseChannel;
    	
    	@Autowired
    	private IntrumentTypeProcessor processor;
    	
		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			try { 
			processor.reopenFile();
			InstrumentTypeBean itBean = null; 
			responseChannel.send(message);
				do{ 
					itBean = processor.next();
					if (itBean !=null) { 
						Message<?> m = MessageBuilder.withPayload(itBean).build();
						dispatcherChannel.send(m);
					}
				}while(itBean !=null);
			}catch(Exception ex) { 
				throw new MessagingException(message , ex);
			}
		}
    }
    
    @MessageEndpoint
    class InstrumentTypeHandler implements MessageHandler{ 

    	@Autowired
    	InstrumentTypeJpaService jpaService;
    	
    	@Autowired
		BaseTypeDescriptionRepository descRep;
    	
		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			
			InstrumentTypeHolder holder = (InstrumentTypeHolder) message.getPayload();
			InstrumentType type = holder.getType();
			Optional<BaseTypeDescription> optDesc = holder.getOptDesc();
			optDesc.ifPresent(d -> {
				descRep.save(d);
				type.setDescription(d);
			});
			jpaService.save(type);
			
		}
    }
    
    @MessageEndpoint
    class InstrumentTypeTransformer implements GenericTransformer<InstrumentTypeBean  , InstrumentTypeHolder> {
    	
		@Override
		public InstrumentTypeHolder transform(InstrumentTypeBean source) {
			
			InstrumentType type = new InstrumentType(source.getGid() , source.getName());
			Optional<BaseTypeDescription> optDesc = Base.NULL_VALUE(BaseTypeDescription.class);
			type.setTypeId(source.getId());
			source.setParent(source.getParent());
			source.setChildOrder(source.getChildOrder());
			if (notEmpty(source.getDescription())) { 
				optDesc = Optional.of(new BaseTypeDescription(source.getDescription()));
			}
			return new InstrumentTypeHolder (type, optDesc);
		}
		
		private boolean notEmpty(String str) {
			return str !=null && !"".contentEquals(str.trim());
		} 
    }
    
    class InstrumentTypeHolder{ 
    	InstrumentType type; 
    	Optional<BaseTypeDescription> optDesc;
		public InstrumentTypeHolder(InstrumentType type, Optional<BaseTypeDescription> optDesc) {
			super();
			this.type = type;
			this.optDesc = optDesc;
		}
		public InstrumentType getType() {
			return type;
		}
		public Optional<BaseTypeDescription> getOptDesc() {
			return optDesc;
		} 
    }
}