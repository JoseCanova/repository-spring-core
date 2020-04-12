package org.nanotek.entities.immutables;

import org.nanotek.Nameable;

public interface MediumNameEntity<K>  extends Nameable<K>{
K getMediumName();
}
