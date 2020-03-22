package org.nanotek;

import java.util.Optional;

public interface Kong<K extends Kong<?>> {
	default <J extends K> Optional<J> get(J j){
		return Optional.of(j);
	}
}