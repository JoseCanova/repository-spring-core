package org.nanotek;

import java.util.Optional;

public  class AnyBase<S extends Base<S> , K extends Comparable<K>> implements Base<S> {

	private static final long serialVersionUID = 8744939030325475864L;

	Optional <K> value;
	
	Class<? extends K> sClass = null;
	
	

	private <A extends AnyBase<S,K>> AnyBase(K s) {
		Optional<?> sc = Optional.ofNullable(s);
		sc.ifPresentOrElse( v-> {
			try {
				sClass =  (Class<? extends K>) Class.forName(s.getClass().getName()).asSubclass(s.getClass());
				this.value = Optional.of(sClass.asSubclass(sClass).cast(s));
			} catch (ClassNotFoundException e) {
					throw new BaseException(e);
			}
		}, BaseException::new);
	}
	
	public static <S extends Base<S>,K extends Comparable<K>,A extends AnyBase<S,K>> A of(K s) { 
		return   (A) AnyBase.class.cast(new AnyBase<S, K>(s));
	}
	
	public K get() {
		return value.get();
	}
	
	public Class<?> getKClass() {
		return sClass;
	}
	
	public <T extends Base<T>> T asBase() { 
		return (T) AnyBase.class.cast(this);
	}
	
	public static <S extends Base<?>,K extends Comparable<K>> void main (String[] args) { 
		AnyBase<?,Long> a = AnyBase.of(0L);
		Long value = a.get();
	}
}
