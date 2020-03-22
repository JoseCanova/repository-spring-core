package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface DescriptionEntity<K extends Serializable> {
		K getDescription();
}
