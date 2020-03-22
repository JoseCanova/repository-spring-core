package org.nanotek.base.maps;

import org.nanotek.beans.csv.TrackBean;
import org.nanotek.opencsv.BaseMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "TrackBeanBaseMap")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "trackbean")
public class TrackBeanBaseMap<K extends BaseMap<K,Class<TrackBean>>> extends BaseMapColumnStrategy<K,TrackBean> {

	private static final long serialVersionUID = -3892873557227957848L;

	public TrackBeanBaseMap() { 
		super();
	}
	
}
