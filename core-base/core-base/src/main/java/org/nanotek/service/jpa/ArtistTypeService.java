package org.nanotek.service.jpa;

import java.util.Optional;

import org.nanotek.beans.entity.ArtistType;
import org.nanotek.repository.jpa.ArtistTypeRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//TODO: implements the transactional.
@Service
public class ArtistTypeService<K extends ArtistType<K>> extends BasePersistenceService<K>  implements InitializingBean{

	@Autowired ArtistTypeRepository<K> abaseRepository;
	
	public ArtistTypeService() { 
		super();
	}

	@Override
	@Transactional
	public Optional<K> findById(Long k) {
		return baseRepository.findById(k);
	} 
	
	@Override
	public void afterPropertiesSet() throws Exception {
		baseRepository = abaseRepository;
	}
	
	@Transactional
	public Iterable<K> findByNameContainingIgnoreCase(String name){ 
		return abaseRepository.findByNameContainingIgnoreCase(name);
	}

	@Transactional
	public Optional<K> findByTypeId(Long id) {
		return abaseRepository.findByTypeId(id);
	}
		
}
