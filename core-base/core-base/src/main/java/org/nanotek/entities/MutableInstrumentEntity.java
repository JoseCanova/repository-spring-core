package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.InstrumentEntity;

public interface MutableInstrumentEntity<K extends Serializable> extends InstrumentEntity<K> {
     void setInstrument(K k);
}
