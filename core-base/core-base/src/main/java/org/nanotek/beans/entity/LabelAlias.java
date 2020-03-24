package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="label_alias")
public class LabelAlias<K extends LabelAlias<K>> extends  BrainzBaseEntity<K> {
	
	private Long label; 
	private Long name; 
	private String locale;
	private Long aliasId;
	
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
