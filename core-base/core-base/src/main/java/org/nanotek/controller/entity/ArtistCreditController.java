package org.nanotek.controller.entity;

import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.controller.response.IdBaseResponseBase;
import org.nanotek.service.jpa.ArtistCreditJpaService;
import org.nanotek.service.jpa.BasePersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist_credit")
public class ArtistCreditController  implements  EntityResponseController<ArtistCredit, Long> {

	@Autowired
	ArtistCreditJpaService baseService;

	@Override
	public BasePersistenceService<ArtistCredit, Long> getBaseService() {
		return baseService;
	}

	@GetMapping("/cache/{id}")
	public IdBaseResponseBase<ArtistCredit> findByIdCache(@PathVariable(value="id") Long  id)  {
		ArtistCredit opt = baseService.findArtistCreditRecordingsById(id);
		HttpStatus status =  opt != null  ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return IdBaseResponseBase.fromEntity(opt , status);
	}
}
