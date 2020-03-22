package org.nanotek;

import java.util.stream.Stream;

@FunctionalInterface
public interface Sender<K> {

	public Stream<?> send(K t);
	
}
