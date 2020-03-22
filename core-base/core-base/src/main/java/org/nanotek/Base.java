package org.nanotek;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import javax.persistence.IdClass;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.gson.Gson;

public interface Base<K extends Base<?>> extends Serializable , KongSupplier<K>{

	static String hash = "35454B055CC325EA1AF2126E27707052";

	default String md5Digest()  { 
		return DigestUtils.md5Hex(withUUID().toString());
	}

	default String toJson () 
	{ 
		return new Gson().toJson(this);
	}


	default <T> T newAnyType(Supplier<T> supplier)
	{ 
		return supplier.get();
	}

	default <T> T ofNullable(T dest , Supplier<T> supplier) 
	{ 
		return Optional.ofNullable(dest).orElseGet(supplier); 
	}

	default <T extends Base<?>> T newType(Supplier<T> baseSupplier)
	{ 
		return baseSupplier.get();
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

	default Base<?> newInstance() throws BaseInstantiationException { 
		try {
			return this.getClass().getDeclaredConstructor(Void.class).newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new BaseInstantiationException(e);
		}
	}

	
	static <KID extends Base<?> , ID extends Base<?>> Optional<KID> newInstance(Class<KID> clazz , Class<ID> idClazz, ID instance) throws BaseInstantiationException { 
		try {
			return Base.newInstance(clazz, new Serializable[] {instance},idClazz);
		} catch (Exception e) {
			throw new BaseInstantiationException(e);
		}
	}
	
	static <KID extends Base<?> , ID extends Base<?>> Optional<KID> newInstance(Class<KID> clazz , Class<ID> idClazz) throws BaseInstantiationException { 
		try {
			return Base.newInstance(clazz, new Serializable[] {Base.newInstance(idClazz).get()},idClazz);
		} catch (Exception e) {
			throw new BaseInstantiationException(e);
		}
	}
	
	static <K extends Base<?>> Optional<K> newInstance(Class<K> clazz) throws BaseInstantiationException { 
		try {
			return Optional.of(clazz.getDeclaredConstructor().newInstance());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new BaseInstantiationException(e);
		}
	}
	

	static <K extends Base<?>> Optional<K> newInstance(Class<K> clazz , Serializable[] args , Class<?>... classArgs  ) throws BaseInstantiationException { 
		try {
			return Optional.of(clazz.getDeclaredConstructor(classArgs).newInstance(args));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new BaseInstantiationException(e);
		}
	}

	static <K extends Base<?>> Optional<K> NULL_VALUE() {
		return Optional.empty();
	}

	static <K extends Base<?>> Optional<K> NULL_VALUE(Class<K> clazz) {
		return Optional.empty();
	}

}