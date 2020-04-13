package org.nanotek.entities.immutables;

import java.io.Serializable;
import java.util.Set;

public interface TrackEntitySet<S> {

	Set<S> getTracks();
	
}
