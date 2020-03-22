package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.ReleaseGroupIdEntity;

public interface MutableReleaseGroupIdEntity<K extends Serializable> extends ReleaseGroupIdEntity<K> {

	

	
	void setReleaseGroupId(K k);
}
