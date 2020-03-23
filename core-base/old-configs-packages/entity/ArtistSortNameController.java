package org.nanotek.controller.entity;

import org.nanotek.beans.entity.ArtistSortName;
import org.nanotek.controller.response.IterableResponseEntity;
import org.nanotek.service.jpa.ArtistSortNameJpaService;
import org.nanotek.service.jpa.BasePersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
		
@RestController
@RequestMapping("/artist_sortname")
public class ArtistSortNameController  implements  EntityResponseController<ArtistSortName, Long>{

	@Autowired
	ArtistSortNameJpaService service;
	
	public ArtistSortNameController() {
	}

	@Override
	public BasePersistenceService<ArtistSortName, Long> getBaseService() {
		return service;
	}
	
	@RequestMapping("/name/{name}")
	public IterableResponseEntity<Iterable<ArtistSortName>, ArtistSortName> findByName(@PathVariable(value="name") String  name) {
		Iterable<ArtistSortName> it = service.findByNameContaining(name);
		return IterableResponseEntity.fromIterable(it, HttpStatus.OK);
	}
	

}
