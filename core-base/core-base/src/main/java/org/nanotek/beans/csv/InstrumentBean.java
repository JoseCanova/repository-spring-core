package org.nanotek.beans.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.Instrument;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.proxy.ProxyBase;

public class InstrumentBean
<K extends ImmutableBase<K,ID>,ID extends BaseEntity<?,?>> 
extends ProxyBase<ID>
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
	
	@Override
	public int compareTo(K to) {
		return withUUID().compareTo(to.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				Base theBase = this.getClass().cast(obj);
				return this.compareTo(theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}

}
