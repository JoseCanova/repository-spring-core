package org.nanotek;

import java.util.function.Supplier;

public interface Kong<K> extends Supplier<Kong<K>>{
	@Override
	default Kong<K> get() {
		return this;
	}
}