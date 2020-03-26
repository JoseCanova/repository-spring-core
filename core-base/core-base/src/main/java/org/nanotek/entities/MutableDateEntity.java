package org.nanotek.entities;

import java.io.Serializable;

public interface MutableDateEntity <Y extends Serializable , M extends Serializable , D extends Serializable> 
extends MutableYearEntity<Y>,MutableMonthEntity<M>,MutableDayEntity<D>{	
}
