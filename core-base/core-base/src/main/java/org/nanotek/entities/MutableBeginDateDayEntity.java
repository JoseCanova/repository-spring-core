package org.nanotek.entities;

import java.io.Serializable;

public interface MutableBeginDateDayEntity<K extends Serializable> 
{
		public void setBeginDateDay(K k);
		public K getBeginDateDay();
}
