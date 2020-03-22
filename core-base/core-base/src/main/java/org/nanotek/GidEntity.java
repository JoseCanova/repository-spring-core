package org.nanotek;

import java.io.Serializable;

public interface GidEntity<K extends Serializable> {
	K getGid();
}
