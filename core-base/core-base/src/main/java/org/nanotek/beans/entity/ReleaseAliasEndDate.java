package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.nanotek.entities.BaseReleaseAliasEndDateEntity;
import org.nanotek.entities.MutableEndDateEntity;
import org.nanotek.entities.MutableEndDayEntity;
import org.nanotek.entities.MutableEndMonthEntity;
import org.nanotek.entities.MutableEndYearEntity;

@Entity
@DiscriminatorValue(value="ReleaseAliasEndDate")
public class ReleaseAliasEndDate<K extends ReleaseAliasEndDate<K>> 
extends EndDateBase<K> 
implements BaseReleaseAliasEndDateEntity<K>,
MutableEndDateEntity<Integer>,
MutableEndYearEntity<Integer>,MutableEndMonthEntity<Integer>,MutableEndDayEntity<Integer>{

	private static final long serialVersionUID = -2316725967469275402L;

	@OneToOne(mappedBy="releaseAliasEndDate")
	private ReleaseAlias releaseAlias;
	
	public ReleaseAliasEndDate() {
	}

	public ReleaseAliasEndDate(Integer year) {
		super(year);
	}

	public ReleaseAliasEndDate(Integer year, Integer month) {
		super(year, month);
	}

	public ReleaseAliasEndDate(Integer year, Integer month, Integer day) {
		super(year, month, day);
	}

	public ReleaseAlias getReleaseAlias() {
		return releaseAlias;
	}

	public void setReleaseAlias(ReleaseAlias releaseAlias) {
		this.releaseAlias = releaseAlias;
	}
	
}
