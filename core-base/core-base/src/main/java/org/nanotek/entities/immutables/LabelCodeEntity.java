package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface LabelCodeEntity<T extends Serializable> {
 T getLabelCode();
}
