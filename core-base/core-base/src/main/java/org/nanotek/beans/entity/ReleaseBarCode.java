package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseReleaseBarCodeEntity;
import org.nanotek.entities.MutableReleaseEntity;

@Entity
@DiscriminatorValue(value = "ReleaseBarCode")
public class ReleaseBarCode<K extends ReleaseBarCode<K>> 
extends BarCodeBase<K> 
implements BaseReleaseBarCodeEntity<K> ,  MutableReleaseEntity<Release>{

	private static final long serialVersionUID = 2946106607354210235L;

	@NotNull
	@OneToOne(mappedBy = "barCode" , optional = false , fetch = FetchType.LAZY)
	private Release release;
	
	public ReleaseBarCode() {
	}

	public ReleaseBarCode(@NotBlank String barCode) {
		super(barCode);
	}

	public ReleaseBarCode(@NotBlank String barCode , @NotNull Release release) {
		super();
		this.release = release;
		this.barCode = barCode;
	}

	public Release getRelease() {
		return release;
	}

	public void setRelease(Release release) {
		this.release = release;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((release == null) ? 0 : release.hashCode());
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
		ReleaseBarCode other = (ReleaseBarCode) obj;
		if (release == null) {
			if (other.release != null)
				return false;
		} else if (!release.equals(other.release))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReleaseBarCode [barCode=" + barCode + ", id=" + id + "]";
	}

	@Override
	public void setBarCode(String B) {
		this.barCode = B;
	}

}
