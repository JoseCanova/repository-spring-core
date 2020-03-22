package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.nanotek.entities.BaseReleaseAliasBeginDateEntity;

@Entity
@DiscriminatorValue(value = "ReleaseAliasBeginDate")
public class ReleaseAliasBeginDate<K extends ReleaseAliasBeginDate<K>> 
extends DatableBase<K,Integer,Integer,Integer> 
implements BaseReleaseAliasBeginDateEntity<K>{

	private static final long serialVersionUID = -3004596106076682952L;
	
	@OneToOne(mappedBy="beginDate")
	private ReleaseAlias releaseAlias;
	
	public ReleaseAliasBeginDate() {
	}

	public ReleaseAliasBeginDate(Integer year) {
		super(year);
	}

	public ReleaseAliasBeginDate(Integer year, Integer month) {
		super(year, month);
	}

	public ReleaseAliasBeginDate(Integer year, Integer month, Integer day) {
		super(year, month, day);
	}
	
	public ReleaseAlias getReleaseAlias() {
		return releaseAlias;
	}

	public void setReleaseAlias(ReleaseAlias releaseAlias) {
		this.releaseAlias = releaseAlias;
	}

}