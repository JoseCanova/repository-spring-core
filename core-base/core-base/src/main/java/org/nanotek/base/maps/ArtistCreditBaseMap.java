package org.nanotek.base.maps;

import org.nanotek.beans.csv.ArtistCreditBean;
import org.nanotek.opencsv.BaseMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "ArtistCreditBaseMap")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "artistcredit")
public class ArtistCreditBaseMap<K extends BaseMap<K,Class<ArtistCreditBean>>>extends BaseMapColumnStrategy<K,ArtistCreditBean>{

	private static final long serialVersionUID = 477274629795560373L;

	public ArtistCreditBaseMap () { 
		super();
	}
	
}
