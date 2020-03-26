package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.InstrumentTypeEntity;

public interface MutableInstrumentTypeEntity<T extends Serializable> extends InstrumentTypeEntity<T>{
	void setInstrumentType(T t);
}
