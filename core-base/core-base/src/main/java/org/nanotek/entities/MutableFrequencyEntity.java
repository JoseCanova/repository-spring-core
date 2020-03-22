package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.FrequencyEntity;

public interface MutableFrequencyEntity<K extends Serializable> extends FrequencyEntity<K> {
		void setFrequency(K k);
}
