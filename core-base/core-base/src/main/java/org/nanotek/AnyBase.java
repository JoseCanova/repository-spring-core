package org.nanotek;

import java.util.Optional;

import org.nanotek.beans.entity.Artist;

public  class AnyBase<S extends Base<S> , K extends Comparable<K>> implements Base<S> , Wrapper <S> {

	private static final long serialVersionUID = 8744939030325475864L;

	K value;
	
	Class<? extends K> sClass = null;
	
	

	private <A extends AnyBase<S,K>> AnyBase(K s) {
		Optional<?> sc = Optional.ofNullable(s);
		sc.ifPresentOrElse( v-> {
			try {
				sClass =  (Class<? extends K>) Class.forName(s.getClass().getName()).asSubclass(s.getClass());
				this.value = sClass.asSubclass(sClass).cast(s);
			} catch (ClassNotFoundException e) {
					throw new BaseException(e);
			}
		}, BaseException::new);
	}
	
	public static <S extends Base<S>,K extends Comparable<K>,A extends AnyBase<S,K>> A of(K s) { 
		return   (A) AnyBase.class.cast(new AnyBase<S, K>(s));
	}
	
	public Optional<K> getValue() {
		return Optional.ofNullable(value);
	}
	
	public Class<?> getKClass() {
		return sClass;
	}
	
	public <T extends Base<T>> T asBase() { 
		return (T) AnyBase.class.cast(this);
	}
	
	public static <S extends Base<?>,K extends Comparable<K>> void main (String[] args) { 
		Artist a = new Artist();
//		Long value = a.get();
		System.out.println(a.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				Base theBase = AnyBase.class.cast(obj);
				return this.compareTo(theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}

}
