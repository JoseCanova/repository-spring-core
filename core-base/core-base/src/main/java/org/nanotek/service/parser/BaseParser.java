package org.nanotek.service.parser;

import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.nanotek.BaseException;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.opencsv.BaseMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

public class BaseParser extends CSVParser implements InitializingBean , Closeable{

	protected CSVReader csvReader;
	
	@Autowired
	protected BaseMapColumnStrategy<?,?> baseMapColumnStrategy;
	
	public BaseParser() {}
	
	public BaseParser(CSVReader csvReader) { 
		this.csvReader = csvReader;
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
		fileLocationStr.append(getBaseMapColumnStrategy().getFileLocation())
		.append(System.getProperty("file.separator")).append(getBaseMapColumnStrategy().getFileName().toString());
		FileReader fileReader = new FileReader(new File(fileLocationStr.toString()));
		csvReader = new CSVReader(fileReader , '\t');		
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
	
	public BaseMapColumnStrategy<?,?> getBaseMapColumnStrategy(){
		return baseMapColumnStrategy;
	}

	public BaseMap<?,?> getBaseMap(){
		return baseMapColumnStrategy.getBaseMap();
	}
	
	

}
