package org.nanotek.service.jpa;

import java.util.Optional;

import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.repository.jpa.ArtistCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//<O extends ArtistCredit , R extends LongIdNameEntityRepository> 
@Service
public class ArtistCreditJpaService<K extends ArtistCredit<K>> {


	@Autowired ArtistCreditRepository<K> baseRepository;
	
	public ArtistCreditJpaService() {
	}

	@Transactional
	public Optional<K> findByArtistCreditId(Long artistCreditId){ 
		return baseRepository.findByArtistCreditId(artistCreditId);
	}
	
	@Transactional
//	@Cacheable(cacheNames="credits", key="#id")
	public  K findArtistCreditRecordingsById(Long id) {
		Optional<K>  opt = baseRepository.findArtistCreditRecordingsById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	public Iterable<K> findByNameContainingIgnoreCase(String name) {
		return baseRepository.findByNameContainingIgnoreCase(name);
	}

}
