package org.nanotek.opencsv.file;

import java.io.BufferedReader;

import org.nanotek.AnyBase;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.collections.BaseMap;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.MappingStrategy;

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

	public abstract BufferedReader getCSVReader();
	
}
