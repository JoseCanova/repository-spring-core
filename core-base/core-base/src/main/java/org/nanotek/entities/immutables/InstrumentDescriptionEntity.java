package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface InstrumentDescriptionEntity<T extends Serializable> {
T getInstrumentDescription();
}
