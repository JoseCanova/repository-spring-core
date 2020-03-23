package org.nanotek.service.jpa;

import org.nanotek.beans.entity.Recording;
import org.nanotek.repository.jpa.RecordingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RecordingJpaService<K extends Recording<K> , C extends RecordingRepository<K>> extends BrainzPersistenceService<K,C>{

	
	public RecordingJpaService(@Autowired @Qualifier("RecordingRepository") C rep) {
		super(rep);
	}

	
}
