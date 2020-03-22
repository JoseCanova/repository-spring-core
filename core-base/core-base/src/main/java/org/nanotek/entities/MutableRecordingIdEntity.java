package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.RecordingIdEntity;

public interface MutableRecordingIdEntity<K extends Serializable> extends RecordingIdEntity<K>{

	void setRecordingId(K k);
	
}
