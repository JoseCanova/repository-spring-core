package org.nanotek.base.maps;

import org.nanotek.beans.csv.ReleaseBean;
import org.nanotek.opencsv.BaseMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "ReleaseBeanBaseMap")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "releasebean")
public class ReleaseBeanBaseMap<K extends BaseMap<K,Class<ReleaseBean>>> extends BaseMapColumnStrategy<K,ReleaseBean>{
	private static final long serialVersionUID = -3337221556032693426L;

	public ReleaseBeanBaseMap() { 
		super();
	}
}
