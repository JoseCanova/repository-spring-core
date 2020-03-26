package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseBarCodeEntity;

public interface MutableReleaseBarCodeEntity<T extends  Serializable> extends ReleaseBarCodeEntity<T> {

	 void setReleaseBarCode(T t);
			 }
