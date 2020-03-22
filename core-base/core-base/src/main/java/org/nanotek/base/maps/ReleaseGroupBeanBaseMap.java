package org.nanotek.base.maps;

import org.nanotek.beans.csv.ReleaseGroupBean;
import org.nanotek.collections.OldBaseMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "ReleaseGroupBeanBaseMap")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "releasegroup")
public class ReleaseGroupBeanBaseMap<K extends OldBaseMap<K,Class<ReleaseGroupBean>>> extends BaseMapColumnStrategy<K,ReleaseGroupBean>{
	private static final long serialVersionUID = -5112114045536709618L;

	public ReleaseGroupBeanBaseMap() { 
		super();
	}
}
