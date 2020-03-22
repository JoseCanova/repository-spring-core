package org.nanotek.service.jpa;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.Release;
import org.nanotek.repository.jpa.ReleaseRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ReleaseJpaService<O extends Release<O>> extends BrainzPersistenceService<O> implements InitializingBean{

	@Autowired  
	@Qualifier("ReleaseRepository") 
	ReleaseRepository<O> rep;
	
	public ReleaseJpaService() {
		super();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		baseRepository =  rep;
	}

	@Transactional
	public Optional<O> findByReleaseId(@Validated @Valid @NotNull Long releaseId){ 
		return rep.findByReleaseId(releaseId);
	}
}
