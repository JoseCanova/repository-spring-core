package org.nanotek.controller.entity;

import java.util.Optional;

import org.nanotek.IdBase;
import org.nanotek.controller.response.IdBaseResponseBase;
import org.nanotek.controller.response.IterableResponseEntity;
import org.nanotek.service.jpa.BasePersistenceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface EntityResponseController < B extends IdBase<B,?>>{


	BasePersistenceService<B,?> getBaseService();

	@GetMapping("/{id}")
	default IdBaseResponseBase<B> get(@PathVariable(value="id") Long  id)  {
		Optional<B> opt = getBaseService().findById(id);
		HttpStatus status =  opt.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		B body = opt.isPresent() ? opt.get() : null;
		return IdBaseResponseBase.fromEntity(body , status);
	}
	
	@GetMapping("/findAll")
	default IterableResponseEntity<?, B> findAll(){ 
		return IterableResponseEntity.fromIterable(getBaseService().findAll() , HttpStatus.OK);
	}
	
	@PostMapping("/add")
	default IdBaseResponseBase<B> post(@RequestBody B e){ 
		B ei = getBaseService().saveAndFlush(e);
		return IdBaseResponseBase.fromEntity(ei ,  HttpStatus.OK );
	}
	
}
