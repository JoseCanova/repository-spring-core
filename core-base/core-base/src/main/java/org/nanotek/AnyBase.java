package org.nanotek;

import java.util.Optional;

@SuppressWarnings("unchecked")
public  class AnyBase<S extends Base<S> , K extends Comparable<K>> implements Base<S> , Wrapper <S> {

	private static final long serialVersionUID = 8744939030325475864L;

	K value = null;
	
	Class<? extends K> sClass = null;
	
	

	public <A extends AnyBase<S,K>> AnyBase(K s) {
		Optional<?> sc = Optional.ofNullable(s);
		sc.ifPresentOrElse( v-> {
			try {
				sClass =  (Class<? extends K>) Class.forName(s.getClass().getName()).asSubclass(s.getClass());
				this.value = sClass.asSubclass(sClass).cast(s);
			} catch (ClassNotFoundException e) {
					throw new BaseException(e);
			}
		},BaseException::new);
	}
	
	public static <S extends Base<S>,K extends Comparable<K>,A extends AnyBase<S,K>> A of(K s) { 
		return    (A) new AnyBase<S, K>(s);
	}
	
	public Optional<K> getValue() {
		return Optional.ofNullable(value);
	}
	
	public void setValue(K k) {
		this.value = k;
	}
	
	public Class<?> getKClass() {
		return sClass;
	}
	
	public <T extends Base<T>> T asBase() { 
		return (T) AnyBase.class.cast(this);
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				if (obj.getClass().equals(AnyBase.class)) {
				AnyBase theBase = AnyBase.class.cast(obj);
				return this.compareTo((S) theBase) == 0;}
			}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}

}
