package org.nanotek;

import java.util.Optional;

public interface PredicateBase<T extends IdBase<T,ID> , ID extends IdBase<?,?>> extends Base<ID>{
	Optional<ID> evaluate(T immutable);
}
