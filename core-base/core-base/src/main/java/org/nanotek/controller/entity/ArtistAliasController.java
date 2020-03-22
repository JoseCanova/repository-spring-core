package org.nanotek.controller.entity;

import org.nanotek.ArtistAlias;
import org.nanotek.service.jpa.ArtistAliasJpaService;
import org.nanotek.service.jpa.BasePersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist_alias")
public class ArtistAliasController implements  EntityResponseController<ArtistAlias, Long>  {
	
	@Autowired
	ArtistAliasJpaService service;
	
	@Override
	public BasePersistenceService<ArtistAlias, Long> getBaseService() {
		return service;
	}

}
