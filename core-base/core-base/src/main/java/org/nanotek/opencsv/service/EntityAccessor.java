package org.nanotek.opencsv.service;

@FunctionalInterface
public interface EntityAccessor<T> {

	T entity();
}
