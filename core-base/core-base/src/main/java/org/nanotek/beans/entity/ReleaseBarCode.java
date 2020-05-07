package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseReleaseBarCodeEntity;
import org.nanotek.entities.MutableBarCodeEntity;
import org.nanotek.entities.MutableReleaseEntity;

@Entity
@DiscriminatorValue(value = "ReleaseBarCode")
public class ReleaseBarCode<K extends ReleaseBarCode<K>> 
extends BarCodeBase<K> 
implements BaseReleaseBarCodeEntity<K> ,  
MutableReleaseEntity<Release<?>>,
MutableBarCodeEntity<String>{

	private static final long serialVersionUID = 2946106607354210235L;

	@OneToOne(mappedBy = "releaseBarCode" , optional = true , fetch = FetchType.LAZY)
	public Release<?> release;
	
	public ReleaseBarCode() {
	}

	public ReleaseBarCode(@NotBlank String barCode) {
		super(barCode);
	}

	public ReleaseBarCode(@NotBlank String barCode , @NotNull Release<?> release) {
		super();
		this.release = release;
		this.barCode = barCode;
	}

	@Override
	public Release<?> getRelease() {
		return release;
	}

	@Override
	public void setRelease(Release<?> k) {
		this.release = k;
	}

	@Override
	public String toString() {
		return "ReleaseBarCode [release=" + release + ", barCode=" + barCode + "]";
	}


}
