package org.nanotek.service.parser;

import org.nanotek.base.maps.ArtistCreditNameBeanBaseMap;
import org.nanotek.opencsv.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "ArtistCreditNameBeanParser")
public class ArtistCreditNameBeanParser extends BaseParser{

	@Autowired
	private ArtistCreditNameBeanBaseMap artistCreditNameBeanBaseMap;
	
	public ArtistCreditNameBeanParser() {}
	
	
	@Override
	public BaseMap<?,?> getBaseMap() {
		return artistCreditNameBeanBaseMap.getBaseMap();
	}


	public ArtistCreditNameBeanBaseMap getArtistCreditNameBeanBaseMap() {
		return artistCreditNameBeanBaseMap;
	}


	public void setArtistCreditNameBeanBaseMap(ArtistCreditNameBeanBaseMap artistCreditNameBeanBaseMap) {
		this.artistCreditNameBeanBaseMap = artistCreditNameBeanBaseMap;
	}

}
