package org.nanotek.opencsv;

import org.nanotek.IdBase;
import org.springframework.beans.factory.InitializingBean;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;

//TODO: review implementation of MapColumnStrategy
public class MapColumnStrategy<T extends BaseMap<?>, ID extends IdBase<ID,?>> 
extends  ColumnPositionMappingStrategy<T> 
implements InitializingBean {

	private static final long serialVersionUID = -4017791440568493951L;

	private ID id;
	
	protected T baseMap; 
	
	public MapColumnStrategy() {
		super();
	}

	public MapColumnStrategy(T baseMap , ID id) {
		this.baseMap = baseMap;
		this.id = id;
	}
	
	public T getBaseMap() {
		return baseMap;
	}

	public void setBaseMap(T baseMap) {
		this.baseMap = baseMap;
	}
	
	public ID getId() {
		return id;
	}

	public void afterPropertiesSet() {
		assert (baseMap !=null && baseMap.size() >=1);
		String [] csvColumns = new String[baseMap.size()];
		try {
			for (Object key : baseMap.keySet()){ 
				Integer position = baseMap.get(key);
				if (position !=null)
					csvColumns[position] = key.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MappingStrategyException (e);
		}
		this.setColumnMapping(csvColumns);
	}

}
