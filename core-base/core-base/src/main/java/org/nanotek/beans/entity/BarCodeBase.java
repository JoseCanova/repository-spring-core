package org.nanotek.beans.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.nanotek.BaseEntity;
import org.nanotek.entities.BaseBarCodeBaseEntity;
import org.nanotek.entities.MutableBarCodeEntity;

@Entity
@Table(name="bar_code_base", 
indexes= {
		@Index(unique = false , name = "bar_code_idx" , columnList ="bar_code")
	})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "table_id",
	    columnDefinition = "VARCHAR NOT NULL"
	)
public abstract class BarCodeBase<K extends BarCodeBase<K>> 
extends BrainzBaseEntity<K> 
implements BaseBarCodeBaseEntity<K>, MutableBarCodeEntity<String>{

	private static final long serialVersionUID = 3988946185099694426L;

	@NotBlank
	@Column(name="bar_code" , length=255 , nullable=false)
	protected String barCode;
	
	public BarCodeBase() {
	}
	
	public BarCodeBase(@NotBlank String barCode) {
		super();
		this.barCode = barCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
}
