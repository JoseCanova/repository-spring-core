package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="begin_dates",
				indexes= {
						@Index(unique = false , name = "begin_date_year_idx" , columnList ="year")
					}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "class_name" ,  columnDefinition = "VARCHAR NOT NULL")
public abstract class BeginDateBase <K extends BeginDateBase<K>>
extends BrainzBaseEntity<K>  {


	private static final long serialVersionUID = -4544159118931690162L;

	@NotNull
	@Column(name="year", nullable = false , columnDefinition = "SMALLINT NOT NULL")
	public Integer beginYear;
	
	@Column(name="month" , nullable = false , columnDefinition = "SMALLINT NOT NULL")
	public Integer beginMonth;
	
	@Column(name="day" , nullable = false , columnDefinition = "SMALLINT NOT NULL")
	public Integer beginDay;

	public BeginDateBase() {
	}

	public BeginDateBase(@NotNull Integer beginYear) {
		super();
		this.beginYear = beginYear;
	}
	
	public BeginDateBase(@NotNull  Integer beginYear, @NotNull Integer beginMonth) {
		super();
		this.beginYear = beginYear;
		this.beginMonth = beginMonth;
	}
	
	public BeginDateBase(@NotNull  Integer beginYear, @NotNull Integer beginMonth, @NotNull Integer beginDay) {
		super();
		this.beginYear = beginYear;
		this.beginMonth = beginMonth;
		this.beginDay = beginDay;
	}

	public Integer getBeginYear() {
		return beginYear;
	}

	public void setBeginYear(Integer beginYear) {
		this.beginYear = beginYear;
	}

	public Integer getBeginMonth() {
		return beginMonth;
	}

	public void setBeginMonth(Integer beginMonth) {
		this.beginMonth = beginMonth;
	}

	public Integer getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(Integer beginDay) {
		this.beginDay = beginDay;
	}

	
	
}