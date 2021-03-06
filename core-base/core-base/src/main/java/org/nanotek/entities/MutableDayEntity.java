package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.DayEntity;

public interface MutableDayEntity<K> extends DayEntity<K> {
	void setDay(K k);
}
