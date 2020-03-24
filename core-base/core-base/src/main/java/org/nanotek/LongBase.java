package org.nanotek;

import java.util.Optional;

public abstract class LongBase<K extends EntityBase<K,Long>> implements EntityBase<K,Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8877249498059736628L;
	
	@Override
	public int compareTo(K to) {
		return withUUID().compareTo(to.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				Base theBase = this.getClass().cast(obj);
				return this.compareTo(theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}
}
