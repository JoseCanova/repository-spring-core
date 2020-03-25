package org.nanotek.entities;

import java.io.Serializable;

public interface MutableBeginDateYearEntity<T extends Serializable>{
	
	public void setBeginDateYear(T t);
	public T getBeginDateYear();

}
