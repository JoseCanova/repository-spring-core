package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.RecordingEntity;

public interface MutableRecordingEntity<K extends Serializable> extends RecordingEntity<K>{
	
	void setRecording(K k);
	
}
