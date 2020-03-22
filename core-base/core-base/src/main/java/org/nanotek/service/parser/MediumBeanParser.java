package org.nanotek.service.parser;

import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.base.maps.MediumBeanBaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "MediumBeanParser")
public class MediumBeanParser extends BaseParser{

	@Autowired
	@Qualifier("MediumBeanBaseMap")
	private MediumBeanBaseMap mediumBeanBaseMap;
	
	public MediumBeanParser() {}

	@Override
	public BaseMapColumnStrategy<?,?> getBaseMapColumnStrategy() {
		return mediumBeanBaseMap;
	}

	public MediumBeanBaseMap getRecordingBeanBaseMap() {
		return mediumBeanBaseMap;
	}

	public void setRecordingBeanBaseMap(MediumBeanBaseMap mediumBeanBaseMap) {
		this.mediumBeanBaseMap = mediumBeanBaseMap;
	}
	
}
