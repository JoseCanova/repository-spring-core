package org.nanotek;

import java.io.Serializable;

public interface TypeEntity<K extends Serializable> {
		K getType();
}
