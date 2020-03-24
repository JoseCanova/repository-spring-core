package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="label_name")
public class LabelName<K extends LabelName<K>> extends BrainzBaseEntity<K> {

	
	private String name; 
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
