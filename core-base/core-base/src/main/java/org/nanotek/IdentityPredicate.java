package org.nanotek;

import org.apache.commons.collections4.Predicate;

public interface IdentityPredicate<T> extends Predicate<T> {
		@Override
		default boolean evaluate(T object) {
			return false;
		}
}
