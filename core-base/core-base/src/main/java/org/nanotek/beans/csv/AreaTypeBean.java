package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.AreaType; 

public class AreaTypeBean
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{

	private static final long serialVersionUID = -6271568961378072618L;
	
	private ID id; 
	
	@Override
	public ID getId() {
		return id;
	}
	


	public Long areaTypeId; 
	public String name; 
	public Long parent; 
	public Long childOrder; 
	public String description; 
	public String gid;
	
	public AreaTypeBean() {
		super(AreaType.class);
	}

	public AreaTypeBean(ID id) {
		super();
		this.id = id;
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


	public Long getAreaTypeId() {
		return areaTypeId;
	}


	public void setAreaTypeId(Long areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

}
