package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface MediumEntity<K extends Serializable> {
K getMedium();
}