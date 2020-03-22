package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface YearEntity<K extends Serializable> {
		K getYear();
}
