package org.nanotek.service.jpa;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.Instrument;
import org.nanotek.beans.entity.InstrumentComment;
import org.nanotek.beans.entity.InstrumentDescription;
import org.nanotek.repository.jpa.InstrumentCommentRepository;
import org.nanotek.repository.jpa.InstrumentDescriptionRepository;
import org.nanotek.repository.jpa.InstrumentRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class InstrumentJpaService<K extends Instrument<K>, C extends InstrumentComment<C> , D extends InstrumentDescription<D,?>> 
extends BasePersistenceService<K> implements InitializingBean{

	@Autowired InstrumentRepository<K> abaseRepository;
	
	@Autowired
	InstrumentCommentRepository<C> commentRepository;
	
	//TODO: verify why description is not generic.
	@Autowired
	InstrumentDescriptionRepository<D> descriptionRepository;
	
	public InstrumentJpaService() {
		super();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.baseRepository = abaseRepository;
	}
	
	public InstrumentJpaService(@Autowired InstrumentRepository<K> baseRepository) {
		super();
	}

	
	@Transactional
	public Optional<K> findByInstrumentId(@Validated @Valid @NotNull Long instrumentId){ 
		return abaseRepository.findByInstrumentId(instrumentId);
	}
	
	@Transactional
	public C saveComment(@Validated @Valid @NotBlank C entity) { 
		return commentRepository.save(entity);
	}
	
	@Transactional
	public D saveDescription(@Validated @Valid @NotBlank D entity) { 
		return descriptionRepository.save(entity);
	}
}
