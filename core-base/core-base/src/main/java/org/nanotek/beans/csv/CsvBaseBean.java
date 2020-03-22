package org.nanotek.beans.csv;

import org.nanotek.Id;
import org.nanotek.IdBase;
import org.nanotek.opencsv.base.WrappedBaseBean;

public class CsvBaseBean<K extends IdBase<?,?>> 
extends WrappedBaseBean<K>
implements Id<K> {

	private static final long serialVersionUID = -1465843449151457466L;

	private K id;

	private Class<?> baseClass;
	
	public CsvBaseBean() {
		super(null);
	}
	
	@SuppressWarnings("unchecked")
	public CsvBaseBean(Class<?> idBase) {
		super(IdBase.prepareBeanInstance(idBase.asSubclass(IdBase.class)));
		baseClass = idBase;
	}
	
	@SuppressWarnings("unchecked")
	private static IdBase<?,?> prepareBeanInstance(Class<?> idBase) { 
		return IdBase.prepareBeanInstance(idBase.asSubclass(IdBase.class));
	}

	@Override
	public K getId() {
		return id;
	}
	
	public static void main(String[] args) { 
		CsvBaseBean opt =   new CsvBaseBean(TrackBean.class);
		TrackBean.class.asSubclass(CsvBaseBean.class);
		
		
		 System.out.println(opt);
	}

}
