package org.nanotek.controller.entity;

import org.nanotek.beans.entity.ArtistAliasType;
import org.nanotek.service.jpa.ArtistAliasTypeJpaService;
import org.nanotek.service.jpa.BasePersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist_alias_type")
public class ArtistAliasTypeController implements EntityResponseController<ArtistAliasType, Long> {

	@Autowired
	ArtistAliasTypeJpaService service;
	
	@Override
	public BasePersistenceService<ArtistAliasType, Long> getBaseService() {
		return service;
	}

}
