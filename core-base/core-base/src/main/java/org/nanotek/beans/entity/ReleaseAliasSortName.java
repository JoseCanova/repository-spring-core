package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.nanotek.entities.BaseReleaseAliasSortNameEntity;

@Entity
@DiscriminatorValue(value = "ReleaseAliasSortName")
public class ReleaseAliasSortName<K extends ReleaseAliasSortName<K>> extends SortNameBase<K> implements BaseReleaseAliasSortNameEntity<K>
{

	private static final long serialVersionUID = -7823743704784694617L;
	
	@OneToOne(mappedBy = "sortName")
	private ReleaseAlias releaseAlias;
	
	public ReleaseAliasSortName(@NotBlank String sortName) {
		super(sortName);
	}

	public ReleaseAliasSortName() {
	}

	public ReleaseAlias getReleaseAlias() {
		return releaseAlias;
	}

	public void setReleaseAlias(ReleaseAlias releaseAlias) {
		this.releaseAlias = releaseAlias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((releaseAlias == null) ? 0 : releaseAlias.hashCode());
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
		ReleaseAliasSortName other = (ReleaseAliasSortName) obj;
		if (releaseAlias == null) {
			if (other.releaseAlias != null)
				return false;
		} else if (!releaseAlias.equals(other.releaseAlias))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReleaseAliasSortName [releaseAlias=" + releaseAlias + ", sortName=" + sortName + ", id=" + id + "]";
	}
}
