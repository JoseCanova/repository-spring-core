package org.nanotek.service.jpa;

import javax.transaction.Transactional;

import org.nanotek.beans.entity.RecordingLength;
import org.nanotek.repository.jpa.RecordingLenghtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordingLengthJpaService {

	@Autowired
	RecordingLenghtRepository repository;
	
	@Transactional
	public void save(RecordingLength entity) { 
		repository.save(entity);
	}
	
}
