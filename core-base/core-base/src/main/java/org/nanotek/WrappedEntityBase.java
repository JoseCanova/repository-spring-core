package org.nanotek;

import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.nanotek.opencsv.WrappedBaseClass;

public interface WrappedEntityBase<K extends IdBase<?,?>> extends Wrapper <K> {
	
	static <S extends WrappedBaseClass<I> , I extends IdBase<?,?> , J extends I> Optional<?> newBeanInstance(I instance) throws BaseInstantiationException { 
		try {
			  return WrappedEntityBase.newInstance(WrappedBaseClass.class.asSubclass(WrappedBaseClass.class)  , Arrays.array(instance) , BaseBean.class);
		} catch (Exception e) {
			throw new BaseInstantiationException(e);
		}
	}
	
	static <K extends WrappedEntityBase<S> , I extends BaseBean<?,?> , S extends I> Optional<?> newBaseBeanInstance(Class<I> beanClass,I instance) throws BaseInstantiationException { 
		try {
			return  WrappedEntityBase.newInstance(WrappedBaseClass.class.asSubclass(WrappedEntityBase.class) , new Object[] {beanClass, instance},  beanClass);
		} catch (Exception e) {
			throw new BaseInstantiationException(e);
		}
	}
	
	static <S extends WrappedEntityBase<?>> Optional<S> newInstance(Class<S> clazz , Object[] args , Class<?>... classArgs  ) throws BaseInstantiationException { 
		try {
			return Optional.of(clazz.getDeclaredConstructor(classArgs).newInstance(args));
		} catch (Exception e) {
			throw new BaseInstantiationException(e);
		}
	}
	
}
