package org.nanotek;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.apache.commons.codec.digest.DigestUtils;


public interface Base<K extends Base<?>> extends Serializable , KongSupplier<K> , Comparable<K>{

	static String hash = "35454B055CC325EA1AF2126E27707052";

	default String md5Digest()  { 
		try { 
			return DigestUtils.md5Hex(new String(toByteArray()));
		}catch(Exception ex) {
			throw new BaseException(ex);
		}
	}
	//TODO: Verify Gson type adapters.
//	default String toJson () 
//	{ 
//		return new Gson().toJson(this);
//	}


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

	static UUID withUUID(Class<?> clazz) { 
		UUID uuid = null;
		try { 
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			ObjectOutputStream oos =  new ObjectOutputStream(bao);
			oos.writeObject(clazz);
			oos.flush();
			uuid = UUID.nameUUIDFromBytes(bao.toByteArray());
			oos.close();
		}catch (Exception ex) { 
			ex.printStackTrace();
		}
		return Optional.ofNullable(uuid).orElseThrow(BaseException::new);
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
			ex.printStackTrace();
		}
		return Optional.ofNullable(uuid).orElseThrow(BaseException::new);
	}

	default byte[] toByteArray() throws Exception{ 
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ObjectOutputStream oos =  new ObjectOutputStream(bao);
		oos.writeObject(this);
		oos.flush();
		return bao.toByteArray();
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
	
	@Override
	default int compareTo(K to) {
		return withUUID().compareTo(to.withUUID());
	}
	
}