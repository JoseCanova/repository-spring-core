package org.nanotek.opencsv.file;


public class CsvBaseConfig {
	
	protected String fileLocation;
	
	protected String fileName;
	
	protected String fieldDelimiter; 
	
	protected String tupleDelimiter;

	public CsvBaseConfig() {
		super();
	}
	
	public CsvBaseConfig(String fileLocation, String fileName) {
		super();
		this.fileLocation = fileLocation;
		this.fileName = fileName;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFieldDelimiter() {
		return fieldDelimiter;
	}

	public void setFieldDelimiter(String fieldDelimiter) {
		this.fieldDelimiter = fieldDelimiter;
	}

	public String getTupleDelimiter() {
		return tupleDelimiter;
	}

	public void setTupleDelimiter(String tupleDelimiter) {
		this.tupleDelimiter = tupleDelimiter;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
