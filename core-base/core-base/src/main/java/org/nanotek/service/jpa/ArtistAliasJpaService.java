package org.nanotek.service.jpa;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.ArtistAlias;
import org.nanotek.repository.jpa.ArtistAliasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistAliasJpaService<K extends ArtistAlias<K>>  {
	
	
	@Autowired
	ArtistAliasRepository<K> baseRepository;
	
	public ArtistAliasJpaService() {
	}

	@Transactional
	public Optional<K> findByAliasId(@NotNull Long aliasId) {
		return baseRepository.findByAliasId(aliasId);
	}

	public Iterable<K> findByNameContainingIgnoreCase( String name) {
		return baseRepository.findByNameContainingIgnoreCase(name);
	}

}
