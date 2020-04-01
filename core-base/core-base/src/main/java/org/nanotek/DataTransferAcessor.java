package org.nanotek;

@FunctionalInterface
public interface DataTransferAcessor<T> {

	<V> V read(T t);
	
}
