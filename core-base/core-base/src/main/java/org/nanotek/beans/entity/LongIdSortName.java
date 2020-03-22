package org.nanotek.beans.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.nanotek.entities.MutableSortNameEntity;

@Entity
@DiscriminatorValue(value = "LongIdSortNameEntity")
public class LongIdSortName<K extends LongIdSortName<K>> extends 
LongIdName<K> implements MutableSortNameEntity<String> {

	private static final long serialVersionUID = -3442197714885490996L;

	@NotBlank
	@Column(name="sort_name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	protected String sortName;
	
	public LongIdSortName() {
	}

	public LongIdSortName(@NotBlank String name) {
		super(name);
	}
	
	public LongIdSortName(@NotBlank String name , @NotBlank String sortName) {
		super(name);
		this.sortName = sortName;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((sortName == null) ? 0 : sortName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LongIdSortName other = (LongIdSortName) obj;
		if (sortName == null) {
			if (other.sortName != null)
				return false;
		} else if (!sortName.equals(other.sortName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LongIdSortNameEntity [sortName=" + sortName + ", name=" + name + ", id=" + id + "]";
	}
	
}
