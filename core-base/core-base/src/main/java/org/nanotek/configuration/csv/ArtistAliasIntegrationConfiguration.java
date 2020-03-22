package org.nanotek.configuration.csv;

import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.nanotek.ArtistAlias;
import org.nanotek.Base;
import org.nanotek.JsonMessage;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.beans.csv.ArtistAliasBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistAliasBeginDate;
import org.nanotek.beans.entity.ArtistAliasEndDate;
import org.nanotek.beans.entity.ArtistAliasLocale;
import org.nanotek.beans.entity.ArtistAliasSortName;
import org.nanotek.beans.entity.ArtistAliasType;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.nanotek.repository.jpa.AreaRepository;
import org.nanotek.repository.jpa.ArtistAliasBeginDateRepository;
import org.nanotek.repository.jpa.ArtistAliasEndDateRepository;
import org.nanotek.repository.jpa.ArtistAliasLocaleRepository;
import org.nanotek.repository.jpa.ArtistAliasRepository;
import org.nanotek.repository.jpa.ArtistAliasSortNameRepository;
import org.nanotek.repository.jpa.ArtistAliasTypeRepository;
import org.nanotek.repository.jpa.ArtistRepository;
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
@IntegrationComponentScan(basePackageClasses = {ArtistAliasIntegrationConfiguration.class})
public class ArtistAliasIntegrationConfiguration {

	private final Logger logger = LoggerFactory.getLogger(ArtistAliasIntegrationConfiguration.class);

	@Autowired
	ArtistAliasRepository aliasRepo;
	
	@Autowired
	ArtistAliasBeginDateRepository beginDateRepository; 
	
	@Autowired
	ArtistAliasEndDateRepository endDateRepository;
	
	@Autowired
	AreaRepository areaRepository;
	
	@Autowired 
	ArtistAliasTypeRepository typeRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	ArtistAliasLocaleRepository localeRepo;
	
	@Autowired
	ArtistAliasSortNameRepository sortRep;
	
	@Autowired
	ArtistRepository artRepo;
	
	@Value("${server.port}")
	private String serverPort;

	@Bean
	@Qualifier(value="artistAliasChannel")
	MessageChannel artistAliasChannel() {
		return new DirectChannel();
	}

	@Bean
	@Qualifier(value="artistAliasReplyChannel")
	MessageChannel artistAliasReplyChannel() {
		return new DirectChannel();
	}

