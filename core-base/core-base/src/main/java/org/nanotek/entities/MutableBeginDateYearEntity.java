package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.BeginDateYearEntity;

public interface MutableBeginDateYearEntity<T extends Serializable> extends BeginDateYearEntity<T>{
	
	 void setBeginDateYear(T t);


}
