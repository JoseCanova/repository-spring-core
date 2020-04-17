package org.nanotek.opencsv.file;


import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.beans.IntrospectionException;
import org.nanotek.beans.PropertyDescriptor;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.MapColumnStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.MappingStrategy;

//@Component
public class CsvFileItemConcreteStrategy 
<T extends BaseMap<S,P,M> , 
S  extends AnyBase<S,String> , 
P   extends AnyBase<P,Integer> , 
M extends BaseBean<?,?>>
extends CsvFileItemConfigMappingStrategy<T,S,P,M> 
implements MappingStrategy<M>, InitializingBean , Closeable {

	private static Logger log = LoggerFactory.getLogger(CsvFileItemConfigMappingStrategy.class.getName());

	T baseMap;

	MapColumnStrategy<M> mapColumnStrategy;

	private BufferedReader reader;	
	
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


	public MapColumnStrategy<M> getMapColumnStrategy() {
		return mapColumnStrategy;
	}

	public void setMapColumnStrategy(MapColumnStrategy<M> mapColumnStrategy) {
		this.mapColumnStrategy = mapColumnStrategy;
	}


	@Override
	public void captureHeader(CSVReader reader) throws IOException {
	}

	@Override
	public BaseMap<S, P, M> getBaseMap() {
		return baseMap;
	}

	public void setBaseMap(T baseMap) {
		this.baseMap = baseMap;
	}

	public BufferedReader reopen() { 
		try { 
			reader.close();
			return prepareFileReader();
		}catch (Exception ex) { 
			throw new BaseException(ex);
		}
	}

	//TODO adjust in configuration the delimiter.
	private BufferedReader prepareFileReader(){
		try {
			StringBuffer fileLocationStr = new StringBuffer();
			fileLocationStr.append(getFileLocation())
			.append(System.getProperty("file.separator")).append(getFileName().toString());
			FileInputStream fin = new FileInputStream(new File(fileLocationStr.toString()));
			InputStreamReader ir = new InputStreamReader(fin,Charset.forName("UTF-8"));
			reader =  new BufferedReader(ir, 1638400);
		} catch (Exception e) {
			throw new BaseException("CSV File not found" , e);

		}
		return reader;
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

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

	@Override
	public M createBean() {
		return  immutable.cast(Base.newInstance(immutable).get());
	}

	@Override
	public BufferedReader getCSVReader() {
		return reopen();
	}
}
