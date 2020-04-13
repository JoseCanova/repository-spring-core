package org.nanotek.entities;

import org.nanotek.entities.immutables.InstrumentNameEntity;

public interface MutableInstrumentNameEntity<K> extends InstrumentNameEntity<K>{
void setInstrumentName(K k);
}
