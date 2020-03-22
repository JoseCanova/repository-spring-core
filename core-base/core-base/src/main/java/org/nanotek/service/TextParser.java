package org.nanotek.service;


public abstract class TextParser {
	
	protected String fileLocation;
	
	protected String fieldDelimiter; 
	
	protected String tupleDelimiter;

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
}
