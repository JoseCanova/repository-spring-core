package org.nanotek.opencsv;

import org.apache.commons.beanutils.WrapDynaBean;
import org.nanotek.Base;

public class WrappedBaseClass <ID extends Base<?>> extends WrapDynaBean{

	private static final long serialVersionUID = 1676627942338335870L;

	public WrappedBaseClass(Object instance) {
		super(instance);
	}

	
}
