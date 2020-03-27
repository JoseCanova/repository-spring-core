package org.nanotek.beans.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.ArtistAliasType;
import org.nanotek.opencsv.CsvBaseBean;
import org.nanotek.opencsv.CsvResult;

public class ArtistAliasTypeBean
<K extends ImmutableBase<K,ID>,ID extends BaseEntity<?,?>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{

	private static final long serialVersionUID = 901207660901713562L;
	
	private ID id;
	
	public ID getId() { 
		return id;
	}
	
	public Long artistAliasTypeId; 
	public String name; 
	public Long parent; 
	public Long childOrder; 
	public String description; 
	public String gid;
	
	public ArtistAliasTypeBean() {
		super(ArtistAliasType.class);
	}

	public ArtistAliasTypeBean(Class<ID> id) {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Long getChildOrder() {
		return childOrder;
	}

	public void setChildOrder(Long childOrder) {
		this.childOrder = childOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}


	public Long getArtistAliasTypeId() {
		return artistAliasTypeId;
	}


	public void setArtistAliasTypeId(Long artistAliasTypeId) {
		this.artistAliasTypeId = artistAliasTypeId;
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
