package org.nanotek.service.parser;

import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.base.maps.TrackBeanBaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "TrackBeanParser")
public final class TrackBeanParser  extends BaseParser {

	@Autowired
	@Qualifier("TrackBeanBaseMap")
	private TrackBeanBaseMap trackBeanBaseMap; 
	
	public TrackBeanBaseMap getTrackBeanBaseMap() {
		return trackBeanBaseMap;
	}

	public void setTrackBeanBaseMap(TrackBeanBaseMap trackBeanBaseMap) {
		this.trackBeanBaseMap = trackBeanBaseMap;
	}

}
