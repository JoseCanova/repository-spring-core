package org.nanotek;

import java.io.Serializable;

public interface ReleasePackagingBase<K extends Serializable> {

	K getReleasePackagingId();
	
	void setReleasePackagingId(K k);
	
}
