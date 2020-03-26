package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface InstrumentCommentEntity<T extends Serializable> {
	
	T getInstrumentComment();
}
