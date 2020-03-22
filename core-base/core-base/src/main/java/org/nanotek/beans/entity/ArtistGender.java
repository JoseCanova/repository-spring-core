package org.nanotek.beans.entity;

import org.nanotek.LongBase;

@SuppressWarnings("serial")
public class ArtistGender implements LongBase<ArtistGender>{

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
