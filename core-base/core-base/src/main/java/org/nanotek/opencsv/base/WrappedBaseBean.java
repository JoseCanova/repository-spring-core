package org.nanotek.opencsv.base;

import org.nanotek.Base;
import org.nanotek.IdBase;
import org.nanotek.opencsv.WrappedBaseClass;

public class WrappedBaseBean<ID extends Base<?>> 
extends WrappedBaseClass<ID>{

	private static final long serialVersionUID = 4115182730636624097L;

	public WrappedBaseBean(IdBase<?,?> idBase) {
		super(idBase);
	}
	

}
