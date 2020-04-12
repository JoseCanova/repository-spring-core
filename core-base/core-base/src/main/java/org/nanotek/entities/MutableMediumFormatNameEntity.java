package org.nanotek.entities;

import org.nanotek.entities.immutables.MediumFormatNameEntity;

public interface MutableMediumFormatNameEntity<K> extends MediumFormatNameEntity<K> {
 void setMediumFormatName(K k);
}
