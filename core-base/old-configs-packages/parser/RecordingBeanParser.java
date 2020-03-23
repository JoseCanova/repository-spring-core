package org.nanotek.service.parser;

import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.base.maps.RecordingBeanBaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "RecordingBeanParser")
public class RecordingBeanParser extends BaseParser{

	@Autowired
	@Qualifier("RecordingBeanBaseMap")
	private RecordingBeanBaseMap recordingBeanBaseMap;
	
	public RecordingBeanParser() {}

	@Override
	public BaseMapColumnStrategy<?,?> getBaseMapColumnStrategy() {
		return recordingBeanBaseMap;
	}

	public RecordingBeanBaseMap getRecordingBeanBaseMap() {
		return recordingBeanBaseMap;
	}

	public void setRecordingBeanBaseMap(RecordingBeanBaseMap recordingBeanBaseMap) {
		this.recordingBeanBaseMap = recordingBeanBaseMap;
	}
	
}