	@Bean(name = "artistAliasTaskExecutor")
	@Qualifier(value = "artistAliasTaskExecutor")
	public ThreadPoolTaskExecutor artistAliasTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(2500000);
		executor.setThreadNamePrefix("artistAliasTaskExecutor");
		executor.initialize();
		return executor;
	}

	@Bean
	@Qualifier(value="artistAliasIntegrationStartChannel")
	MessageChannel artistAliasIntegrationStartChannel(
			@Autowired @Qualifier("artistAliasTaskExecutor") ThreadPoolTaskExecutor executor) {
		return new ExecutorChannel(executor);
	}

	@Bean
	RequestMapping initArtistAlias() { 
		RequestMapping mapping = new RequestMapping();
		mapping.setMethods(new HttpMethod[] {HttpMethod.GET, HttpMethod.POST});
		mapping.setConsumes(MediaType.APPLICATION_JSON_VALUE);
		mapping.setProduces(MediaType.APPLICATION_JSON_VALUE);
		mapping.setPathPatterns("/initArtistAlias");
		return mapping;
	}

	@Bean
	@Qualifier("artistAliasGate")
	HttpRequestHandlingMessagingGateway artistAliasGate() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
		gateway.setRequestMapping(initArtistAlias());
		gateway.setRequestPayloadTypeClass(JsonMessage.class);
		gateway.setReplyTimeout(10000);
		gateway.setRequestChannel(artistAliasChannel());
		gateway.setReplyChannel(artistAliasReplyChannel());
		return gateway;
	}

	@Bean
	@ConfigurationProperties(prefix = "artistalias")
	@Qualifier(value="artistAliasMapStrategy")
	BaseMapColumnStrategy<ArtistAliasBean> artistAliasMapStrategy(){ 
		return new BaseMapColumnStrategy<>();
	}

	@Bean
	@Qualifier(value="artistAliasCsvToBean")
	CsvToBean<ArtistAliasBean> artistAliasCsvToBean() {
		return new CsvToBean<>();
	}

	@Bean
	@Qualifier(value="artistAliasParser")
	BaseMapParser<ArtistAliasBean> artistAliasParser() { 
		return new BaseMapParser<>(artistAliasMapStrategy());
	}


	@Service
	class ArtistAliasProcessor extends CsvBaseProcessor<ArtistAliasBean, BaseMapParser<ArtistAliasBean>>{

		public ArtistAliasProcessor
		(@Autowired @Qualifier("artistAliasParser") BaseMapParser<ArtistAliasBean> parser,
				@Autowired @Qualifier("artistAliasCsvToBean") CsvToBean<ArtistAliasBean> csvToBean) {
			super(parser, csvToBean);
		} 
	}

	@Bean
	@Qualifier(value="artistAliasMessageHander")
	CsvMessageHandler artistAliasMessageHander
	(@Autowired @Qualifier("artistAliasIntegrationStartChannel") MessageChannel integrationChannel, 
			@Autowired @Qualifier("artistAliasReplyChannel") MessageChannel replyChannel,
			@Autowired ArtistAliasProcessor processor) { 
		return new CsvMessageHandler(integrationChannel , replyChannel , processor);
	}

	@Bean
	IntegrationFlow artistAliasFlowRequestHttp
	(@Autowired @Qualifier("artistAliasMessageHander") CsvMessageHandler handler,
			@Autowired @Qualifier("artistAliasChannel") MessageChannel channel) { 
		return IntegrationFlows
				.from(channel)
				.handle(handler)
				.get();
	}

	@MessageEndpoint
	class ArtistAliasHandler implements MessageHandler{


		@Override
		public void handleMessage(Message<?> message) {
			
			ArtistAliasBean source = (ArtistAliasBean) message.getPayload();
			
			Optional<Artist> optArtist = artRepo.findByArtistId(source.getArtistId());
			
			if (!optArtist.isPresent()) {
				throw new MessagingException("Artist is Required and Not Present " + source.toString());
			}
			
			Artist artist = optArtist.get();
			
			ArtistAlias artistAlias = new ArtistAlias(source.getId(),artist,source.getName());
			
//			aliasRepo.findByAliasId(source.getId()).ifPresent(a -> {throw new MessagingException("ArtistAlias Present " + a.toString());});
			
			if(notEmpty(source.getSortName())) { 
				Base.newInstance(ArtistAliasSortName.class, 
						Arrays.array(source.getSortName()) , 
						Arrays.array(String.class)).ifPresent(s -> artistAlias.setSortName(sortRep.save(s)));
			}else {throw new MessagingException("Artist SortName Not Present " + source.toString());}
			
			
			if (source.getArtistId() == null)
				throw new MessagingException("Artist is Required and Not Present " + source.toString());
			
			Optional.ofNullable(source.getBeginDateYear())
								.ifPresent(y ->{
													ArtistAliasBeginDate entity = new ArtistAliasBeginDate(y, 
																							source.getBeginDateMonth(), 
																							source.getBeginDateDay());
													artistAlias.setArtistAliasBeginDate(beginDateRepository.save(entity));
								});


			Optional.ofNullable(source.getEndDateYear())
						.ifPresent(y ->{
											ArtistAliasEndDate endDate = new ArtistAliasEndDate(y, 
																								source.getEndDateMonth(), 
																								source.getEndDateDay());
											artistAlias.setArtistAliasEndDate(endDateRepository.save(endDate));
											
			});

			Long typeId = Optional.ofNullable(source.getType()).orElse(3l);
			
			ArtistAliasType type =  typeRepository
														.findByTypeId(typeId)
														.orElse(typeRepository.findByTypeId(3l).get());
			
			artistAlias.setArtistAliasType(type);
			
			if (notEmpty(source.getLocale())) {
				ArtistAliasLocale locale =  localeRepo.save(new ArtistAliasLocale(source.getLocale()));
				artistAlias.setArtistAliasLocale(locale);
			}
			aliasRepo.save(artistAlias);
		}

		private boolean notEmpty(String comment) {
			return comment !=null && !"".contentEquals(comment.trim());
		}
	}


	@Bean IntegrationFlow processArtistAliasRequest
	(@Autowired ArtistAliasHandler handler , 
			@Autowired @Qualifier("artistAliasIntegrationStartChannel") MessageChannel artistAliasIntegrationStartChannel) { 
		return IntegrationFlows
				.from(artistAliasIntegrationStartChannel)
				.handle(handler)
				.get();
	}
	


}