package org.nanotek.configuration.csv;

import java.util.function.Predicate;

import org.assertj.core.util.Arrays;
import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistBeginDate;
import org.nanotek.beans.entity.ArtistComment;
import org.nanotek.beans.entity.ArtistEndDate;
import org.nanotek.beans.entity.ArtistSortName;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.AreaRepository;
import org.nanotek.repository.jpa.ArtistBeginDateRepository;
import org.nanotek.repository.jpa.ArtistEndDateRespository;
import org.nanotek.repository.jpa.ArtistSortNameRepository;
import org.nanotek.repository.jpa.ArtistTypeRepository;
import org.nanotek.repository.jpa.GenderRepository;
import org.nanotek.service.CsvMessageHandler;
import org.nanotek.service.jpa.ArtistCommentJpaService;
import org.nanotek.service.jpa.ArtistJpaService;
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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.bytecode.opencsv.bean.CsvToBean;

@Configuration
@EnableIntegration
@EnableConfigurationProperties
@IntegrationComponentScan(basePackageClasses = {ArtistIntegrationConfiguration.class})
public class ArtistIntegrationConfiguration {

	private final Logger logger = LoggerFactory.getLogger(ArtistIntegrationConfiguration.class);

	@Autowired
	ArtistJpaService service;
	
	@Autowired
	ArtistCommentJpaService commentService;
	
	@Autowired
	ArtistBeginDateRepository beginDateRepository; 
	
	@Autowired
	ArtistEndDateRespository endDateRepository;
	
	@Autowired
	AreaRepository areaRepository;
	
	@Autowired 
	ArtistTypeRepository typeRepository;
	
	@Autowired
	ArtistSortNameRepository sortNameRep;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Value("${server.port}")
	private String serverPort;

	@Bean
	@Qualifier(value="artistChannel")
	MessageChannel artistChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="artistReplyChannel")
	MessageChannel artistReplyChannel() {
		return new DirectChannel();
	}

	@Bean(name = "artistTaskExecutor")
	@Qualifier(value = "artistTaskExecutor")
	public ThreadPoolTaskExecutor artistTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(2500000);
		executor.setThreadNamePrefix("artistTaskExecutor");
		executor.initialize();
		return executor;
	}

	@Bean
	@Qualifier(value="artistIntegrationStartChannel")
	MessageChannel artistIntegrationStartChannel(
			@Autowired @Qualifier("artistTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}

	@Bean
	RequestMapping initArtist() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initArtist");
		return mapping;
	}

	@Bean
	@Qualifier("artistGate")
	HttpRequestHandlingMessagingGateway artistGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initArtist());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(artistChannel());
		gateway.setReplyChannel(artistReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "artist")
	@Qualifier(value="artistMapStrategy")
	BaseMapColumnStrategy<ArtistBean> artistMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="artistCsvToBean")
	CsvToBean<ArtistBean> artistCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="artistParser")
	BaseMapParser<ArtistBean> artistParser() { 
		return new BaseMapParser<>(artistMapStrategy());
	}


	@Service
	class ArtistProcessor extends CsvBaseProcessor<ArtistBean, BaseMapParser<ArtistBean>>{

		public ArtistProcessor
		(@Autowired @Qualifier("artistParser") BaseMapParser<ArtistBean> parser,
				@Autowired @Qualifier("artistCsvToBean") CsvToBean<ArtistBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="artistMessageHander")
	CsvMessageHandler artistMessageHander
	(@Autowired @Qualifier("artistIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("artistReplyChannel") MessageChannel replyChannel,
			@Autowired ArtistProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}

	@MessageEndpoint
	class ArtistHandler implements MessageHandler{

		@Transactional
		@Override
		public void handleMessage(Message<?> message) {
			ArtistBean source = (ArtistBean) message.getPayload();
//			service.findByArtistId(source.getId()).ifPresent(a -> {throw new MessagingException("Artist Present " + source.toJson());});
			
			Artist artist = new Artist(source.getId(),source.getName(),source.getGid());

			if(NotEmpty(source.getSortName())) { 
				Base.newInstance(ArtistSortName.class, 
						Arrays.array(source.getSortName()) , 
						Arrays.array(String.class)).ifPresent(s -> artist.setSortName(sortNameRep.save(s)));
			}else {throw new MessagingException("Artist SortName Not Present " + source.toString());}
			
			if (source.getBeginDateYear() != null) {
				ArtistBeginDate beginDate = new ArtistBeginDate(source.getBeginDateYear() , source.getBeginDateMonth() , source.getBeginDateDay());
				artist.setArtistBeginDate(beginDateRepository.save(beginDate));
			}
			
			if (source.getEndDateYear()!= null) {
				ArtistEndDate endDate = new ArtistEndDate(source.getEndDateYear() , source.getEndDateMonth() , source.getEndDateDay());
				artist.setArtistEndDate(endDateRepository.save(endDate));
			}

			if (source.getGender() != null) { 
				genderRepository.findByTypeId(source.getGender()).ifPresent(g ->{
					artist.setGender(g);
				});
			}

			if (source.getBeginArea()!=null) { 
				areaRepository.findByAreaId(source.getBeginArea()).ifPresent(a -> {
					artist.setBeginArea(a);
				});
			}

			if (source.getEndArea()!=null) { 
				areaRepository.findByAreaId(source.getEndArea());
			}
			
			if (source.getType() !=null) {
				typeRepository.findByTypeId(source.getType()).ifPresent(t ->{
					artist.setType(t);
				});
			}else { 
				typeRepository.findByTypeId(3l).ifPresent(t ->{
					artist.setType(t);
				});
			}
			
			if (source.getArea()!=null) { 
				areaRepository.findByAreaId(source.getArea()).ifPresent(a -> {
					artist.setArea(a);
				});
			}
			
			if(NotEmpty(source.getComment())) { 
				Base.newInstance(ArtistComment.class, 
						Arrays.array(source.getComment()) , 
						Arrays.array(String.class)).ifPresent(c -> artist.setArtistComment(commentService.save(c)));
			}
			service.save(artist);
		}

		private boolean NotEmpty(String str) {
			return  str !=null && !"".contentEquals(str.trim());
		} 
	}

	@Service
	@Qualifier(value="ArtistBeanFilter")
	class ArtistBeanFilter implements Predicate<Message<ArtistBean>>{

		@Autowired
		ArtistJpaService service;
		
		@Override
		public boolean test(Message<ArtistBean> message ) {
			ArtistBean bean = message.getPayload();
			return service.findByArtistId(bean.getId()).isEmpty();
		} 
		
	}
	
	@Bean
	IntegrationFlow artistFlowRequestHttp
	(@Autowired @Qualifier("artistMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("artistChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}
	
	@Bean IntegrationFlow processArtistRequest
	(@Autowired ArtistHandler handler , 
			@Autowired @Qualifier("artistIntegrationStartChannel") MessageChannel executorChannel,
			@Autowired ArtistBeanFilter filter) { 
		return IntegrationFlows
				.from(executorChannel)
				.handle(handler)
				.get();
	}
}