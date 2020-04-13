package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.InstrumentEntity;

public interface MutableInstrumentEntity<K> extends InstrumentEntity<K> {
     void setInstrument(K k);
}
