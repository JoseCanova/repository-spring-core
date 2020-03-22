package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.RecordingAliasType;

public class RecordingAliasTypeBean 
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{

	private static final long serialVersionUID = 2632943505939712312L;
	
	private ID id;
	
	public ID getId() { 
		return id;
	}
	
	public Long recordingAliasTypeId; 
	public String name; 
	public Long parent; 
	public Long childOrder; 
	public String description; 
	public String gid;
	

	public RecordingAliasTypeBean(Class<ID> id) {
		super(id);
	}

	public RecordingAliasTypeBean() {
		super(RecordingAliasType.class);
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

	public Long getRecordingAliasTypeId() {
		return recordingAliasTypeId;
	}

	public void setRecordingAliasTypeId(Long recordingAliasTypeId) {
		this.recordingAliasTypeId = recordingAliasTypeId;
	}
	
}
