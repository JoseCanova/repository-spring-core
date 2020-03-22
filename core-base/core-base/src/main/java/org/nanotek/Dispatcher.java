package org.nanotek;

@FunctionalInterface
public interface Dispatcher<K>  {
	void dispatch (K bean);
}
