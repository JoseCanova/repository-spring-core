package org.nanotek.base.maps;

import org.nanotek.beans.csv.ArtistCreditNameBean;
import org.nanotek.collections.OldBaseMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "ArtistCreditNameBeanBaseMap")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "artistcreditnamebean")
public class ArtistCreditNameBeanBaseMap<K extends OldBaseMap<K,Class<ArtistCreditNameBean>>> extends BaseMapColumnStrategy<K,ArtistCreditNameBean> {

	private static final long serialVersionUID = 6188640277899291092L;

	public ArtistCreditNameBeanBaseMap() {
		super();
	}
}
