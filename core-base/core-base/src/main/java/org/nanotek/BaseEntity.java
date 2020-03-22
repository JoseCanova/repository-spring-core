package org.nanotek;

import java.io.Serializable;
import java.util.Optional;

import org.apache.commons.collections4.Predicate;
import org.assertj.core.util.Arrays;

public interface BaseEntity<K extends IdBase<K,ID> , ID extends Serializable> extends IdentityPredicate<K> , IdBase<K,ID> , Entity<K>{


	static Optional<?> createPkInstance(Class<?> i){
		try {
			return Optional.of(Long.class.getDeclaredConstructors()[0].newInstance(new Object[] {Id.NULL_VALUE()}));
		} catch (Exception e) {
			throw new BaseInstantiationException(e);
		}
	}

	static <T extends BaseEntity<?,?> , S extends T , ID extends Serializable> Optional<S> newBaseEntityInstance(Class<S> clazz , Class<ID> IDC ) 
			throws BaseInstantiationException { 
		try {
			Object[] lp = Arrays.array(BaseEntity.createPkInstance(IDC).get());
			S s = clazz.getDeclaredConstructor(new Class<?>[] {Serializable.class}).newInstance(lp);
			return Optional.of(s);
		} catch (Exception e) {
			throw new BaseInstantiationException(e);
		}
	}
	
	default <T extends BaseEntity<?,?> , S extends T> Optional<S> newInstance(Class<S> clazz ,  ID IDS , Class<ID>IDC ) 
			throws BaseInstantiationException { 
		try {
			return Optional.of(clazz.getDeclaredConstructor(IDC).newInstance(IDS));
		} catch (Exception e) {
			throw new BaseInstantiationException(e);
		}
	}
	
	
	default  Optional<BaseEntity<K,ID>> compute(Predicate<BaseEntity<K,ID>> predicate) {
		boolean eval = Optional.ofNullable(predicate.evaluate(this)).orElseThrow(BaseException::new);
		return eval ? asOptional() : Optional.empty();
	}
	
	
	default Optional<BaseEntity<K,ID>> asOptional() {
		return Optional.of(this);
	}
}
