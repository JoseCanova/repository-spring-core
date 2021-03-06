package org.nanotek.service.jpa;

import java.util.Optional;

import org.nanotek.beans.entity.ArtistSortName;
import org.nanotek.repository.jpa.ArtistSortNameRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ArtistSortNameJpaService<K extends ArtistSortName<K> , C extends ArtistSortNameRepository<K>> extends BrainzPersistenceService<K,C>implements InitializingBean{

	
	@Autowired @Qualifier("ArtistSortNameRepository") C artistSortNameRepository;
	
	public ArtistSortNameJpaService() {
		super();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.baseRepository = artistSortNameRepository;
	}
	
	
	public Optional<K> findById(Long k) {
		return baseRepository.findById(k);
	}

	public Iterable<K> findByNameContaining(String name){
		return artistSortNameRepository.findBySortNameContainingIgnoreCase(name);
	}

}
