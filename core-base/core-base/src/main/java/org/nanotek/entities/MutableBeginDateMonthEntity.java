package org.nanotek.entities;

import java.io.Serializable;

public interface MutableBeginDateMonthEntity<T extends Serializable> {
	
	public void setBeginDateMonth(T t);
	public T getBeginDateMonth();
}
