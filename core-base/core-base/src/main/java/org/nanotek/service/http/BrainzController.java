package org.nanotek.service.http;

import java.util.List;
import java.util.Optional;

import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.proxy.map.bean.ForwardMapBean;
import org.nanotek.repository.jpa.BrainzBaseEntityRepository;
import org.nanotek.service.http.response.CollectionResponseEntity;
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
		ForwardMapBean<S> fm = new ForwardMapBean<>(ArtistCredit.class);
		fm.write("artistCreditId", id);
		return returnResponse(Example.of(fm.to()));
	}

	@GetMapping(path = "/artist_credit/name/{name}")
	@Transactional
	public CollectionResponseEntity<List<B>,B> findArtistCreditName(@PathVariable(value="name") String name) {
		return  CollectionResponseEntity.fromCollection(
							baseSearchService.findByEntityName(toClass(ArtistCredit.class), name), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked"})
	private static <B extends BrainzBaseEntity<B>> Class<B> toClass(Class clazz){ 
		return clazz;
	}
	
	@GetMapping(path = "/area_type/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findAreaType(@PathVariable(value="id") Long  id) {
		ForwardMapBean<S> fm = new ForwardMapBean<>(AreaType.class);
		fm.write("typeId", id);
		return returnResponse(Example.of(fm.to()));
	}
	
	@GetMapping(path = "/area/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findArea(@PathVariable(value="id") Long  id) {
		ForwardMapBean<S> fm = new ForwardMapBean<>(Area.class);
		fm.write("areaId", id);
		Example<S> example = Example.of(fm.to());
		return returnResponse(example);
	}
	
	private  <S extends B> ResponseEntity<?> returnResponse(Example<S> example) {
		Optional<S> opt = findOne(example);
		HttpStatus status =  opt.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(opt,status);
	}

	
	private ResponseEntity<?> returnResponse(Optional<?> opt) {
		HttpStatus status =  opt.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(opt,status);
	}

}
