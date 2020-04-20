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

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.entities.BaseCountBaseEntity;
import org.nanotek.entities.MutableCountEntity;

@Entity
@Table(name="long_count_base",
				indexes= {
						@Index(unique = false , name = "long_count_field_idx" , columnList ="count")
					})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "table_id",
	    columnDefinition = "VARCHAR NOT NULL"
	)
public class LongCountBase<K extends LongCountBase<K>> 
extends CountBase<K> implements BaseCountBaseEntity<K>,
MutableCountEntity<Long>
{

	private static final long serialVersionUID = 7485578978795549390L;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@Column(name="count",nullable=false)
	public Long count;

	public LongCountBase(@NotNull Long count) {
		super();
		this.count = count;
	}

	public LongCountBase() {
	}

	@Override
	public Long count() {
		return count;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
