package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.Base;

@SuppressWarnings("serial")
@Entity
@Table(name="label_alias")
public class LabelAlias implements Base<LabelAlias> {
	
	private Long id; 
	private Long label; 
	private Long name; 
	private String locale;
	private Long aliasId;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getLabel() {
		return label;
	}
	
	public void setLabel(Long label) {
		this.label = label;
	}
	
	public Long getName() {
		return name;
	}
	
	public void setName(Long name) {
		this.name = name;
	}
	
	public String getLocale() {
		return locale;
	}
	
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Long getAliasId() {
		return aliasId;
	}

	public void setAliasId(Long aliasId) {
		this.aliasId = aliasId;
	}

}
