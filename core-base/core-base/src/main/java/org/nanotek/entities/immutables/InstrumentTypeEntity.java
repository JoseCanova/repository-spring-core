package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface InstrumentTypeEntity<T extends Serializable> {
	T getInstrumentType();
}
