package org.nanotek.opencsv;

import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.nanotek.AnyBase;
import org.nanotek.BaseException;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvBaseConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

@SuppressWarnings("unchecked")
public class BaseParser extends CSVParser 
implements InitializingBean , Closeable{

	protected CSVReader csvReader;
	
	protected CsvBaseConfig config;
	
	@Autowired
	@Qualifier("MapColumnStrategy")
	protected  MapColumnStrategy mapColumnStrategy;
	
	public BaseParser() {}
	
	public BaseParser(CSVReader csvReader) { 
		this.csvReader = csvReader;
	}
	
	public <M extends BaseMap<K,I,?>, K extends AnyBase<K,String> ,  I extends AnyBase<I,Integer> ,  B extends BaseBean<?,?>> BaseParser(CSVReader csvReader , MapColumnStrategy<M,I,B> mapColumnStrategy) { 
		this.csvReader = csvReader;
		this.mapColumnStrategy = mapColumnStrategy;
	}

	public CSVReader getCsvReader() {
		return csvReader;
	}

	public void setCsvReader(CSVReader csvReader) {
		this.csvReader = csvReader;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		openFileReader();
		
	}

	private void openFileReader() throws Exception{
		StringBuffer fileLocationStr = new StringBuffer();
		fileLocationStr.append(getConfig().getFileLocation())
		.append(System.getProperty("file.separator")).append(getConfig().getFileName().toString());
		FileReader fileReader = new FileReader(new File(fileLocationStr.toString()));
		csvReader = new CSVReader(fileReader , '\t');		
	}

	public CsvBaseConfig getConfig() {
		return config;
	}

	public void setConfig(CsvBaseConfig config) {
		this.config = config;
	}

	public List<String[]> readAll() throws IOException {
		return csvReader.readAll();
	}

	public Optional<String[]> readNext() {
		try { 
		return Optional.ofNullable(csvReader.readNext());
		}catch (Exception ex) { 
			throw new BaseException(ex);
		}
	}

	@Override
	public void close() throws IOException {
		csvReader.close();
	}

	public void reopen() throws Exception { 
		csvReader.close();
		openFileReader();
	}
	
	public <M extends BaseMap<K,I,?>, K extends AnyBase<K,String> ,  I extends AnyBase<I,Integer> ,  B extends BaseBean<?,?>> 
	MapColumnStrategy<M,I,B> getBaseMapColumnStrategy(){
		return mapColumnStrategy;
	}

	public <K extends AnyBase<K,String> ,  I extends AnyBase<I,Integer> ,  B extends BaseBean<?,?>>
	BaseMap<K,I,?> getBaseMap(){
		return mapColumnStrategy.getBaseMap();
	}
}
