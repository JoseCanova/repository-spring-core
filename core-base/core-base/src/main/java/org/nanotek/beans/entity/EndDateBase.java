package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.entities.validation.DateComposition;

@Entity
@Table(name="end_dates",
				indexes= {
						@Index(unique = false , name = "end_date_year_idx" , columnList ="end_year")
					}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "class_name" ,  columnDefinition = "VARCHAR NOT NULL")
@DateComposition(groups= {PrePersistValidationGroup.class})
public class EndDateBase <K extends EndDateBase<K>>
extends BrainzBaseEntity<K>  {


	private static final long serialVersionUID = -4544159118931690162L;

	@Column(name="end_year", nullable = false , columnDefinition = "SMALLINT NOT NULL")
	public Integer endYear;
	
	@Column(name="end_month" , nullable = false , columnDefinition = "SMALLINT NOT NULL")
	public Integer endMonth;
	
	@Column(name="end_day" , nullable = false , columnDefinition = "SMALLINT NOT NULL")
	public Integer endDay;

	public EndDateBase() {
	}

	public EndDateBase(@NotNull Integer endYear) {
		super();
		this.endYear = endYear;
		this.endMonth = 1;
		this.endDay = 1;
	}
	
	public EndDateBase(@NotNull  @NotNull  Integer endYear, @NotNull Integer endMonth) {
		super();
		this.endYear = endYear;
		this.endMonth = endMonth;
		this.endDay = 1;
	}
	
	public EndDateBase(@NotNull  Integer endYear, @NotNull Integer endMonth, @NotNull Integer endDay) {
		super();
		this.endYear = endYear;
		this.endMonth = endMonth;
		this.endDay = endDay;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
		verifyMonthDay();
	}

	private void verifyMonthDay() {
		if(endMonth == null)
			endMonth = 1;
		if(endDay == null)
			endDay = 1;
	}

	public Integer getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(Integer endMonth) {
		this.endMonth = endMonth;
		if(this.endDay==null)
			this.endDay=1;
	}

	public Integer getEndDay() {
		return endDay;
	}

	public void setEndDay(Integer endDay) {
		this.endDay = endDay;
	}

	@Override
	public String toString() {
		return "EndDateBase [endYear=" + endYear + ", endMonth=" + endMonth + ", endDay=" + endDay + "]";
	}

	
}