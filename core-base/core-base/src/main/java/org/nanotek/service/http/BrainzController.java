package org.nanotek.service.http;

import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.repository.jpa.BrainzBaseEntityRepository;
import org.nanotek.service.jpa.BrainzPersistenceService;
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

	public BrainzController(@Autowired
			BrainzBaseEntityRepository<B> repository) {
		super(repository);
	}

	@GetMapping(path = "/artist_credit/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findArtistCredit(@PathVariable(value="id") Long  id) {
		ArtistCredit<?> ac = new ArtistCredit<>(); 
		ac.setArtistCreditId(id);
		Example<S> example = (Example<S>) Example.of(ac);
		S opt = findByExample(example);
		HttpStatus status =  opt !=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(opt,status);
	}
	
	@GetMapping(path = "/area_type/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findAreaType(@PathVariable(value="id") Long  id) {
		AreaType<?> type = new AreaType<>(); 
		type.setTypeId(id);
		Example<S> example = (Example<S>) Example.of(type);
		S opt = findByExample(example);
		HttpStatus status =  opt !=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(opt,status);
	}
	
	@GetMapping(path = "/area/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findArea(@PathVariable(value="id") Long  id) {
		Area<?> area = new Area<>(); 
		area.setAreaId(id);
		Example<S> example = (Example<S>) Example.of(area);
		S opt = findByExample(example);
		HttpStatus status =  opt !=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(opt,status);
	}
	
	
	

}
