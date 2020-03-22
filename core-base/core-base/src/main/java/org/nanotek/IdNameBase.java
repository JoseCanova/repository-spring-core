package org.nanotek;

import java.io.Serializable;

import org.nanotek.entities.MutableNameBase;

public interface IdNameBase<E extends IdNameBase<E , K ,ID> , K extends Serializable, ID extends Serializable> extends  MutableNameBase<K> , IdBase<E,ID>, Id<ID>{

	
	
}
