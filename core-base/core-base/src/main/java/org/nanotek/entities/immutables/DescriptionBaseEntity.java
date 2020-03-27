package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface DescriptionBaseEntity<K extends Serializable> {
K getDescription();
}
