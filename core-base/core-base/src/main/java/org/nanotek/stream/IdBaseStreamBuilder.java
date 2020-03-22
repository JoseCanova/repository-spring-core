package org.nanotek.stream;

import java.util.stream.Stream;

import org.nanotek.IdBase;

public interface IdBaseStreamBuilder<K extends IdBase<?,?>> extends Stream.Builder<K>{

}
