package org.nanotek.entities.immutables;

import java.io.Serializable;
import java.util.Set;

public interface TrackEntitySet<S extends Serializable> {

	Set<S> getTracks();
	
}
