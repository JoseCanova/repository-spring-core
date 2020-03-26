package org.nanotek.entities;

import java.io.Serializable;

public interface MutableEndDateEntity <K extends Serializable> 
extends MutableEndYearEntity<K>,MutableEndMonthEntity<K>,MutableEndDayEntity<K>{	
}
