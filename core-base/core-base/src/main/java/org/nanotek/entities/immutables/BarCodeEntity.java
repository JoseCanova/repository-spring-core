package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface BarCodeEntity<K extends Serializable> {
  
	K getBarCode();
	
}
