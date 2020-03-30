package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface BaseTypeDescriptionEntity<K extends Serializable> {
	K getBaseTypeDescription();
}
