package org.nanotek.service.jpa;

import org.nanotek.beans.entity.Medium;
import org.nanotek.repository.jpa.MediumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class MediumJpaService<K extends Medium<K>> 
 {

	@Autowired MediumRepository<K> baseRepository;
	
	public MediumJpaService() {
	}

	public Iterable<K> findByNameContainingIgnoreCase(String name) {
		return baseRepository.findByNameContainingIgnoreCase(name);
	}

}
