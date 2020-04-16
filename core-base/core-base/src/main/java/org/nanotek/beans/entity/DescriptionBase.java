package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.nanotek.entities.MutableDescriptionBaseEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Entity
@Table(name="description_base",
						indexes= {
								@Index(unique = false , name = "description_table_idx" , columnList ="description")
							})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "table_id",
	    columnDefinition = "VARCHAR NOT NULL"
	)
public abstract class DescriptionBase<K extends DescriptionBase<K>> 
extends BrainzBaseEntity<K> implements MutableDescriptionBaseEntity<String> {

	private static final long serialVersionUID = -4976009864905272762L;

	@NotNull(groups = {CsvValidationGroup.class,Default.class})
	@Column(name="description", columnDefinition = "VARCHAR NOT NULL" , nullable=false)
	public String description;
	
	public DescriptionBase() {
	}

	public DescriptionBase(@NotNull String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
