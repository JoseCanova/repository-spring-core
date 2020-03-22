package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="description_base",
						indexes= {
								@Index(unique = false , name = "description_table_idx" , columnList ="description"),
								@Index(unique = false , name = "description_table_id_idx" , columnList ="table_id")
							})
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "table_id",
	    columnDefinition = "VARCHAR NOT NULL"
	)
public abstract class DescriptionBase<K extends DescriptionBase<K>> extends BrainzBaseEntity<K>  {

	private static final long serialVersionUID = -4976009864905272762L;

	@NotNull
	@Column(name="description", columnDefinition = "VARCHAR NOT NULL" , nullable=false)
	private String description;
	
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
