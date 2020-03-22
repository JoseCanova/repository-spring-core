package org.nanotek.controller.entity;

import org.nanotek.beans.entity.Recording;
import org.nanotek.service.jpa.BasePersistenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/recording")
public class RecordingBeanController implements  EntityResponseController<Recording, Long>  {

	private BasePersistenceService<Recording, Long> baseService;

	@Override
	public BasePersistenceService<Recording, Long> getBaseService() {
		return baseService;
	}
	
	
}
