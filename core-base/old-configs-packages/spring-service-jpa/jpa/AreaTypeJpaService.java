package org.nanotek.service.jpa;

import java.util.Optional;

import org.nanotek.beans.entity.AreaType;
import org.nanotek.repository.jpa.AreaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

//TODO: Create support for ExampleMathcers
@Service
@Validated
public class AreaTypeJpaService<K extends AreaType<K> , C extends AreaTypeRepository<K>> extends BrainzPersistenceService<K,C> implements AreaTypeRepository<K>{
	
	@Autowired 
	C baseRepository;
	
	public AreaTypeJpaService(@Autowired @Qualifier("AreaTypeRepository") C  rep) {
		super(rep);
	}

	public Iterable<K> findByNameContainingIgnoreCase( String name) {
		return baseRepository.findByNameContainingIgnoreCase(name);
	}

	public Optional<K> findByTypeId(Long typeId) {
		return baseRepository.findByTypeId(typeId);
	}

}
