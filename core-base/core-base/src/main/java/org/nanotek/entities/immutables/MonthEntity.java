package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface MonthEntity<K extends Serializable> {
		K getMonth();
}
