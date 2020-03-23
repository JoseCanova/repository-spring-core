package org.nanotek.collections;

import org.nanotek.Base;
import org.springframework.beans.factory.InitializingBean;

public abstract class IdBaseMap<K extends Base<K> , ID extends Base<ID>> 
extends BaseMap<K,ID> implements InitializingBean{

	private static final long serialVersionUID = 8945888004557753179L;

	protected K immutable;
	
	public IdBaseMap(K immutable) {
		super(immutable);
		this.immutable = immutable;
	}
	
}
