package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

//TODO create a List entity
@SuppressWarnings("serial")
@Entity
@Table(name="track_list")
public class TrackList<K extends TrackList<K>> extends  BrainzBaseEntity<K> {

	private Long count; 
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
