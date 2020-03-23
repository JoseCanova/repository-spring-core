package org.nanotek.service.parser;

import org.nanotek.base.maps.ArtistAliasBaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component(value = "ArtistAliasParserOld")
@Qualifier(value = "ArtistAliasParserOld")
@DependsOn({"ArtistAliasBaseMap"})
public class ArtistAliasParser extends BaseParser{

	@Autowired
	private ArtistAliasBaseMap<?> artistBaseMap;


	public ArtistAliasBaseMap<?> getArtistBaseMap() {
		return artistBaseMap;
	}


	public void setArtistBaseMap(ArtistAliasBaseMap<?> artistBaseMap) {
		this.artistBaseMap = artistBaseMap;
	}

	
}
