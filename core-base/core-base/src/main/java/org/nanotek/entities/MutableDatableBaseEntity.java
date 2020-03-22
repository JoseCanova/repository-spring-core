package org.nanotek.entities;

import java.io.Serializable;

public interface MutableDatableBaseEntity <Y extends Serializable , M extends Serializable , D extends Serializable> 
extends MutableYearEntity<Y>,MutableMonthEntity<M>,MutableDayEntity<D>{	
}
