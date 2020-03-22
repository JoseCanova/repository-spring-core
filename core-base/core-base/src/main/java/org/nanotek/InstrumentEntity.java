package org.nanotek;

import java.io.Serializable;

public interface InstrumentEntity<K extends Serializable> {

	K getInstrument();
	
}
