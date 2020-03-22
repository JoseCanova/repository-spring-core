package org.nanotek.opencsv.base;

import org.nanotek.IdBase;
import org.nanotek.beans.csv.AreaBean;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.opencsv.WrappedBaseClass;

public class WrappedBaseBean<ID extends IdBase<?,?>> extends WrappedBaseClass<ID>{

	public WrappedBaseBean(IdBase<?,?> idBase) {
		super(idBase);
	}
	
	public static void main(String[] args) { 
		Area area =  IdBase.prepareBeanInstance(Area.class);
		BaseBean instance = BaseBean.newBaseBeanInstance(AreaBean.class.asSubclass(IdBase.class) , new Object[] {area} , IdBase.class ).get();	
	}

}
