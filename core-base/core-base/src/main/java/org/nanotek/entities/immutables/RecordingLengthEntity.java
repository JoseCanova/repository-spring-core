package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface RecordingLengthEntity<T extends Serializable> {
	
	T getRecordingLength();

}
