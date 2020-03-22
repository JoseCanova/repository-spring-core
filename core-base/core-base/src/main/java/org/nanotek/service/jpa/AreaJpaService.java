package org.nanotek.service.jpa;

import org.nanotek.beans.entity.Area;
import org.nanotek.repository.jpa.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value="AreaJpaService")
public class AreaJpaService<B extends Area<B>, C extends AreaRepository<B>> extends BrainzPersistenceService<B,C>{
	
	
	public AreaJpaService (@Autowired  @Qualifier("AreaRepository") C rep){ 
		super(rep);
	}

	
	public Iterable<B> findByNameContainingIgnoreCase(String name) {
		return baseRepository.findByNameContainingIgnoreCase(name);
	}
	
}
