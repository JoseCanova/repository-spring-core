package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface MediumCountEntity<K extends Serializable> {
K getMediumCount();
}