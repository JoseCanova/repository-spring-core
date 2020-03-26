package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.InstrumentIdEntity;

public interface MutableInstrumentIdEntity<T extends Serializable> extends InstrumentIdEntity<T>{

	void setInstrumentId(T t);
}
