package org.nanotek.opencsv.service;

@FunctionalInterface
public interface EntityClassAccessor<T> {

	T entity();
}
