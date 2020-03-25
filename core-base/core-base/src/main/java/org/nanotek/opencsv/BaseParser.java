package org.nanotek.opencsv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.nanotek.AnyBase;
import org.nanotek.BaseException;
import org.nanotek.StringPositionBase;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConfigMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

@SuppressWarnings("unchecked")
public class BaseParser<M extends BaseMap<K,I,B>, K extends AnyBase<K,String> ,  I extends AnyBase<I,Integer> ,  B extends BaseBean<?,?>> 
extends CSVParser 
{

	protected CSVReader csvReader;
	
	@Autowired
	protected  CsvFileItemConfigMappingStrategy<M,K,I,B> mapColumnStrategy;
	
	public BaseParser() {}
	
	public BaseParser(CSVReader csvReader) { 
		this.csvReader = csvReader;
	}
	
	public <M extends BaseMap<K,I,B>, K extends AnyBase<K,String> ,  I extends AnyBase<I,Integer> ,  B extends BaseBean<?,?>> 
		BaseParser(CSVReader csvReader , CsvFileItemConfigMappingStrategy<M,K,I,B> mapColumnStrategy) { 
		this.csvReader = csvReader;
		this.mapColumnStrategy = CsvFileItemConfigMappingStrategy.class.cast(mapColumnStrategy);
	}

	public CSVReader getCsvReader() {
		return csvReader;
	}

	public void setCsvReader(CSVReader csvReader) {
		this.csvReader = csvReader;
	}

	public List<String[]> readAll() throws IOException {
		return csvReader.readAll();
	}

	public  <T extends StringPositionBase<?>, S extends T> List<StringPositionBase<?>>  readNext() {
		try { 	
				return Optional.ofNullable(
						csvReader.readNext())
				.filter(v -> v!=null)
				.map(s -> mountList(s)).orElse(new ArrayList<>());
		}catch (Exception ex) { 
			throw new BaseException(ex);
		}
	}

	private <T extends StringPositionBase<?> , S extends T> List<StringPositionBase<?>> mountList(String[] sArry) {
		int pos = 0;
		ArrayList<StringPositionBase<?>> al = new ArrayList<>();
		for (String s : sArry) {
			StringPositionBase<?> base = new StringPositionBase<>(s, pos);
			al.add(base);
			++pos;
		} 
		return al;
	}

	public 	CsvFileItemConfigMappingStrategy<M,K,I,B> getBaseMapColumnStrategy(){
		return mapColumnStrategy;
	}

	public BaseMap<K,I,B> getBaseMap(){
		return mapColumnStrategy.getBaseMap();
	}
}
