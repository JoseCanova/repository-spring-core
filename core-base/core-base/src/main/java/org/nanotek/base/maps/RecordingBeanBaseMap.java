package org.nanotek.base.maps;

import org.nanotek.beans.csv.RecordingBean;
import org.nanotek.opencsv.BaseMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "RecordingBeanBaseMap")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "recordingbean")
public class RecordingBeanBaseMap<K extends BaseMap<K,Class<RecordingBean>>> extends BaseMapColumnStrategy<K,RecordingBean>{
	private static final long serialVersionUID = 5152789565432261772L;

	public RecordingBeanBaseMap() { 
		super();
	}
}
