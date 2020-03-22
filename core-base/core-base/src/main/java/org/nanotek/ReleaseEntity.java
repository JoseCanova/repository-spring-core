package org.nanotek;

import java.io.Serializable;

public interface ReleaseEntity<K extends Serializable> {

	K getRelease();
	
}
