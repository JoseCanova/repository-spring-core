package org.nanotek.service.jpa;

import java.util.Optional;

import org.nanotek.beans.entity.ReleaseGroup;
import org.nanotek.repository.jpa.ReleaseGroupRepository;
import org.nanotek.service.LongIdGidNameEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//TODO: implement Service.
@Service
public class ReleaseGroupJpaService<K extends ReleaseGroup<K>>  extends LongIdGidNameEntityService<K,ReleaseGroupRepository<K>> {

	public ReleaseGroupJpaService(@Autowired ReleaseGroupRepository<K> rep) {
		super(rep);
	}

	public Optional<K> findByReleaseGroupId(Long releaseGroupId){
		return Optional.empty();
	}

	public Iterable<K> findByNameContainingIgnoreCase(String name) {
		return null;
	}

}
