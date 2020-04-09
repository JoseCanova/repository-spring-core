package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.InstrumentDescriptionEntity;

public interface MutableInstrumentDescriptionEntity<T> extends InstrumentDescriptionEntity<T> {
void setInstrumentDescription(T t);
}
