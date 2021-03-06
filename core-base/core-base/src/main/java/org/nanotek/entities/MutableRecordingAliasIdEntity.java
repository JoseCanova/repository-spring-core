package org.nanotek.entities;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public interface MutableRecordingAliasIdEntity<K> {
	
	K getRecordingAliasId();
	
	void setRecordingAliasId(@NotNull K id);

}
