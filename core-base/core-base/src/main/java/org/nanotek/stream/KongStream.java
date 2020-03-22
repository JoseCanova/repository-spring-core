package org.nanotek.stream;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.nanotek.BaseException;
import org.nanotek.IdBase;
import org.nanotek.Kong;
import org.nanotek.beans.entity.Area;

public class KongStream<S extends K, K extends Kong<?>> implements KongStreamBuilder<S,K> {
	
	Class<S> clazz;
	
	ArrayList<S> iterable;
	
	@Override
	public void accept(S value) {
		Class<?> classe = clazz;
		Optional.ofNullable(value).filter(v -> value.getClass().equals(classe.asSubclass(clazz))).orElseThrow(BaseException::new);
		iterable.add(value);
	}

	@Override
	public Builder<S> add(S t) {
		 accept(t);
		 return this;
	}
	
	@Override
	public Stream<S> build() {
		return iterable.stream();
	}
	
	private KongStream(Class<S> clazz) {
		iterable = new  ArrayList<S>();
		this.clazz = clazz;
	}

	public static  <S extends K, K extends Kong<?>> KongStream<S,K> of(Class<S> clazz) {
		return new KongStream<S,K>(clazz);
	}
	
	public static void main (String[] args) {
		Area area = new Area();
		KongStream.of(Area.class).add(area).build().forEach(a -> a.toString());;
	}
}

