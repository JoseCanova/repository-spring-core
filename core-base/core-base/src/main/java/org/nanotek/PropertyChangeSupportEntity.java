package org.nanotek;

import java.beans.PropertyChangeSupport;
import java.util.Optional;

public interface PropertyChangeSupportEntity<K> {

	default Optional<PropertyChangeSupport> getPropertyChangeSupport(){ 
		return Optional.empty();
	}

}
