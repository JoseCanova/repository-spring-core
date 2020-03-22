package org.nanotek.stream;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.nanotek.BaseException;
import org.nanotek.IdBase;

public class IdBaseStream<K extends IdBase<K,?>> implements IdBaseStreamBuilder<K>{

Class<K> clazz;
	
	ArrayList<K> iterable;
	
	@Override
	public void accept(K value) {
		Class<?> classe = clazz;
		Optional.ofNullable(value).filter(v -> value.getClass().equals(classe)).orElseThrow(BaseException::new);
		iterable.add(value);
	}

	@Override
	public Builder<K> add(K t) {
		 accept(t);
		 return this;
	}
	
	@Override
	public Stream<K> build() {
		return iterable.stream();
	}
	
	private IdBaseStream(Class<K> clazz) {
		iterable = new  ArrayList<K>();
		this.clazz = clazz;
	}

	public static  <K extends IdBase<K,?>> IdBaseStream<K> of(Class<K> clazz) {
		return new IdBaseStream<K>(clazz);
	}

}
