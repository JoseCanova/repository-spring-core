package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.MonthEntity;

public interface MutableMonthEntity<K extends Serializable>  extends MonthEntity<K>{
		void setMonth(K k);
}
