package org.nanotek.beans.csv;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.opencsv.CsvBaseBean;
import org.nanotek.opencsv.CsvResult;

@SuppressWarnings("serial")
public class ArtistCreditBean
<K extends ImmutableBase<K,ID>,ID extends BaseEntity<?,?>> 
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

	@Override
	public int compareTo(K to) {
		return withUUID().compareTo(to.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				Base theBase = this.getClass().cast(obj);
				return this.compareTo(theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}
	
}
