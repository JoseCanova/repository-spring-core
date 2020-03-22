package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.ReleasePackaging;

public class ReleasePackagingBean
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{

	private static final long serialVersionUID = -6068518463159348252L;


	private ID id; 
	
	public ID getId() { 
		return id;
	}
	
	
	public Long releasePackagingId; 
	public String name; 
	public Long parent; 
	public Long childOrder; 
	public String description; 
	public String gid;
	
	public ReleasePackagingBean(Class<ID> id) {
		super(id);
	}

	public ReleasePackagingBean() {
		super(ReleasePackaging.class);
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

	public Long getReleasePackagingId() {
		return releasePackagingId;
	}

	public void setReleasePackagingId(Long releasePackagingId) {
		this.releasePackagingId = releasePackagingId;
	}


}
