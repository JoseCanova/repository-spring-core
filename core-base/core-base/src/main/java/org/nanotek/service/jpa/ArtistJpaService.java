package org.nanotek.service.jpa;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.nanotek.base.bean.projections.ArtistVirtualProjection;
import org.nanotek.beans.entity.Artist;
import org.nanotek.repository.jpa.ArtistRepository;
import org.nanotek.repository.jpa.projections.ArtistBaseProjection;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

//TODO: Create support for ExampleMathcers
@Service
@Validated
public class ArtistJpaService<K extends Artist<K>, C extends ArtistRepository<K>> extends BrainzPersistenceService<K,C>implements InitializingBean{

	
	@Autowired
	ArtistBaseProjection baseProjection;
	
	@Autowired
	@Qualifier("ArtistRepository")
	C artistRepository;
	
	public ArtistJpaService(@Autowired @Qualifier("ArtistRepository") C rep) {
		super(rep);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.baseRepository = artistRepository;
	}
	

	@Transactional
	public  Optional<K> findByArtistId(Long artistId) {
		return artistRepository.findByArtistId(artistId);
	}
	
	@Transactional
	public Iterable<K> findByNameContaining(@NotNull @NotEmpty String name){ 
		return artistRepository.findByNameContainingIgnoreCase(name);
	}
	
	@Transactional
	public Optional<ArtistVirtualProjection> projectArtistVirtualByArtistId(Long artistId){ 
		return baseProjection.findByArtistId(artistId);
	}
	
}
