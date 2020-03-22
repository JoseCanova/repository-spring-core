package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface DayEntity<K extends Serializable> {
		K getDay();
}
