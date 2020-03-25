package org.nanotek.opencsv.file;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.nanotek.AnyBase;
import org.nanotek.BaseException;
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
<T extends BaseMap<S,P,M> , 
S  extends AnyBase<S,String> , 
P   extends AnyBase<P,Integer> , 
M extends BaseBean<?,?>>
extends CsvFileItemConfigMappingStrategy<T,S,P,M> implements MappingStrategy<M>, InitializingBean , Closeable {

	private static Logger log = LoggerFactory.getLogger(CsvFileItemConfigMappingStrategy.class.getName());
	
	T baseMap;
	
	public String[] getColumnMapping() {
		return mapColumnStrategy.getColumnMapping();
	}

	public PropertyDescriptor findDescriptor(int col) {
		return mapColumnStrategy.findDescriptor(col);
	}

	public PropertyDescriptor findDescriptor(String name) throws IntrospectionException {
		return mapColumnStrategy.findDescriptor(name);
	}

	public boolean matches(String name, PropertyDescriptor desc) {
		return mapColumnStrategy.matches(name, desc);
	}

	public Map<String, PropertyDescriptor> loadDescriptorMap(Class<M> cls) throws IntrospectionException {
		return mapColumnStrategy.loadDescriptorMap(cls);
	}

	public PropertyDescriptor[] loadDescriptors(Class<M> cls) throws IntrospectionException {
		return mapColumnStrategy.loadDescriptors(cls);
	}

	public Class<M> getType() {
		return mapColumnStrategy.getType();
	}

	MapColumnStrategy<M> mapColumnStrategy;
	
	CSVReader csvReader;
	
	public CsvFileItemConcreteStrategy() {
		super();
	}
	
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		mapColumnStrategy = new MapColumnStrategy<M>(immutable);
		prepareFileReader();
		mapColumnStrategy.configureMapStrategy(BaseMap.class.cast(baseMap));
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

	public void reopen() throws Exception { 
		csvReader.close();
		prepareFileReader();
	}

	private void prepareFileReader(){
		try {
				StringBuffer fileLocationStr = new StringBuffer();
				fileLocationStr.append(getFileLocation())
				.append(System.getProperty("file.separator")).append(getFileName().toString());
				FileReader fileReader = new FileReader(new File(fileLocationStr.toString()));
				csvReader = new CSVReader(fileReader , '\t');		
		} catch (FileNotFoundException e) {
			throw new BaseException("CSV File not found" , e);

		}
	}
	
	@Override
	public void close() throws IOException {
		csvReader.close();
	}

	@Override
	public M createBean() throws InstantiationException, IllegalAccessException {
//		return getType().;
		return null;
	}

}
