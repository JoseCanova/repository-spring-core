package org.nanotek.opencsv;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.ValueBase;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVParser;

@SuppressWarnings("unchecked")
public class CsvBaseParser<M extends BaseMap<K,I,B>,
K extends AnyBase<K,String> ,
I extends AnyBase<I,Integer>
, B extends BaseBean<?,?>>
extends CSVParser // Extends CSVParser from au.com.bytecode.opencsv
{
	private static final Logger log = LoggerFactory.getLogger(CsvBaseParser.class); // Renamed logger class

	protected BufferedReader reader;

	protected CsvFileItemConcreteStrategy<M,K,I,B> mapColumnStrategy; // No longer @Autowired

    // Constructor now directly takes the specific strategy it will use
	public CsvBaseParser(CsvFileItemConcreteStrategy<M,K,I,B> mapColumnStrategy) {
		super('\t'); // Call CSVParser constructor with delimiter
		this.mapColumnStrategy = mapColumnStrategy;
        // The reader is now obtained directly here, assuming mapColumnStrategy is already initialized
        // (which will be handled by CsvBaseParserConfigurations)
		this.reader = mapColumnStrategy.getCSVReader();
	}

    // No default constructor as it requires a strategy
    // No afterPropertiesSet() or InitializingBean interface

	public BufferedReader getCsvReader() {
		return reader;
	}

	public <T extends ValueBase<?>, S extends T> List<?> readNext() {
		try {
			return Optional.ofNullable(
					reader.readLine())
					.map(s -> mountList(s)).orElse(null);
		}catch (Exception ex) {
			throw new BaseException(ex);
		}
	}

	private List<? super ValueBase<?>> mountList(final String line) {
		log.debug("line read\t" + line);
		final ArrayList<? super ValueBase<?>> al = new ArrayList<>();
		try {
			final String[] sArry = Optional
					.ofNullable(parseLine(line)).get(); // Use parseLine from superclass
			for (int pos = 0 ; pos < sArry.length ; pos++) {
				al.add(ValueBase.of(pos,sArry[pos]));
			}
		}catch(Exception ex) {
			throw new BaseException(ex);
		}
		return al;
	}

	public CsvFileItemConcreteStrategy<M,K,I,B> getBaseMapColumnStrategy(){
		return mapColumnStrategy;
	}

	public BaseMap<K,I,B> getBaseMap(){
		return mapColumnStrategy.getBaseMap();
	}
}