package org.nanotek.service.jpa;

import java.util.List;
import java.util.Optional;

import org.nanotek.beans.entity.ArtistCreditName;
import org.nanotek.repository.jpa.ArtistCreditNameRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArtistCreditNameJpaService
<K extends ArtistCreditName<K> , C extends ArtistCreditNameRepository<K>> 
extends BasePersistenceService<K,C>
implements InitializingBean{

	
	@Autowired  @Qualifier("ArtistCreditNameRepository") C abaseRepository;
	
	public ArtistCreditNameJpaService() {
		super();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		baseRepository = abaseRepository;
	}
	
	@Transactional
	public List<K> findByArtistCreditId(Long id){ 
		return abaseRepository.findByArtistCreditId(id);
	}
	
	@Transactional
	public Optional<K> findByArtistCreditNameId(Long id){
		return abaseRepository.findByArtistCreditNameId(id);
	}

}
