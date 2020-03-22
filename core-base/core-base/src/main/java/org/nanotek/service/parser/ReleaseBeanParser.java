package org.nanotek.service.parser;

import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.base.maps.ReleaseBeanBaseMap;
import org.nanotek.opencsv.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "ReleaseBeanParser")
public class ReleaseBeanParser extends BaseParser{

	@Autowired
	@Qualifier("ReleaseBeanBaseMap")
	private ReleaseBeanBaseMap releaseBeanBaseMap;
	
	public ReleaseBeanParser() {}
	


	public ReleaseBeanBaseMap getReleaseBeanBaseMap() {
		return releaseBeanBaseMap;
	}


	public void setReleaseBeanBaseMap(ReleaseBeanBaseMap releaseBeanBaseMap) {
		this.releaseBeanBaseMap = releaseBeanBaseMap;
	}

}
