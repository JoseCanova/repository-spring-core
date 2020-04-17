package org.nanotek.opencsv;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.ValueBase;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConfigMappingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;


import au.com.bytecode.opencsv.CSVParser;

@SuppressWarnings("unchecked")
public class BaseParser<M extends BaseMap<K,I,B>, 
K extends AnyBase<K,String> ,  
I extends AnyBase<I,Integer> 
,  B extends BaseBean<?,?>> 
extends CSVParser implements InitializingBean
{
	private Logger log = LoggerFactory.getLogger(CsvBaseProcessor.class);

	protected BufferedReader reader;
	
	protected CSVParser csvParser;
	
	@Autowired
	protected  CsvFileItemConfigMappingStrategy<M,K,I,B> mapColumnStrategy;

	public BaseParser() {this.csvParser = new CSVParser('\t');}
	
	public   BaseParser(CsvFileItemConfigMappingStrategy<?,?,?,?> mapColumnStrategy) { 
		this.mapColumnStrategy = CsvFileItemConfigMappingStrategy.class.cast(mapColumnStrategy);
		reader = mapColumnStrategy.getCSVReader();
		this.csvParser = new CSVParser('\t');
	}
	
	public void afterPropertiesSet() { 
		this.reader = mapColumnStrategy.getCSVReader();
	}

	public BufferedReader getCsvReader() {
		return reader;
	}

	public  <T extends ValueBase<?>, S extends T> List<?>  readNext() {
		
		try { 	
			return Optional.ofNullable(
					reader.readLine())
					.map(s -> mountList(s)).orElse(null);
		}catch (Exception ex) { 
			throw new BaseException(ex);
		}
	}

	private List<? super ValueBase<?>> mountList(String line) {
		log.debug("line read\t" + line);
		int pos = 0;
		ArrayList<? super ValueBase<?>> al = new ArrayList<>();
		try { 
		String[] sArry = csvParser.parseLine(line);
		for (pos = 0 ; pos < sArry.length ; pos++) {
			ValueBase<?> base =    ValueBase.of(pos,sArry[pos]);
			log.debug(base.toString());
			al.add(base);
		} 
		}catch(Exception ex) { 
			throw new BaseException(ex);
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
