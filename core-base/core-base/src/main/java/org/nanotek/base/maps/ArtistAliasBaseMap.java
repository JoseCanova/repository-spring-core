package org.nanotek.base.maps;

import org.nanotek.beans.csv.ArtistAliasBean;
import org.nanotek.opencsv.BaseMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "ArtistAliasBaseMap")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "artistalias")
public class ArtistAliasBaseMap<K extends BaseMap<K,ArtistAliasBean>> extends BaseMapColumnStrategy<K, ArtistAliasBean>  {

	
	private static final long serialVersionUID = 2211459099571938436L;

	public ArtistAliasBaseMap() {
	}
	
	
}
