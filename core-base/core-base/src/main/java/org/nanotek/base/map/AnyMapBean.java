package org.nanotek.base.map;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.Id;
import org.nanotek.collections.BaseMap;

public abstract class AnyMapBean
<K extends BaseMap<V,D,B>,V extends AnyBase<V,?>,D extends AnyBase<D,?> , B extends Base<?>> 
extends BaseMap<V,D,B> implements Id<B>{

	private static final long serialVersionUID = -4252749472595615364L;
	
	B id;

}
