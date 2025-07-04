package org.nanotek.beans.entity.graph;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.nanotek.beans.entity.SequenceLongBase;

@Entity
@Table(name="entity_name")
public class EntityName
extends SequenceLongBase<EntityName, Long>{

	private static final long serialVersionUID = -2157607773399578200L;

	@Column(name="entity_name", nullable=false , updatable = true)	
	public String name;
	
	@OneToMany(mappedBy = "entityName" , fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<PriorityResult> priorityResults;
	
	
	public EntityName() {
		super();
	}
	
	public EntityName(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<PriorityResult> getPriorityResult() {
		return Optional.ofNullable(priorityResults).orElse(priorityResults = new HashSet<PriorityResult>());
	}
	
	
}
