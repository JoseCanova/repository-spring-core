package org.nanotek;

import java.util.Optional;

import org.nanotek.opencsv.PredicateBase;

@FunctionalInterface
public interface Holder<K extends IdBase<K,ID> , ID extends IdBase<?,?>>{
	Optional<ID> on(PredicateBase<K,ID> predicate);
}
