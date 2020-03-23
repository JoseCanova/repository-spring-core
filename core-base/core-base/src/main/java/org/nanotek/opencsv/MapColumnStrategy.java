package org.nanotek.opencsv;

import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.nanotek.AnyBase;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.collections.BaseMap;
import org.springframework.beans.factory.InitializingBean;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;

//TODO: review implementation of MapColumnStrategy
public class MapColumnStrategy<T extends BaseMap<S,V,?> , S extends AnyBase<S,String>,V extends AnyBase<V,Integer>> 
extends  ColumnPositionMappingStrategy<T> 
implements InitializingBean {

	protected T baseMap; 
	
	public MapColumnStrategy() {
		super();
	}

	public MapColumnStrategy(T baseMap) {
		this.baseMap = baseMap;
	}
	
	public T getBaseMap() {
		return baseMap;
	}

	public void setBaseMap(T baseMap) {
		this.baseMap = baseMap;
	}
	
	public void afterPropertiesSet() {
		baseMap.afterPropertiesSet();
		assert (baseMap !=null && baseMap.size() >=1);
		String [] csvColumns = new String[baseMap.size()];
						baseMap.keySet().stream().forEach(k -> {
									Optional<Integer> value = baseMap.get(k).getValue();
									csvColumns[value.get()] = k.getValue().get();
									});
		this.setColumnMapping(csvColumns);
		Arrays.asList(csvColumns).stream().forEach(c -> System.out.println(c));
	}

	
	public static void main(String[] args) { 
		MapColumnStrategy s = new MapColumnStrategy(new BaseMap(new ArtistBean()));
		s.afterPropertiesSet();
	}
	
}
