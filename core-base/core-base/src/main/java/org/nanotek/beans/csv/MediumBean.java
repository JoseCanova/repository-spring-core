package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.Medium;

public class MediumBean<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{


	private static final long serialVersionUID = -8141072962299778762L;

	private ID id;
	
	public ID getId() { 
		return id;
	}
	
	public Long mediumId;
	
	public Long release; 
	
	public Long position;
	
	public Long format; 
	
	public String name; 
	
	public String editsPending; 
	
	public String lastUpdated; 
	
	public Integer trackCount; 
	
	
	public MediumBean(Class<ID> id) {
		super(id);
	}

	public MediumBean() {
		super(Medium.class);
	}

	public Long getRelease() {
		return release;
	}

	public void setRelease(Long release) {
		this.release = release;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public Long getFormat() {
		return format;
	}

	public void setFormat(Long format) {
		this.format = format;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEditsPending() {
		return editsPending;
	}

	public void setEditsPending(String editsPending) {
		this.editsPending = editsPending;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Integer getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(Integer trackCount) {
		this.trackCount = trackCount;
	}

	public Long getMediumId() {
		return mediumId;
	}

	public void setMediumId(Long mediumId) {
		this.mediumId = mediumId;
	}
	
}
