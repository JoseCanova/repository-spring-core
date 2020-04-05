package org.nanotek.beans.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="composite_dates",
				indexes= {
						@Index(unique = false , name = "date_year_idx" , columnList ="year")
					}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "class_name" ,  columnDefinition = "VARCHAR NOT NULL")
public class DatableBase <K extends DatableBase<K,Y,M,D>, Y extends Serializable , M extends Serializable , D extends Serializable>
extends BrainzBaseEntity<K>  {


	private static final long serialVersionUID = -4544159118931690162L;

	@NotNull
	@Column(name="year", nullable = false , columnDefinition = "SMALLINT NOT NULL")
	protected Y year;
	
	@Column(name="month" , nullable = false , columnDefinition = "SMALLINT NOT NULL")
	protected M month;
	
	@Column(name="day" , nullable = false , columnDefinition = "SMALLINT NOT NULL")
	protected D day;

	public DatableBase() {
	}

	public DatableBase(@NotNull Y year) {
		super();
		this.year = year;
	}
	
	public DatableBase(@NotNull  @NotNull  Y year, @NotNull M month) {
		super();
		this.year = year;
		this.month = month;
	}
	
	public DatableBase(@NotNull  Y year, @NotNull M month, @NotNull D day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}

}