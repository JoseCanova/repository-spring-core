package org.nanotek.beans.csv;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.nanotek.BaseInstantiationException;
import org.nanotek.IdBase;
import org.nanotek.ImmutableBase;

public  interface BaseBean<K extends ImmutableBase<K,ID> , ID extends IdBase<?,?>> extends ImmutableBase<K,ID>{
	
	static <K extends BaseBean<?,?>> Optional<K> newBaseBeanInstance(Class<K> clazz) throws BaseInstantiationException { 
		try {
			return Optional.of(clazz.getDeclaredConstructor().newInstance());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new BaseInstantiationException(e);
		}
	}
	static <K extends BaseBean<?,?>, S extends K> Optional<S> newBaseBeanInstance(Class<S> clazz , Object[] args , Class<?>... classArgs  ) throws BaseInstantiationException { 
		try {
			return Optional.of(clazz.getDeclaredConstructor(classArgs).newInstance(args));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new BaseInstantiationException(e);
		}
	}
}
