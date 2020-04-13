package org.nanotek.entities.immutables;

import org.nanotek.Nameable;


public interface LabelNameEntity<K> extends Nameable<K>{
 K getLabelName();
}
