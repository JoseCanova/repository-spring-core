package org.nanotek.service.jpa;

import org.nanotek.beans.entity.Language;
import org.nanotek.repository.jpa.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LanguageJpaService<K extends Language<K>> {

	@Autowired
	LanguageRepository<K> repository;
	
	public LanguageJpaService() {
	}

	@Transactional
	public K save(K bean) { 
		return repository.save(bean);
	}
	
}
