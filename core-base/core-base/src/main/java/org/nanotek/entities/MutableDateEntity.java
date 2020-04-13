package org.nanotek.entities;

import java.io.Serializable;

public interface MutableDateEntity <Y , M , D> 
extends MutableYearEntity<Y>,MutableMonthEntity<M>,MutableDayEntity<D>{	
}
