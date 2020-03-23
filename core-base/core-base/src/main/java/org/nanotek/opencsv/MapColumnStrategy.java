package org.nanotek.opencsv;

import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.nanotek.AnyBase;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.collections.BaseMap;
import org.springframework.beans.factory.InitializingBean;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;

/**
 * Class that works as a bridge between opecsv and the basebeans. 
 * 
 * @author jose
 *
 * @param <T> Base Map defines the configuration of properties and posision on the csv file
 * @param <S> AnyBase for the Map Key 
 * @param <V> AnyBase for the Value Key
 */
//TODO: review implementation of MapColumnStrategy
public class MapColumnStrategy
<T extends BaseMap<?,V,?>, V extends AnyBase<V,Integer> ,  D extends BaseBean<?,?>> 
extends  ColumnPositionMappingStrategy<D> 
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
									Optional<Integer> value = (Optional<Integer>) baseMap.get(k).getValue();
									csvColumns[value.get()] = k.getValue().get().toString();
									});
		this.setColumnMapping(csvColumns);
		Arrays.asList(csvColumns).stream().forEach(c -> System.out.println(c));
	}

	
	public static void main(String[] args) { 
		MapColumnStrategy s = new MapColumnStrategy(new BaseMap(new ArtistBean()));
		s.afterPropertiesSet();
	}
	
}
