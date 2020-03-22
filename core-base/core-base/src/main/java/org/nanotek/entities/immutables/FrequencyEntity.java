package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface FrequencyEntity<K extends Serializable> {
	K getFrequency();
}
