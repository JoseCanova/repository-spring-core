package org.nanotek.opencsv.base;

import org.nanotek.IdBase;
import org.nanotek.beans.csv.AreaBean;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.opencsv.WrappedBaseClass;

public class WrappedBaseBean<ID extends IdBase<?,?>> extends WrappedBaseClass<ID>{

	private static final long serialVersionUID = 4115182730636624097L;

	public WrappedBaseBean(IdBase<?,?> idBase) {
		super(idBase);
	}
	

}
