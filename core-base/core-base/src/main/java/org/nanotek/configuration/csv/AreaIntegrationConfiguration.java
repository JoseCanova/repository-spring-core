package org.nanotek.configuration.csv;

import java.io.Serializable;
import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.IdBase;
import org.nanotek.JsonMessage;
import org.nanotek.Kong;
import org.nanotek.Result;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.AreaBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.AreaEndDate;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.SequenceLongBase;
import org.nanotek.opencsv.BaseMap;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.AreaBeginDateRepository;
import org.nanotek.repository.jpa.AreaCommentRepository;
import org.nanotek.repository.jpa.AreaEndDateRepository;
import org.nanotek.repository.jpa.AreaRepository;
import org.nanotek.repository.jpa.SequenceLongBaseRepository;
import org.nanotek.service.jpa.AreaJpaService;
import org.nanotek.service.jpa.AreaTypeJpaService;
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
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.bean.CsvToBean;

@SuppressWarnings("rawtypes")
@Configuration
@EnableIntegration
@EnableConfigurationProperties
@IntegrationComponentScan(basePackageClasses = {AreaIntegrationConfiguration.class})
public class AreaIntegrationConfiguration<K extends SequenceLongBaseRepository<K,B>, B extends SequenceLongBase<B,Long>> {

	
	@Autowired
	AreaJpaService service;
	
	@Autowired
	SequenceLongBaseRepository<?,?> idRep;
	
	@Autowired
	AreaEndDateRepository<?> eRep;
	
	@Autowired
	AreaCommentRepository cRep;
	
			
	private final Logger logger = LoggerFactory.getLogger(AreaIntegrationConfiguration.class);

	@Value("${server.port}")
	private String serverPort;

	public AreaIntegrationConfiguration() {
	}

	@Bean
	@Qualifier(value="areaChannel")
	MessageChannel areaChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="areaReplyChannel")
	MessageChannel areaReplyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="areaIntegrationStartChannel")
	ExecutorChannel areaIntegrationStartChannel(@Autowired @Qualifier("serviceTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}


	@Bean
	@Qualifier(value="areaRequestMapping")
	RequestMapping areaRequestMapping() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/area");
		return mapping;
	}

	@Bean
	@Qualifier("areaGate")
	public HttpRequestHandlingMessagingGateway areaGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(areaRequestMapping());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(areaChannel());
		gateway.setReplyChannel(areaReplyChannel());
		return gateway;
	}


	@Bean
	@ConfigurationProperties(prefix = "area")
	@Qualifier(value="areaMapStrategy")
	<K extends BaseMap<?,ID> , ID extends IdBase<ID,?>>BaseMapColumnStrategy<K,ID> areaMapStrategy(){ 
		return   Base.newInstance(BaseMapColumnStrategy.class.asSubclass(BaseMapColumnStrategy.class)).get();
	}

	@Bean
	@Qualifier(value="areaCsvToBean")
	CsvToBean<AreaBean> areaCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="areaParser")
	BaseMapParser<?,?> areaParser() { 
		return new BaseMapParser<>();
	}

	@Bean
	IntegrationFlow areaFlowRequestHttp
	(@Autowired AreaBeanProcessor handler,
			@Autowired @Qualifier("areaChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}


	@Bean IntegrationFlow processAreaRequest
			(@Autowired AreaHandler handler , 
			@Autowired @Qualifier("areaIntegrationStartChannel") ExecutorChannel executorChannel,
			@Autowired AreaTransformer transformer) { 
		return IntegrationFlows
				.from(executorChannel)
				.transform(transformer)
				.handle(handler)
				.get();
	}
	
	@Service
	class AreaProcessor<A,B,C> extends CsvBaseProcessor{

		@SuppressWarnings("unchecked")
		public AreaProcessor
		(@Autowired @Qualifier("areaParser") BaseMapParser<?,?> parser,
				@Autowired @Qualifier("areaCsvToBean") CsvToBean<AreaBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@MessageEndpoint
	class AreaBeanProcessor implements MessageHandler{ 

		@Autowired
		@Qualifier("areaIntegrationStartChannel")
		private ExecutorChannel dispatcherChannel;

		@Autowired
		@Qualifier("areaReplyChannel")
		private MessageChannel responseChannel;

		@Autowired
		private AreaProcessor processor;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			try { 
				processor.reopenFile();
				Result<?,?> area = null; 
				responseChannel.send(message);
				do{ 
					area = processor.next();
					if (area !=null) { 
						Message<?> m = MessageBuilder.withPayload(area).build();
						dispatcherChannel.send(m);
					}
				}while(area !=null);
			}catch(Exception ex) { 
				throw new MessagingException(message , ex);
			}
		}
	}


	@MessageEndpoint
	class AreaHandler<S extends Area<S>,C extends AreaComment<C>> implements MessageHandler{ 
		

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			AreaHolder<S,C> holder = (AreaHolder<S,C>) message.getPayload();
			S transientArea = holder.getArea();
			if (transientArea.getAreaBeginDate() !=null) {
				AreaBeginDate<?> bd = idRep.save(transientArea.getAreaBeginDate());
				transientArea.setAreaBeginDate(bd);
			}
			if(transientArea.getAreaEndDate() !=null) {
				AreaEndDate ed = eRep.save(transientArea.getAreaEndDate());
				transientArea.setAreaEndDate(ed);
			}
			AreaComment areaComment = holder.getAreaComment();
			if (areaComment !=null) {
				transientArea.setAreaComment(cRep.save(areaComment));
			}
			service.save(transientArea);
		}
	}

	@MessageEndpoint
	class AreaTransformer implements GenericTransformer<AreaBean  , AreaHolder>{

		@Autowired
		AreaTypeJpaService service;
		
		@Autowired
		AreaRepository areaRep;
		
		@SuppressWarnings("unchecked")
@Override
		public AreaHolder transform(AreaBean source) {
			Area<?> area = null;
			Optional <Area<?>> optArea = areaRep.findByAreaId(source.getAreaId());
			if(source.getType() == null)
				throw new MessagingException("No Areatype found for bean " + source.toJson());
			Optional<AreaType<?>> optType = service.findByTypeId(source.getType());
			
			
			if (optArea.isPresent()) 
				area = optArea.get();
			 else
				 area = new Area(source.getId(),source.getName(),source.getGid());
			area.setType(optType.get());
			if (source.getBeginDateYear() !=null) { 
				AreaBeginDate eDate  = new AreaBeginDate(source.getBeginDateYear(), source.getBeginDateMonth(), source.getBeginDateDay());
				area.setAreaBeginDate(eDate);
			}
			if (source.getEndDateYead() !=null) { 
				AreaEndDate eDate = new AreaEndDate(source.getEndDateYead(),source.getEndDateMonth(),source.getEndDateDay());
				area.setAreaEndDate(eDate);
			}
			AreaComment areaComment = null;
			if (NotEmpty(source.getComment())) { 
				areaComment = new AreaComment(source.getComment());
			}
			return new AreaHolder(area,areaComment);
		}

		private boolean NotEmpty(String comment) {
			return comment !=null && !"".contentEquals(comment.trim());
		} 
	}
	
	class AreaHolder< S extends Area<S> , C extends AreaComment<C>>  implements Serializable{ 
		
		private static final long serialVersionUID = -544891655711717670L;

		private S area; 
		
		private C areaComment;

		public AreaHolder(S area, C areaComment) {
			super();
			this.area = area;
			this.areaComment = areaComment;
		}

		public S getArea() {
			return area;
		}

		public C getAreaComment() {
			return areaComment;
		}
		
	}

}
