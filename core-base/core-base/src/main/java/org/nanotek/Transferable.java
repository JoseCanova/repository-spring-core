package org.nanotek;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import javax.validation.constraints.NotNull;

public interface Transferable {

	static <S extends Base ,  SOURCE extends Collection<S>, DEST extends Collection<S>>
	DEST transferElements(
			@NotNull SOURCE sourceCollection,
			@NotNull Supplier<DEST> collectionFactory) {

		DEST result = collectionFactory.get();
		
		sourceCollection.stream().forEach(p -> result.add(Optional.of(p).filter(t -> t !=null).get()));
		
		return result;
	}
}
