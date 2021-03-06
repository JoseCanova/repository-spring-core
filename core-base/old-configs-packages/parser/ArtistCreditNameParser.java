package org.nanotek.service.parser;

import org.nanotek.base.maps.ArtistCreditNameBaseMap;
import org.nanotek.collections.OldBaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "ArtistCreditNameParser")
@DependsOn(value = {"ArtistCreditNameBaseMap"})
public class ArtistCreditNameParser extends BaseParser{

	@Autowired
	private ArtistCreditNameBaseMap artistCreditNameBaseMap;
	
	public ArtistCreditNameParser() {}
	
	
	@Override
	public OldBaseMap<?,?> getBaseMap() {
		return artistCreditNameBaseMap.getBaseMap();
	}


	public ArtistCreditNameBaseMap getArtistCreditNameBaseMap() {
		return artistCreditNameBaseMap;
	}


	public void setArtistCreditNameBaseMap(ArtistCreditNameBaseMap artistCreditNameBaseMap) {
		this.artistCreditNameBaseMap = artistCreditNameBaseMap;
	}

}
