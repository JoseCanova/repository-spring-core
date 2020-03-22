package org.nanotek;

@FunctionalInterface
public interface Evaluative<K> {
	void evaluate(K k);
}
