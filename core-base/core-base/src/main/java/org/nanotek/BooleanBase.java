package org.nanotek;

import java.util.Optional;

import org.nanotek.opencsv.PredicateBase;

public interface BooleanBase<K extends IdBase<K,ID> , ID extends IdBase<?,?>> extends  Holder<K,ID> {
	Optional<ID> compute (PredicateBase<K, ID>  predicate);
	
	@Override
	default Optional<ID> on(PredicateBase<K, ID> predicate) {
		return compute(predicate);
	}
}
