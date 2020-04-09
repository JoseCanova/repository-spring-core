package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.RecordingIdEntity;

public interface MutableRecordingIdEntity<K> extends RecordingIdEntity<K>{

	void setRecordingId(K k);
	
}
