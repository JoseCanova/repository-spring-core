package org.nanotek.beans.entity;

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

import org.hibernate.validator.constraints.Length;
import org.nanotek.entities.MutableChildOrderEntity;
import org.nanotek.entities.MutableDescriptionEntity;
import org.nanotek.entities.MutableParentEntity;
import org.nanotek.entities.MutableTypeIdEntity;


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
MutableDescriptionEntity<BaseTypeDescription>{

	private static final long serialVersionUID = -6795816207025448078L;

	@NotNull
	@Column(name="type_id" , nullable = false)
	protected Long typeId;
	
	@Column(name="parent")
	protected Long parent; 
	
	@Column(name="childOrder")
	protected Long childOrder;

	@OneToOne(optional=true,orphanRemoval = true)
	@JoinTable(
			  name = "base_type_description_join", 
			  joinColumns = @JoinColumn(name = "base_type_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "description_id",referencedColumnName = "id"))
	protected BaseTypeDescription description; 
	
	public BaseType() {
	}

	public BaseType(@NotNull Long typeId , @NotBlank @Length(min = 1, max = 50) String gid, @NotBlank String name) {
		super(gid, name);
		this.typeId = typeId;
	}
	
	public BaseType(@NotBlank @Length(min = 1, max = 50) String gid, @NotBlank String name) {
		super(gid, name);
	}

	public BaseType(@NotNull String name) {
		super(name);
	}

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

	public BaseTypeDescription getDescription() {
		return description;
	}

	public void setDescription(BaseTypeDescription description) {
		this.description = description;
	}

}
