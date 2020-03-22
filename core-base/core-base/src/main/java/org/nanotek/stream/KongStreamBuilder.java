package org.nanotek.stream;

import java.util.stream.Stream;

import org.nanotek.Kong;

public interface KongStreamBuilder<S extends K, K extends Kong<?>> extends Stream.Builder<S> {
}
