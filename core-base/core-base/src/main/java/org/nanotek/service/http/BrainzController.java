package org.nanotek.service.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.assertj.core.util.Objects;
import org.nanotek.Base;
import org.nanotek.IdBase;
import org.nanotek.MapBase;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.beans.entity.Label;
import org.nanotek.beans.entity.Recording;
import org.nanotek.beans.entity.Release;
import org.nanotek.proxy.map.bean.ForwardMapBean;
import org.nanotek.repository.jpa.BrainzBaseEntityRepository;
import org.nanotek.service.http.response.CollectionResponseEntity;
import org.nanotek.service.jpa.BrainzPersistenceService;
import org.nanotek.service.mq.AsyncBaseSender;
import org.nanotek.service.search.BaseSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Qualifier(value = "BrainzController")
@RequestMapping(path={"/brainz"},produces = MediaType.APPLICATION_JSON_VALUE)
@SuppressWarnings("rawtypes")
public class BrainzController<B extends BrainzBaseEntity<B>,K extends MapBase<K>> 
extends BrainzPersistenceService<B>
{
	
	@Autowired
	@Qualifier("BaseSearchService")
	public  BaseSearchService<B> baseSearchService;
	
	@Autowired
	@Qualifier("AsyncBaseSender")
	public AsyncBaseSender<K,?> baseSender;

	public BrainzController(@Autowired
			BrainzBaseEntityRepository<B> repository) {
		super(repository);
	}

	@GetMapping(path = "/artist_credit/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findArtistCredit(@PathVariable(value="id") Long  id) {
		return prepareResponse(BaseFieldClassEnum.ARTIST_CREDIT , id);
	}

	@GetMapping(path = "/artist_credit/name/{name}")
	@Transactional
	public CollectionResponseEntity<List<B>,B> findArtistCreditName(@PathVariable(value="name") String name) {
		List<B> results = baseSearchService.findByEntityName(toClass(ArtistCredit.class), name);
		return processResult(name,results,ArtistCredit.class);
	}
	
	@GetMapping(path = "/recording/name/{name}")
	@Transactional
	public CollectionResponseEntity<List<B>,B> findRecording(@PathVariable(value="name") String name) {
		List<B> results = baseSearchService.findByEntityName(toClass(Recording.class), name);
		return processResult(name,results,Recording.class);
	}
	
	@GetMapping(path = "/recording/name/{name}")
	@Transactional
	public CollectionResponseEntity<List<B>,B> findRelease(@PathVariable(value="name") String name) {
		List<B> results = baseSearchService.findByEntityName(toClass(Release.class), name);
		return processResult(name,results,Release.class);
	}
	
	@GetMapping(path = "/area_type/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findAreaType(@PathVariable(value="id") Long  id) {
		return prepareResponse(BaseFieldClassEnum.AREA_TYPE , id);
	}
	
	@GetMapping(path = "/area/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findArea(@PathVariable(value="id") Long  id) {
		return prepareResponse(BaseFieldClassEnum.AREA , id);
	}
	
	
	@GetMapping(path = "/recording/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findRecording(@PathVariable(value="id") Long  id) {
		return prepareResponse(BaseFieldClassEnum.RECORDING, id);
	}
	
	@GetMapping(path = "/label/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findLabel(@PathVariable(value="id") Long  id) {
		return prepareResponse(BaseFieldClassEnum.LABEL, id);
	}
	
	@GetMapping(path = "/release/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findRelease(@PathVariable(value="id") Long  id) {
		return prepareResponse(BaseFieldClassEnum.RELEASE, id);
	}
	
	private  <S extends B>  ResponseEntity<?> prepareResponse(BaseFieldClassEnum fieldEnum , Long id) {
		ForwardMapBean<S> fm = new ForwardMapBean<>(fieldEnum.getClazz());
		fm.write(fieldEnum.getFieldId(), id);
		return returnResponse(Example.of(fm.to()));
	}
	
	private  <S extends B> ResponseEntity<?> returnResponse(Example<S> example) {
		Optional<S> opt = findOne(example);
		HttpStatus status =  opt.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(opt,status);
	}

	
	@SuppressWarnings({ "unchecked"})
	private static <B extends BrainzBaseEntity<B>> Class<B> toClass(Class clazz){ 
		return clazz;
	}
	
	private  void processQueryResults(String query , List<?> queryResults, Class<?> class1) { 
		List<Map<String,Object>> lResult = new ArrayList<>();
		queryResults
		.forEach(
				r-> {
					IdBase<?,Long> idBase = toIdBase(r);
					Float rankResult = toRankResult(r);
					Map<String,Object> result = new HashMap<String,Object>();
					result.put("id", idBase.getId());
					result.put("rank", rankResult);
					lResult.add(result);
					System.out.println(idBase.getId() + " " + rankResult);
					}
		);
		K queryBase = toMapBase(Base.newInstance(MapBase.class).get());
		queryBase.put("query",query);
		queryBase.put("className", class1.getName());
		queryBase.put("results", lResult);
		baseSender.sendAsync(queryBase);
	}
	
	private K toMapBase(MapBase mapBase) {
		return (K)mapBase;
	}

	private Float toRankResult(Object r) {
		Float resultItem = null;
		Object[] ary = null;
		ary = Objects.castIfBelongsToType(r, Object[].class);
		if(ary !=null) {
			resultItem = (Float) ary[1];
		}
		return resultItem;
	}

	private <R extends IdBase<R,Long>> R toIdBase(Object r) {
		R resultItem = null;
		Object[] ary = null;
		ary = Objects.castIfBelongsToType(r, Object[].class);
		if(ary !=null) {
			resultItem = (R) ary[0];
		}
		return resultItem;
	}

	
	private CollectionResponseEntity<List<B>,B> processResult(String name,List<B> results, Class<?> class1) { 
		processQueryResults(name,results,class1);
		return  CollectionResponseEntity.fromCollection(
							results , HttpStatus.OK);
	}
	
	
	enum BaseFieldClassEnum{ 
		
		ARTIST_CREDIT("artistCreditId" , ArtistCredit.class),
		AREA_TYPE("typeId" , AreaType.class),
		AREA ("areaId" , Area.class),
		RECORDING("recordingId" , Recording.class),
		LABEL("labelId" , Label.class ),
		RELEASE("releaseId" , Release.class );
		
		
		private String fieldId;
		private Class<?> clazz;

		private BaseFieldClassEnum(String fieldId ,Class<?> clazz) { 
			this.fieldId = fieldId; 
			this.clazz = clazz;
		}

		public String getFieldId() {
			return fieldId;
		}

		public Class<?> getClazz() {
			return clazz;
		}
		
	}

}
