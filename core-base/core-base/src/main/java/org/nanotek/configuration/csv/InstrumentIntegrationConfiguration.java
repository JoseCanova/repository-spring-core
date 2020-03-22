package org.nanotek.configuration.csv;

import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.nanotek.Base;
import org.nanotek.BaseIntegrationException;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.InstrumentBean;
import org.nanotek.beans.entity.Instrument;
import org.nanotek.beans.entity.InstrumentComment;
import org.nanotek.beans.entity.InstrumentDescription;
import org.nanotek.beans.entity.InstrumentType;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.service.jpa.InstrumentJpaService;
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
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.bean.CsvToBean;

@Configuration
@EnableIntegration
@EnableConfigurationProperties
@IntegrationComponentScan(basePackageClasses = {InstrumentIntegrationConfiguration.class})
public class InstrumentIntegrationConfiguration {

	private final Logger logger = LoggerFactory.getLogger(InstrumentIntegrationConfiguration.class);

	@Autowired
	InstrumentTypeJpaService instrumentTypeService;
	
	@Autowired
	InstrumentJpaService instrumentService;
	
	@Value("${server.port}")
	private String serverPort;

	public InstrumentIntegrationConfiguration() {
	}

	@Bean
	@Qualifier(value="instrumentChannel")
	MessageChannel instrumentChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="instrumentReplyChannel")
	MessageChannel instrumentReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="instrumentIntegrationStartChannel")
	ExecutorChannel instrumentIntegrationStartChannel(@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}


	@Bean
	@Qualifier(value="instrumentRequestMapping")
	RequestMapping instrumentRequestMapping() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/instrument");
		return mapping;
	}

	@Bean
	@Qualifier("instrumentGate")
	public HttpRequestHandlingMessagingGateway instrumentGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(instrumentRequestMapping());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(instrumentChannel());
		gateway.setReplyChannel(instrumentReplyChannel());
		return gateway;
	}


	@Bean
	@ConfigurationProperties(prefix = "instrument")
	@Qualifier(value="instrumentMapStrategy")
	BaseMapColumnStrategy<InstrumentBean> intrumentBaseMap(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="instrumentCsvToBean")
	CsvToBean<InstrumentBean> instrumentCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="instrumentParser")
	BaseMapParser<InstrumentBean> instrumentParser() { 
		return new BaseMapParser<>(intrumentBaseMap());
	}

	@Bean
	IntegrationFlow instrumentFlowRequestHttp
	(@Autowired InstrumentBeanProcessor handler,
			@Autowired @Qualifier("instrumentChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}


	@Bean IntegrationFlow processInstrumentRequest
			(@Autowired InstrumentHandler handler , 
			@Autowired @Qualifier("instrumentIntegrationStartChannel") ExecutorChannel executorChannel) { 
		return IntegrationFlows
				.from(executorChannel)
				.handle(handler)
				.get();
	}
	
	@Service
	class InstrumentProcessor extends CsvBaseProcessor<InstrumentBean, BaseMapParser<InstrumentBean>>{

		public InstrumentProcessor
		(@Autowired @Qualifier("instrumentParser") BaseMapParser<InstrumentBean> parser,
				@Autowired @Qualifier("instrumentCsvToBean") CsvToBean<InstrumentBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@MessageEndpoint
	class InstrumentBeanProcessor implements MessageHandler{ 

		@Autowired
		@Qualifier("instrumentIntegrationStartChannel")
		private ExecutorChannel dispatcherChannel;

		@Autowired
		@Qualifier("instrumentReplyChannel")
		private MessageChannel responseChannel;

		@Autowired
		private InstrumentProcessor processor;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			try { 
				processor.reopenFile();
				InstrumentBean instrument = null; 
				responseChannel.send(message);
				do{ 
					instrument = processor.next();
					if (instrument !=null) { 
						Message<?> m = MessageBuilder.withPayload(instrument).build();
						dispatcherChannel.send(m);
					}
				}while(instrument !=null);
			}catch(Exception ex) { 
				throw new MessagingException(message , ex);
			}
		}
	}


	@MessageEndpoint
	class InstrumentHandler implements MessageHandler{

		@Override
		public void handleMessage(Message<?> message){
			
			InstrumentBean source = (InstrumentBean) message.getPayload();
			
			Optional<InstrumentType> optType = Base.NULL_VALUE(InstrumentType.class);
			
			optType =  Optional
								.of(instrumentTypeService.findByTypeId(source.getType())
										.orElseThrow(BaseIntegrationException::new));
			
			Instrument instrument = Base.newInstance(Instrument.class, 
																Arrays.array(
																		source.getId() , 
																		source.getGid() , 
																		source.getName() , 
																		optType.get()),
																Arrays.array(
																		Long.class , 
																		String.class,
																		String.class,
																		InstrumentType.class)
																).map(i -> instrumentService.save(i)).get();
			if (NotEmpty(source.getComment())) {
				Base.newInstance(InstrumentComment.class, 
									Arrays.array(source.getComment()), 
									Arrays.array(String.class)).ifPresent(c -> {c.setInstrument(instrument);
																				instrumentService.saveComment(c);
																				});
			}
			if (NotEmpty(source.getDescription())) {
				Base.newInstance(
						InstrumentDescription.class , 
							Arrays.array(source.getDescription()) , 
							Arrays.array(String.class)).ifPresent(d -> {d.setInstrument(instrument); 
																	instrumentService.saveDescription(d);
																	});
			}
		}

		private boolean NotEmpty(String str) {
			return str !=null && !"".contentEquals(str.trim());
		} 
	}
}
