package org.nanotek.service.parser;

import org.nanotek.base.maps.ArtistBeanBaseMap;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.opencsv.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "ArtistBeanParser")
@DependsOn(value = {"ArtistBeanBaseMap"})
public class ArtistBeanParser extends BaseParser {

	@Autowired
	private ArtistBeanBaseMap artistBaseMap;

	public ArtistBeanBaseMap getArtistBaseMap() {
		return artistBaseMap;
	}

	public void setArtistBaseMap(ArtistBeanBaseMap artisBaseMap) {
		this.artistBaseMap = artisBaseMap;
	}

}
