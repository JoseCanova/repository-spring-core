package org.nanotek.beans.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.proxy.ProxyBase;

public class ArtistTypeBean
<K extends ImmutableBase<K,ID>,ID extends BaseEntity<?,?>> 
extends ProxyBase<ID>
implements BaseBean<K,ID>{

	private static final long serialVersionUID = -6271568961378072618L;
	
	private ID id;
	
	public ID getId() {
		return id;
	};
	
	public Long artistTypeId; 
	public String name; 
	public Long parent; 
	public Long childOrder; 
	public String description; 
	public String gid;
	
	public ArtistTypeBean() {
	}

	public ArtistTypeBean(ID id) {
		super();
		this.id = id;
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



	public Long getArtistTypeId() {
		return artistTypeId;
	}



	public void setArtistTypeId(Long artistTypeId) {
		this.artistTypeId = artistTypeId;
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
