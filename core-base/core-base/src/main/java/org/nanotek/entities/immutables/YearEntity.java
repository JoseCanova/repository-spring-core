package org.nanotek.entities.immutables;

import org.nanotek.Dateable;

public interface YearEntity<K> extends Dateable<K>{
		K getYear();
}
