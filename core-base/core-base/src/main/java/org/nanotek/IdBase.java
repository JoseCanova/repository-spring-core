package org.nanotek;

import java.io.Serializable;

import org.nanotek.stream.KongStream;

public interface IdBase<K extends Base<K>,ID extends Serializable> extends Base<K> , Id<ID> {
	
	static <T extends S , S extends IdBase<T,?>>  S prepareBeanInstance(Class<S> clazz) {
		//K, Kong<?>
		return KongStream.of(clazz).add(Base.newInstance(clazz).get()).build().map(i->i).findFirst().get();
	}
	
	default IdBase<K,ID> getIdBase(){
		return this;
	}
}
