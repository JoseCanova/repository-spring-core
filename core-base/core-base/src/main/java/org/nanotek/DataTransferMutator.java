package org.nanotek;

public interface DataTransferMutator<T> extends DataTransferAcessor<T>{
	<V> void write(T t , V v );
}
