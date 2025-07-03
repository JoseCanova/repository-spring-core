package org.nanotek.beans.entity.graph;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.SequenceLongBase;

@Entity
@Table(name="priority_result")
public class PriorityResult
extends SequenceLongBase<PriorityResult, Long> {

	private static final long serialVersionUID = 5175849489258900153L;

	@NotNull	
	@javax.persistence.Column(name="priority", nullable=false, unique=false)
	public Integer priority;
	
	@NotNull	
	@ManyToOne
	public EntityName entityName;
	/**
	 * Default constructor for JPA
	 */
	public PriorityResult() {
		super();
	}
	
	/**
	 * Constructor with priority
	 * @param priority the priority value
	 */
	public PriorityResult(Integer priority) {
		super();
		this.priority = priority;
	}

	
	public Integer getPriority() {
		return priority;
	}
	
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	
	public EntityName getEntityName() {
		return entityName;
	}
	
	public void setEntityName(EntityName entityName) {
		this.entityName = entityName;
	}
	
	@Override
	public String toString() {
		return "PriorityResult [id=" + getId() + "]";
	}

}
