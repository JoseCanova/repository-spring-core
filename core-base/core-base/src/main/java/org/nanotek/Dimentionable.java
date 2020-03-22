package org.nanotek;

import java.io.Serializable;

public interface Dimentionable<K extends Serializable> {
	
	K getDimension();
	
}
