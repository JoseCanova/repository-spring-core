package org.nanotek.entities.metamodel;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;

public class EntityBrainzMetaModel<X,Y>{

	private Class<X> metaModelClass;

	private Class<Y> entityClass;

	private Set<AttributeMetaModel<?>> attributeMetaModel;

	Graph<EntityBrainzMetaModel<?,?>, MetaModelEdge>  modelGraph;

	public EntityBrainzMetaModel() {
		super();
		attributeMetaModel = new HashSet<>();

	}

	public EntityBrainzMetaModel(Class<X> metaModelClass, Class<Y> entityClass) {
		super();
		this.metaModelClass = metaModelClass;
		this.entityClass = entityClass;
		attributeMetaModel = new HashSet<>();

	}

	public Class<X> getMetaModelClass() {
		return metaModelClass;
	}

	public void setMetaModelClass(Class<X> metaModelClass) {
		this.metaModelClass = metaModelClass;
	}

	public Class<Y> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<Y> entityClass) {
		this.entityClass = entityClass;
	}

	public Graph<EntityBrainzMetaModel<?,?>, MetaModelEdge>  getModelGraph() {
		return modelGraph;
	}

	public void setModelGraph(Graph<EntityBrainzMetaModel<?,?>, MetaModelEdge>  modelGraph) {
		this.modelGraph = modelGraph;
	}

	public Set<AttributeMetaModel<?>> getAttributeMetaModel() {
		return attributeMetaModel;
	}

	public void setAttributeMetaModel(Set<AttributeMetaModel<?>> attributeMetaModel) {
		this.attributeMetaModel = attributeMetaModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entityClass == null) ? 0 : entityClass.hashCode());
		result = prime * result + ((metaModelClass == null) ? 0 : metaModelClass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityBrainzMetaModel other = (EntityBrainzMetaModel) obj;
		if (entityClass == null) {
			if (other.entityClass != null)
				return false;
		} else if (!entityClass.equals(other.entityClass))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EntityBrainzMetaModel [entityClass=" + entityClass + "]";
	}

	
}