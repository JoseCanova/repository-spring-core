package org.nanotek.controller.entity;

import org.nanotek.beans.entity.Release;
import org.nanotek.service.jpa.BasePersistenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/release")
public class ReleaseCotroller implements  EntityResponseController<Release, Long>{

	private BasePersistenceService<Release, Long> baseService;

	@Override
	public BasePersistenceService<Release, Long> getBaseService() {
		return baseService;
	}
	
	
}
