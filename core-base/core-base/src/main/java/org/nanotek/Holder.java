package org.nanotek;

import java.util.Optional;

@FunctionalInterface
public interface Holder<K extends IdBase<K,ID> , ID extends IdBase<?,?>>{
	Optional<ID> on(PredicateBase<K,ID> predicate);
}
