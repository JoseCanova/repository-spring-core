package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.InstrumentType;

public class InstrumentTypeBean 
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{

	

	private static final long serialVersionUID = 245355432039730928L;

	private ID id;
	
	public ID getId() { 
		return id;
	}
	
	public Long instrumentTypeId; 
	
	public String name; 
	
	public Long parent; 
	
	public Long childOrder; 
	
	public String description; 
	
	public String gid; 
	
	
	
	public InstrumentTypeBean(Class<ID> id) {
		super(id);
	}

	public InstrumentTypeBean() {
		super(InstrumentType.class);
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getParent() {
		return parent;
	}


	public void setParent(Long parent) {
		this.parent = parent;
	}


	public Long getChildOrder() {
		return childOrder;
	}


	public void setChildOrder(Long childOrder) {
		this.childOrder = childOrder;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getGid() {
		return gid;
	}


	public void setGid(String gid) {
		this.gid = gid;
	}


	public Long getInstrumentTypeId() {
		return instrumentTypeId;
	}

	public void setInstrumentTypeId(Long instrumentTypeId) {
		this.instrumentTypeId = instrumentTypeId;
	}

}
