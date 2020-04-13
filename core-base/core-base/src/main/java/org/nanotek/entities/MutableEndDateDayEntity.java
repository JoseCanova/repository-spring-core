package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.EndDateDayEntity;

public interface MutableEndDateDayEntity<T> extends EndDateDayEntity<T> {

	void setEndDateDay(T t);
	
	
}
