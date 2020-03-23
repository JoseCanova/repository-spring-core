package org.nanotek.controller.entity;

import java.util.Optional;

import org.nanotek.beans.entity.ArtistType;
import org.nanotek.controller.response.IdBaseResponseBase;
import org.nanotek.controller.response.IterableResponseEntity;
import org.nanotek.service.jpa.ArtistTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist_type")
public class ArtistTypeController  implements  EntityResponseController<ArtistType, Long> {


	@Autowired
	private ArtistTypeService baseService;
	

	@RequestMapping("/name/{name}")
	public IterableResponseEntity<Iterable<ArtistType>, ArtistType> findByName(@PathVariable(value="name") String  name) {
		Iterable<ArtistType> it = getBaseService().findByNameContaining(name);
		return IterableResponseEntity.fromIterable(it, HttpStatus.OK);
	}

	@RequestMapping("/type_id/{type_id}")
	public IdBaseResponseBase<ArtistType> findByTypeId(@PathVariable(value="type_id") Long  id) {
		Optional<ArtistType> it = getBaseService().findByTypeId(id);
		return IdBaseResponseBase.fromEntityBase(it, HttpStatus.OK);
	}
	
	public ArtistTypeService getBaseService() {
		return baseService;
	}
	
}