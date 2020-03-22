package org.nanotek.beans.csv;

import javax.validation.constraints.NotNull;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.ArtistCredit;

@SuppressWarnings("serial")
public class ArtistCreditBean
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{

	ID id;
	
	public ID getId() { 
		return id;
	}
	
	@NotNull
	Long artistCreditId; 
	@NotNull
	String name; 
	@NotNull
	Integer artistCount;
	@NotNull
	Integer refCount; 
	@NotNull
	String created; 
	@NotNull
	Integer editsPending;
	
	public ArtistCreditBean(){
		super(ArtistCredit.class);
	}
	
	
	public ArtistCreditBean(Class<ID> id) {
		super(id);
	}


	public ArtistCreditBean(Long artistCreditId, String name, Integer artistCount, Integer refCount, String created,
			Integer editsPending) {
		super();
		this.artistCreditId = artistCreditId;
		this.name = name;
		this.artistCount = artistCount;
		this.refCount = refCount;
		this.created = created;
		this.editsPending = editsPending;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getArtistCount() {
		return artistCount;
	}

	public void setArtistCount(Integer artistCount) {
		this.artistCount = artistCount;
	}

	public Integer getRefCount() {
		return refCount;
	}

	public void setRefCount(Integer refCount) {
		this.refCount = refCount;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Integer getEditsPending() {
		return editsPending;
	}

	public void setEditsPending(Integer editsPending) {
		this.editsPending = editsPending;
	}

	public Long getArtistCreditId() {
		return artistCreditId;
	}

	public void setArtistCreditId(Long artistCreditId) {
		this.artistCreditId = artistCreditId;
	}

}
