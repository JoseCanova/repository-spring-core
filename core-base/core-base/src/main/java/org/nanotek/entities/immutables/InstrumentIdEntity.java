package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface InstrumentIdEntity<K extends Serializable> {
	K getInstrumentId();
}
