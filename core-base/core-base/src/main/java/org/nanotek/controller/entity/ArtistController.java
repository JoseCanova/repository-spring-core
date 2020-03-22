package org.nanotek.controller.entity;

import org.nanotek.base.bean.projections.ArtistVirtualProjection;
import org.nanotek.beans.entity.Artist;
import org.nanotek.controller.response.IterableResponseEntity;
import org.nanotek.controller.response.ResponseBase;
import org.nanotek.service.jpa.ArtistJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist")
public class ArtistController  implements  EntityResponseController<Artist, Long> {


	@Autowired
	private ArtistJpaService baseService;
	

	@RequestMapping("/name/{name}")
	public IterableResponseEntity<Iterable<Artist>, Artist> findByName(@PathVariable(value="name") String  name) {
		Iterable<Artist> it = getBaseService().findByNameContaining(name);
		return IterableResponseEntity.fromIterable(it, HttpStatus.OK);
	}

	@RequestMapping("/virtual/{artistId}")
	public  ResponseBase<ArtistVirtualProjection> projectByArtistId(@PathVariable(value="artistId") Long  artistId){
		return ResponseBase.fromEntity(baseService.projectArtistVirtualByArtistId(artistId), HttpStatus.OK);
	}
	
	public ArtistJpaService getBaseService() {
		return baseService;
	}
	
}