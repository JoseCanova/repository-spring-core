package org.nanotek.controller.entity;

import org.nanotek.beans.entity.Track;
import org.nanotek.service.jpa.BasePersistenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/track")
public class TrackController  implements  EntityResponseController<Track, Long>{

	private BasePersistenceService<Track, Long> baseService;

	@Override
	public BasePersistenceService<Track, Long> getBaseService() {
		return baseService;
	}
	

	
}
