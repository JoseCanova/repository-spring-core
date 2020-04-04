package org.nanotek;

import java.util.Optional;

public interface BooleanBase<K extends IdBase<K,ID> , ID extends IdBase<?,?>> extends  Holder<K,ID> , ImmutableBase<K,ID>{

	Optional<ID> compute (PredicateBase<K, ID>  predicate);

	@Override
	default <V> Optional<? super V> on() {
		return Optional.empty();
	}
}
