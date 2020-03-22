package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface RecordingIdEntity<K extends Serializable> {
	K getRecordingId();
}
