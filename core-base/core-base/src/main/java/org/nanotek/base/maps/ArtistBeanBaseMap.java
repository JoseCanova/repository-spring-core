package org.nanotek.base.maps;

import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.opencsv.BaseMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;


@Component(value = "ArtistBeanBaseMap")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "artist")
public class ArtistBeanBaseMap<K extends BaseMap<K,Class<ArtistBean>>> extends BaseMapColumnStrategy<K,ArtistBean>{

	private static final long serialVersionUID = -8548776439981495201L;

	public ArtistBeanBaseMap() {
	}

}
