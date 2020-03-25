package org.nanotek.opencsv;

import java.beans.PropertyChangeSupport;

import org.nanotek.Id;
import org.nanotek.IdBase;
import org.nanotek.beans.csv.TrackBean;
import org.nanotek.opencsv.base.WrappedBaseBean;

public class CsvBaseBean<K extends IdBase<?,?>> 
extends WrappedBaseBean<K>
implements Id<K> {

	private static final long serialVersionUID = -1465843449151457466L;

	private K id;

	protected Class<?> baseClass;
	
	private PropertyChangeSupport pcs = null;
	
	public CsvBaseBean() {
		super(null);
	}
	
	@SuppressWarnings("unchecked")
	public CsvBaseBean(Class<?> idBase) {
		super(CsvBaseBean.prepareBeanInstance(idBase.asSubclass(IdBase.class)));
		baseClass = idBase;
		addPropertySupport(this);
	}
	
	private void addPropertySupport(CsvBaseBean<K> csvBaseBean) {
		pcs = new PropertyChangeSupport(this);
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
