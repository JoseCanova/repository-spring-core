package org.nanotek.service.jpa;

import org.nanotek.beans.entity.Recording;
import org.nanotek.repository.jpa.RecordingRepository;
import org.nanotek.service.IdBasePersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordingJpaService<K extends Recording<K>> extends BrainzPersistenceService<K>{

	
	public RecordingJpaService(@Autowired RecordingRepository<K> rep) {
		super(rep);
	}

	
}
