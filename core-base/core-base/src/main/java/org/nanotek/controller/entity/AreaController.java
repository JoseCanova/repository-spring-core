package org.nanotek.controller.entity;

import org.nanotek.beans.entity.Area;
import org.nanotek.repository.jpa.AreaRepository;
import org.nanotek.service.jpa.AreaJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area")
public class AreaController<B extends Area<B>, C extends AreaRepository<B>>   extends AreaJpaService<B,C> implements  EntityNameBaseResponseController<B> {

	public AreaController(@Autowired @Qualifier("AreaRepository") C rep) {
		super(rep);
	}
}