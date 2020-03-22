package org.nanotek;

import java.io.Serializable;

import org.nanotek.entities.immutables.DayEntity;
import org.nanotek.entities.immutables.MonthEntity;
import org.nanotek.entities.immutables.YearEntity;

public interface DatableBaseEntity <Y extends Serializable , M extends Serializable , D extends Serializable> 
extends YearEntity<Y>,MonthEntity<M>,DayEntity<D>{	
}
