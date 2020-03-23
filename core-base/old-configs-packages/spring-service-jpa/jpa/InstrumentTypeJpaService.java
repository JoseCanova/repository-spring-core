package org.nanotek.service.jpa;

import java.util.Optional;

import org.nanotek.beans.entity.InstrumentType;
import org.nanotek.repository.jpa.InstrumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class InstrumentTypeJpaService<K extends InstrumentType<K>> {

	
	@Autowired InstrumentTypeRepository<K> baseRepository;
	
	public InstrumentTypeJpaService() {
	}

	public Optional<K> findByTypeId(Long typeId) {
		return baseRepository.findByTypeId(typeId);
	}

	public Iterable<K> findByNameContainingIgnoreCase(String name) {
		return baseRepository.findByNameContainingIgnoreCase(name);
	}

	
}
