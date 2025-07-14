package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.MutableBaseTypeDescriptionEntity;
import org.nanotek.entities.MutableChildOrderEntity;
import org.nanotek.entities.MutableParentEntity;
import org.nanotek.entities.MutableTypeIdEntity;
import org.nanotek.opencsv.CsvValidationGroup;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="base_type",
					indexes= {
							@Index(unique = false , name = "base_type_id_idx" , columnList ="type_id")
						})
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "table_id",
	    columnDefinition = "VARCHAR NOT NULL"
	)
public abstract class BaseType<K extends BaseType<K>> 
extends TypeNamedEntity<K> implements
MutableTypeIdEntity<Long>,
MutableParentEntity<Long>,
MutableChildOrderEntity<Long>,
MutableBaseTypeDescriptionEntity<BaseTypeDescription<?>>{

	private static final long serialVersionUID = -6795816207025448078L;
    
	@BrainzKey (entityClass = BaseType.class, pathName = "typeId")
	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="type_id" , nullable = false)
	public Long typeId;
	
	@Column(name="parent")
	public Long parent; 
	
	@Column(name="childOrder")
	public Long childOrder;

	@OneToOne(optional=true,orphanRemoval = true,cascade = CascadeType.ALL)
	@JoinTable(
			  name = "base_type_description_join", 
			  joinColumns = @JoinColumn(name = "base_type_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "description_id",referencedColumnName = "id"))
	public BaseTypeDescription<?> baseTypeDescription; 
	
	public BaseType() {
	}

	public BaseType(@NotNull Long typeId) {
		this.typeId = typeId;
	}
	
	public BaseType(@NotNull UUID gid, @NotBlank String name) {
		super(gid , name);
	}

	public BaseType(@NotNull Long typeId , @NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
		this.typeId = typeId;
	}

	@BrainzKey(entityClass = BaseType.class, pathName = "typeId")
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Long getChildOrder() {
		return childOrder;
	}

	public void setChildOrder(Long childOrder) {
		this.childOrder = childOrder;
	}

	public BaseTypeDescription<?> getBaseTypeDescription() {
		return baseTypeDescription;
	}

	public void setBaseTypeDescription(BaseTypeDescription<?> baseTypeDescription) {
		this.baseTypeDescription = baseTypeDescription;
	}

}
