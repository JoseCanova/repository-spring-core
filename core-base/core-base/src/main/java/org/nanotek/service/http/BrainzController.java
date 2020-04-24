package org.nanotek.service.http;

import java.util.List;
import java.util.Optional;

import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.beans.entity.Recording;
import org.nanotek.proxy.map.bean.ForwardMapBean;
import org.nanotek.repository.jpa.BrainzBaseEntityRepository;
import org.nanotek.service.http.response.CollectionResponseEntity;
import org.nanotek.service.jpa.BrainzPersistenceService;
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
public class BrainzController<B extends BrainzBaseEntity<B>> 
extends BrainzPersistenceService<B>
{
	
	@Autowired
	@Qualifier("BaseSearchService")
	public  BaseSearchService<B> baseSearchService;

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
		return  CollectionResponseEntity.fromCollection(
							baseSearchService.findByEntityName(toClass(ArtistCredit.class), name), HttpStatus.OK);
	}
	
	@GetMapping(path = "/recording/name/{name}")
	@Transactional
	public CollectionResponseEntity<List<B>,B> findRecording(@PathVariable(value="name") String name) {
		return  CollectionResponseEntity.fromCollection(
							baseSearchService.findByEntityName(toClass(Recording.class), name), HttpStatus.OK);
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
	
	enum BaseFieldClassEnum{ 
		
		ARTIST_CREDIT("artistCreditId" , ArtistCredit.class),
		AREA_TYPE("typeId" , AreaType.class),
		AREA ("areaId" , Area.class),
		RECORDING("recordingId" , Recording.class);
		
		
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
