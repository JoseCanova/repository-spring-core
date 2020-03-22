package org.nanotek;

import java.util.Optional;

public interface Mutable<K extends IdBase<K,ID>, ID extends IdBase<?,?>,R extends Result<?,ID>> extends Holder<K,ID> {
	default Optional<R> mutate (ID i){ 
		return Optional.empty();
	}
}
