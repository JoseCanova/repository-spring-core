package org.nanotek.opencsv.file;

import java.io.BufferedReader;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;

public abstract class CsvFileItemConfigMappingStrategy 
<T extends BaseMap<S,P,M> , 
S  extends AnyBase<S,String> , 
P   extends AnyBase<P,Integer> , 
M extends BaseBean<?,?>>
extends CsvFileItem<S,P,M>{

//MapColumnStrategy<T, P, M>{

	public CsvFileItemConfigMappingStrategy() {
	}

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
	}
	
}
