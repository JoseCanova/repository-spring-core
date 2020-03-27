package org.nanotek.opencsv;

import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.IdBase;
import org.nanotek.beans.csv.AreaBean;

public class CsvBaseBean<ID extends Base<?>> 
extends WrappedBaseClass<ID>{

	private static final long serialVersionUID = -1465843449151457466L;

	private ID id;

	protected Class<ID> baseClass;
	
	protected PropertyChangeSupport propertyChangeSupport = null;
	
	private Map<Class<?> , String> interfaceMap;
	
	public CsvBaseBean() {
		super(prepareBeanInstance(Base.class));
		baseClass = (Class<ID>) Base.class;
		id =  baseClass.cast(getInstance());
		interfaceMap = new HashMap<>();
	}
	
	public CsvBaseBean(Class<ID> class1) {
		super(CsvBaseBean.prepareBeanInstance(class1.asSubclass(IdBase.class)));
		baseClass = class1;
		id =  class1.cast(getInstance());
	}
	
	
	@SuppressWarnings("unchecked")
	private static IdBase<?,?> prepareBeanInstance(Class<?> idBase) { 
		return IdBase.prepareBeanInstance(idBase.asSubclass(IdBase.class));
	}

	public ID getId() {
		return id;
	}
		
	
	public static void main(String[] args) { 
		AreaBean opt =   new AreaBean();
		opt.setAreaId(1000L);
		opt.setType(1000L);
		System.out.println("");
	}

	public Optional<PropertyChangeSupport> getPropertyChangeSupport() {
		return Optional.ofNullable(propertyChangeSupport);
	}
	
	public String getAtributeName(Class<?> clazz) {
			return interfaceMap.get(clazz);
	}
	
	public void registryMethod(Class<?> clazz, String atributeName) {
		interfaceMap.put(clazz, atributeName);
	}

	public CsvBaseBean<?> getCsvBaseBean(){
		return this;
	}
}
