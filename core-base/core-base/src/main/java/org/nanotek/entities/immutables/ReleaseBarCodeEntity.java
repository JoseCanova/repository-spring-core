package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface ReleaseBarCodeEntity<T extends Serializable> {

	T getReleaseBarCode();
}
