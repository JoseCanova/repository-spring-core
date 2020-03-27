package org.nanotek.beans.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.BaseTypeDescription;
import org.nanotek.entities.BaseAreaTypeBean;
import org.nanotek.opencsv.CsvBaseBean; 

public class AreaTypeBean
<K extends BaseBean<K,AreaType<?>>,ID extends AreaType<?>> 
extends CsvBaseBean<ID>
implements BaseAreaTypeBean<K> {

	private static final long serialVersionUID = -6271568961378072618L;

	public Long typeId; 
	public String name; 
	public Long parent; 
	public Long childOrder; 
	public String description; 
	public String gid;


	public AreaTypeBean() {
		super(prepareClass());
	}

	private  static <A extends AreaType<?> , B extends A> Class<A> prepareClass() {
		try {
			return (Class<A>) Class.forName(AreaType.class.getName()).asSubclass(AreaType.class);
		}catch(Exception ex) {throw new BaseException();}
	}

	public Long getTypeId() {
		return typeId;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
		getId().setName(name);
	}


	public Long getParent() {
		return parent;
	}


	public void setParent(Long parent) {
		this.parent = parent;
		getId().setParent(parent);
	}


	public Long getChildOrder() {
		return childOrder;
	}


	public void setChildOrder(Long childOrder) {
		this.childOrder = childOrder;
		getId().setChildOrder(childOrder);
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
		Optional.ofNullable(description).ifPresent(d ->{
			BaseTypeDescription td = new BaseTypeDescription();
			td.setDescription(description);
		});
	}


	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}


	@Override
	public int compareTo(K to) {
		return withUUID().compareTo(to.withUUID());
	}

	@Override
	public boolean equals(Object obj) {
		boolean b = Optional.ofNullable(obj).isPresent();
		if (b) {
			Base theBase = AreaTypeBean.class.cast(obj);
			return this.compareTo(theBase) == 0;}
		return false;
	}

	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}

	public static void main(String[] args) {
		AreaTypeBean a = new AreaTypeBean();
		a.setTypeId(1000L);
		System.out.println();
	}
	
}
