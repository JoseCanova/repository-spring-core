package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.LongBase;

@SuppressWarnings("serial")
@Entity
@Table(name="track_list")
public class TrackList implements LongBase<TrackList> {

	private Long id; 
	private Long count; 
	
	@Override
	public Long getId() {
		return id;
	}

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
