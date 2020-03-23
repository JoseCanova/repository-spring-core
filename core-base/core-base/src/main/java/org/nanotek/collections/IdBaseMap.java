package org.nanotek.collections;

import org.nanotek.Base;
import org.nanotek.IdBase;
import org.nanotek.WrappedEntityBase;
import org.nanotek.opencsv.ResultHolderBaseMap;
import org.springframework.beans.factory.InitializingBean;

public abstract class IdBaseMap<K extends WrappedEntityBase<ID> , ID extends IdBase<ID,?>,R extends ResultHolderBaseMap<K, ID, ?> 
extends BaseMap<K,ID> implements InitializingBean{

	private static final long serialVersionUID = 8945888004557753179L;

	protected K immutable;
	
	public IdBaseMap(K immutable) {
		super(immutable);
		this.immutable = immutable;
	}
	
}
