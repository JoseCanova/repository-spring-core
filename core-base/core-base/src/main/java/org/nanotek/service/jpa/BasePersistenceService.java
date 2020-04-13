package org.nanotek.service.jpa;

import java.util.List;
import java.util.Optional;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.repository.jpa.BrainzBaseEntityRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class BasePersistenceService<B extends BrainzBaseEntity<B>, C extends BrainzBaseEntityRepository<B>>{

	public C  baseRepository;
	
	public BasePersistenceService() {
		super();
	}

	public BasePersistenceService(C  baseRepository) {
		super();
		this.baseRepository = baseRepository;
	}

	public List<B> findAll() {
		return baseRepository.findAll();
	}

	
	public List<B> findAll(Sort sort) {
		return baseRepository.findAll(sort);
	}

	
	public List<B> findAllById(Iterable<Long> ids) {
		return baseRepository.findAllById(ids);
	}

	
	public void flush() {
		baseRepository.flush();
	}

	

	public <S extends B> S saveAndFlush(S entity) {
		return baseRepository.saveAndFlush(entity);
	}

	public void deleteInBatch(Iterable<B> entities) {
		baseRepository.deleteInBatch(entities);
	}

	
	public void deleteAllInBatch() {
		baseRepository.deleteAllInBatch();
	}

	
	public <S extends B> S save(S entity) {
			return baseRepository.save(entity);
		}

//	public <S extends B> B save(S entity) {
//		return baseRepository.save(entity);
//	}


	public Page<B> findAll(Pageable pageable) {
		return baseRepository.findAll(pageable);
	}

	public Optional<B> findById(Long id) {
		return baseRepository.findById(id);
	}

	public boolean existsById(Long id) {
		return baseRepository.existsById(id);
	}


	public B getOne(Long id) {
		return baseRepository.getOne(id);
	}

	public long count() {
		return baseRepository.count();
	}

	public void deleteById(Long id) {
		baseRepository.deleteById(id);
	}

	public void delete(B entity) {
		baseRepository.delete(entity);
	}

	public void deleteAll(Iterable<? extends B> entities) {
		baseRepository.deleteAll(entities);
	}

	public void deleteAll() {
		baseRepository.deleteAll();
	}

	
	public <S extends B> List<S> saveAll(Iterable<S> entities) {
		return baseRepository.saveAll(entities);
	}

	
	public <S extends B> List<S> findAll(Example<S> example) {
		return baseRepository.findAll(example);
	}

	
	public <S extends B> List<S> findAll(Example<S> example, Sort sort) {
		return baseRepository.findAll(example,sort);
	}

	
	public <S extends B> Optional<S> findOne(Example<S> example) {
		return baseRepository.findOne(example);
	}

	
	public <S extends B> Page<S> findAll(Example<S> example, Pageable pageable) {
		return baseRepository.findAll(example, pageable);
	}

	
	public <S extends B> long count(Example<S> example) {
		return baseRepository.count(example);
	}

	
	public <S extends B> boolean exists(Example<S> example) {
		return baseRepository.exists(example);
	}


	
}
