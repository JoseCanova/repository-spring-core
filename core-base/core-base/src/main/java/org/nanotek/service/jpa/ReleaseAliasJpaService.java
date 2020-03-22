package org.nanotek.service.jpa;

import java.util.Optional;

import org.nanotek.beans.entity.ReleaseAlias;
import org.nanotek.repository.jpa.ReleaseAliasJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseAliasJpaService {

	@Autowired 
	ReleaseAliasJpaRepository rep; 
	
	public ReleaseAliasJpaService(ReleaseAliasJpaRepository rep) {
		super();
	}
	
    public Optional<ReleaseAlias> findByReleaseAliasId(Long id){ 
    	return rep.findByReleaseAliasId(id);
    }

}
