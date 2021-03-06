package org.nanotek.base.maps;

import org.nanotek.beans.csv.ArtistCreditNameBean;
import org.nanotek.collections.OldBaseMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "ArtistCreditNameBaseMap")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "artistcreditname")
public class ArtistCreditNameBaseMap<K extends OldBaseMap<K,Class<ArtistCreditNameBean>>> extends BaseMapColumnStrategy<K , ArtistCreditNameBean> {

	private static final long serialVersionUID = -4296164143609718972L;

	public ArtistCreditNameBaseMap() {
		super();
	}
}
