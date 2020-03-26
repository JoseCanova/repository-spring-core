package org.nanotek.entities;

import java.io.Serializable;

public interface MutableBeginDateEntity <K extends Serializable> 
extends MutableBeginYearEntity<K>,MutableBeginMonthEntity<K>,MutableBeginDayEntity<K>{	
}
