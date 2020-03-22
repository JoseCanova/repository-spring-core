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

import org.nanotek.entities.MutablePositionEntity;

@Entity
@Table(name="long_position_base",
indexes= {
		@Index(unique = false , name = "long_position_position_idx" , columnList ="position"),
		@Index(unique = false , name = "long_position_table_idx" , columnList ="table_id")
	})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "class_id",
	    columnDefinition = "VARCHAR NOT NULL"
	)
public class LongPositionBase<K extends LongPositionBase<K>> 
extends PositionEntityBase<K> 
implements MutablePositionEntity<Long>{

	private static final long serialVersionUID = 2610811597922933992L;

	@NotNull
	@Column(name="position" , nullable=false)
	protected Long position;
	
	public LongPositionBase() {
	}

	public LongPositionBase(@NotNull Long position) {
		super();
		this.position = position;
	}

	@Override
	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

}
