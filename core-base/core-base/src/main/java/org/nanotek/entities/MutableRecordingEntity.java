package org.nanotek.entities;

import org.nanotek.entities.immutables.RecordingEntity;

public interface MutableRecordingEntity<K> extends RecordingEntity<K>{
	
	void setRecording(K k);
	
}
