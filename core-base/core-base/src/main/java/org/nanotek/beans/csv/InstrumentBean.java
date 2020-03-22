package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.Instrument;

public class InstrumentBean
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{


	private static final long serialVersionUID = -6916258778573566572L;
	
	private ID id;
	
	public ID getId() { 
		return id;
	}
	
	public Long instrumentId; 
	public String gid; 
	public String name; 
	public Long type; 
	public Integer editsPending; 
	public String lastUpdatead; 
	public String comment; 
	public String description;
	
	public InstrumentBean() {
		super(Instrument.class);
	}

	

	public InstrumentBean(Class<ID> id) {
		super(id);
	}



	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Integer getEditsPending() {
		return editsPending;
	}

	public void setEditsPending(Integer editsPending) {
		this.editsPending = editsPending;
	}

	public String getLastUpdatead() {
		return lastUpdatead;
	}

	public void setLastUpdatead(String lastUpdatead) {
		this.lastUpdatead = lastUpdatead;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Long getInstrumentId() {
		return instrumentId;
	}


	public void setInstrumentId(Long instrumentId) {
		this.instrumentId = instrumentId;
	}
	

}
