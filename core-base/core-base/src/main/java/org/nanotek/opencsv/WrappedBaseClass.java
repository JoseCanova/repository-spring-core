package org.nanotek.opencsv;

import org.nanotek.IdBase;
import org.nanotek.WrappedEntityBase;
import org.springframework.beans.BeanWrapperImpl;

public class WrappedBaseClass <ID extends IdBase<?,?>> extends BeanWrapperImpl implements WrappedEntityBase<ID>{

	private static final long serialVersionUID = 1676627942338335870L;

	public WrappedBaseClass(Object instance) {
		super(instance);
	}

	
}
