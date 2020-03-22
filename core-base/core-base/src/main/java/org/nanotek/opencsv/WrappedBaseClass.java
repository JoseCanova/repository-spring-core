package org.nanotek.opencsv;

import org.apache.commons.beanutils.WrapDynaBean;
import org.nanotek.IdBase;
import org.nanotek.WrappedEntityBase;
import org.nanotek.beans.csv.BaseBean;

public class WrappedBaseClass <ID extends IdBase<?,?>> extends WrapDynaBean implements WrappedEntityBase<ID>{

	private static final long serialVersionUID = 1676627942338335870L;

	public WrappedBaseClass(Object instance) {
		super(instance);
	}

	
}
