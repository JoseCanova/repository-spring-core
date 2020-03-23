package org.nanotek.controller.entity;

import org.nanotek.beans.entity.ReleaseAlias;
import org.nanotek.service.jpa.ReleaseAliasJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/release_alias")
public class ReleaseAliasController implements  EntityResponseController<ReleaseAlias, Long>{

	@Autowired
	private  ReleaseAliasJpaService baseService;

	@Override
	public ReleaseAliasJpaService getBaseService() {
		return baseService;
	}

}
