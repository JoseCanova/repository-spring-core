package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.entities.immutables.LongIdNameEntityBase;

@SuppressWarnings("serial")
@Entity
@Table(name="track_name")
public class TrackName<K extends TrackName<K>> extends BrainzBaseEntity<K>{

	
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
