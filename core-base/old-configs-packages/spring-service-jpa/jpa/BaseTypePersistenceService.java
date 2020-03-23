package org.nanotek.service.jpa;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.BaseType;
import org.nanotek.repository.jpa.LongIdNameEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseTypePersistenceService
<O extends BaseType<O> , B extends LongIdNameEntityRepository<O>> 
implements LongIdNameEntityRepository<O> {

	@Autowired 
	B rep;
	
	public BaseTypePersistenceService(B rep) {
	}
	
	@Transactional
	public abstract Optional<O> findByTypeId(@NotNull Long typeId);
}
