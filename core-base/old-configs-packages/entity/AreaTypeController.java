package org.nanotek.controller.entity;

import org.nanotek.beans.entity.AreaType;
import org.nanotek.controller.response.IterableResponseEntity;
import org.nanotek.service.jpa.AreaTypeJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area_type")
public class AreaTypeController  implements  BaseTypeController<AreaType> {


	@Autowired
	private AreaTypeJpaService baseService;
	

	@RequestMapping("/name/{name}")
	public IterableResponseEntity<Iterable<AreaType>, AreaType> findByNameContainingIgnoreCase(@PathVariable(value="name") String  name) {
		Iterable<AreaType> it = getBaseService().findByNameContainingIgnoreCase(name);
		return IterableResponseEntity.fromIterable(it, HttpStatus.OK);
	}

	public AreaTypeJpaService getBaseService() {
		return baseService;
	}
	
}