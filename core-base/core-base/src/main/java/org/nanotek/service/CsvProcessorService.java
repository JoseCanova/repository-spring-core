package org.nanotek.service;

import org.nanotek.opencsv.CsvBaseConfig;
import org.springframework.stereotype.Service;

@Service
public class CsvProcessorService extends CsvBaseConfig{
	public CsvProcessorService() {} 
	
	public CsvProcessorService(String fileLocation , String fieldDelimiter, String tupleDelimiter) { 
		this.fileLocation = fileLocation; 
		this.fieldDelimiter = fieldDelimiter; 
		this.tupleDelimiter = tupleDelimiter;
	}

}
