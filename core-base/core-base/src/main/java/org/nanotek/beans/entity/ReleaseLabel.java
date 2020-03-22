package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.LongBase;

@Entity
@Table(name="release_label")
public class ReleaseLabel implements LongBase<ReleaseLabel> {

	private static final long serialVersionUID = -4336246677898584112L;
	
	private Long id; 
	private Long release; 
	private Long label; 
	private String catalogNumber; 
	
	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getRelease() {
		return release;
	}

	public void setRelease(Long release) {
		this.release = release;
	}

	public Long getLabel() {
		return label;
	}

	public void setLabel(Long label) {
		this.label = label;
	}

	public String getCatalogNumber() {
		return catalogNumber;
	}

	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

}
