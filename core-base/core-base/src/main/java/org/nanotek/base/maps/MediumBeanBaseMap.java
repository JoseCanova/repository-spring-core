package org.nanotek.base.maps;

import org.nanotek.beans.csv.MediumBean;
import org.nanotek.opencsv.BaseMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "MediumBeanBaseMap")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "mediumbean")
public class MediumBeanBaseMap<K extends BaseMap<K,Class<MediumBean>>> extends BaseMapColumnStrategy<K,MediumBean>{
	
	private static final long serialVersionUID = 2075887298895102837L;

	public MediumBeanBaseMap() { 
		super();
	}

}
