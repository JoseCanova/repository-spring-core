package org.nanotek.opencsv.file;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;

import org.nanotek.AnyBase;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.MapColumnStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.MappingStrategy;

@Component
public class CsvFileItemConcreteStrategy 
<T extends BaseMap<S,P,M> , S  extends AnyBase<S,String> , P   extends AnyBase<P,Integer> , M extends BaseBean<?,?>>
extends CsvFileItemConfigMappingStrategy<T,S,P,M> implements MappingStrategy<M>, InitializingBean {

	private static Logger log = LoggerFactory.getLogger(CsvFileItemConcreteStrategy.class.getName());
	
	T baseMap;
	
	MapColumnStrategy<T,P,M> mapColumnStrategy;
	
	@SuppressWarnings("unchecked")
	public CsvFileItemConcreteStrategy() {
		super();
	}
	
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		mapColumnStrategy = MapColumnStrategy.class.cast(new MapColumnStrategy(baseMap));
		mapColumnStrategy.afterPropertiesSet();
	}

	@Override
	public PropertyDescriptor findDescriptor(int col) throws IntrospectionException {
		return mapColumnStrategy.findDescriptor(col);
	}


	@Override
	public M createBean() throws InstantiationException, IllegalAccessException {
		return mapColumnStrategy.createBean();
	}


	@Override
	public void captureHeader(CSVReader reader) throws IOException {
	}

	@Override
	public BaseMap<S, P, ?> getBaseMap() {
		return baseMap;
	}


	public void setBaseMap(T baseMap) {
		this.baseMap = baseMap;
	}


}
