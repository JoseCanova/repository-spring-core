package org.nanotek.controller.entity;

import org.nanotek.beans.entity.InstrumentType;
import org.nanotek.controller.response.IterableResponseEntity;
import org.nanotek.service.jpa.InstrumentTypeJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instrument_type")
public class InstrumentTypeController  implements  EntityResponseController<InstrumentType, Long> {


	@Autowired
	private InstrumentTypeJpaService baseService;
	

	@RequestMapping("/name/{name}")
	public IterableResponseEntity<Iterable<InstrumentType>, InstrumentType> findByName(@PathVariable(value="name") String  name) {
		Iterable<InstrumentType> it = getBaseService().findByNameContaining(name);
		return IterableResponseEntity.fromIterable(it, HttpStatus.OK);
	}

	public InstrumentTypeJpaService getBaseService() {
		return baseService;
	}
	
}