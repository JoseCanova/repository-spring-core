package org.nanotek.service.parser;

import java.io.Serializable;

import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.collections.OldBaseMap;

public class BaseMapParser<K extends BaseMapColumnStrategy<?,?>, I extends Serializable> extends BaseParser{
	
	private K baseMapColumnStrategy;
	
	public BaseMapParser() {}
	
	public BaseMapParser(K baseMapColumnStrategy) {
		super();
		this.baseMapColumnStrategy = baseMapColumnStrategy;
	}

	public void setBaseMap(K baseMapColumnStrategy) {
		this.baseMapColumnStrategy = baseMapColumnStrategy;
	}

	public OldBaseMap<?,?> getBaseMap() {
		return baseMapColumnStrategy.getBaseMap();
	}

	public void setFileName(String newFileName) {
		getBaseMapColumnStrategy().setFileName(newFileName);
	}

	public K getBaseMapColumnStrategy() {
		return baseMapColumnStrategy;
	}

	public void setBaseMapColumnStrategy(K baseMapColumnStrategy) {
		this.baseMapColumnStrategy = baseMapColumnStrategy;
	}
	
}
