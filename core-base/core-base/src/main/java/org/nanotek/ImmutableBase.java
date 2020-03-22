package org.nanotek;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.UUID;

import org.nanotek.beans.csv.BaseBean;

public interface ImmutableBase <K extends IdBase<K,ID>,ID extends Serializable> extends KongSupplier<K>  , IdBase<K,ID> {

	default UUID getUUID() { 
		return withUUID();
	}

	static <S extends K , K extends ImmutableBase<S,ID> , ID extends Serializable> Optional<K> newImmutableBase(Class<S> class1) throws BaseInstantiationException { 
		try {
			return Optional.of(class1.getDeclaredConstructor().newInstance());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new BaseInstantiationException(e);
		}
	}

	static <K extends ImmutableBase<K,ID> ,ID extends Serializable> Optional<K> newInstance(Class<K> clazz , Object[] args , Class<?>... classArgs) throws BaseInstantiationException { 
		try {
			return Optional.of(clazz.getDeclaredConstructor(classArgs).newInstance(args));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new BaseInstantiationException(e);
		}
	}

	default UUID withUUID() { 
		UUID uuid = null;
		try { 
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			ObjectOutputStream oos =  new ObjectOutputStream(bao);
			oos.writeObject(this);
			oos.flush();
			uuid = UUID.nameUUIDFromBytes(bao.toByteArray());
			oos.close();
		}catch (Exception ex) { 
		}
		return Optional.ofNullable(uuid).orElseThrow(BaseException::new);
	}

	static <K extends ImmutableBase<?, ?>> Optional<K> NULL_VALUE() {
		return Optional.empty();
	}

	static <K extends ImmutableBase<K,ID>,ID extends Serializable> Optional<K> OPTIONAL_INSTANCE(Class<K> clazz) {
		return ImmutableBase.newInstance(clazz,new Object[] {});
	}
	
}
