package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface RecordingEntity<K extends Serializable> {
	K getRecording();
}
