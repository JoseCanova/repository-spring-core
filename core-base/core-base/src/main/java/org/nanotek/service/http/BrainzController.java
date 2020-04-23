package org.nanotek.service.http;

import java.util.List;
import java.util.Optional;

import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.repository.jpa.BrainzBaseEntityRepository;
import org.nanotek.service.http.response.CollectionResponseEntity;
import org.nanotek.service.http.response.ResponseBase;
import org.nanotek.service.jpa.BrainzPersistenceService;
import org.nanotek.service.search.BaseSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Qualifier(value = "BrainzController")
@RequestMapping(path={"/brainz"})
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
	public <S extends B> ResponseBase<S> findArtistCredit(@PathVariable(value="id") Long  id) {
		ArtistCredit<?> ac = new ArtistCredit<>(); 
		ac.setArtistCreditId(id);
		Example<S> example = Example.of(to(ac));
		return returnResponse(example);
	}

	@GetMapping(path = "/artist_credit/name/{name}")
	@Transactional
	public CollectionResponseEntity<List<B>,B> findArtistCreditName(@PathVariable(value="name") String name) {
		return  CollectionResponseEntity.fromCollection(
							baseSearchService.findByEntityName(toClass(ArtistCredit.class), name), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <B extends BrainzBaseEntity<B>> Class<B> toClass(Class clazz){ 
		return clazz;
	}
	
	@GetMapping(path = "/area_type/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findAreaType(@PathVariable(value="id") Long  id) {
		AreaType<?> type = new AreaType<>(); 
		type.setTypeId(id);
		Example<S> example = Example.of(to(type));
		return returnResponse(example);
	}
	
	@GetMapping(path = "/area/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findArea(@PathVariable(value="id") Long  id) {
		Area<?> area = new Area<>(); 
		area.setAreaId(id);
		Example<S> example = Example.of(to(area));
		return returnResponse(example);
	}
	
	private <S extends B> ResponseBase<S> returnResponse(Example<S> example) {
		Optional<S> opt = findOne(example);
		HttpStatus status =  opt.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseBase.fromEntity(opt,status);
	}
	
	@SuppressWarnings("unchecked")
	private static <S extends B, B> S to(B instance){ 
		return (S)instance;
	}
	

}
