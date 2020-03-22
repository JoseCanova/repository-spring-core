package org.nanotek.service;

import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.service.jpa.ArtistCreditJpaService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ArtistCreditService implements BaseService<ArtistCredit>{

	private static final long serialVersionUID = 5324723400955565335L;
	
	@Autowired
	ArtistCreditJpaService jpaService;

}
