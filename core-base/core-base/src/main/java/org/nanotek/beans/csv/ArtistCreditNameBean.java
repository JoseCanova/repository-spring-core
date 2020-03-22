package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.ArtistCreditName;

public class ArtistCreditNameBean
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{


	private static final long serialVersionUID = -625201514069517695L;

	private Long artistCreditId;
	
	private Long position;
	
	private Long artistId; 
	
	private String name; 
	
	private String joinPhrase;

	
	
	public ArtistCreditNameBean(ID id) {
		super();
		this.id = id;
	}

	public ArtistCreditNameBean() {
		super();
	}

	public Long getArtistCreditId() {
		return artistCreditId;
	}

	public void setArtistCreditId(Long artistCreditId) {
		this.artistCreditId = artistCreditId;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJoinPhrase() {
		return joinPhrase;
	}

	public void setJoinPhrase(String joinPhrase) {
		this.joinPhrase = joinPhrase;
	}

	ID id; 
	
	@Override
	public ID getId() {
		return id;
	}

	
}
