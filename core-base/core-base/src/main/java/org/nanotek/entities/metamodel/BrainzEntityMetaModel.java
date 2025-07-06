package org.nanotek.entities.metamodel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.metamodel.EntityType;

import org.jgrapht.Graph;
import org.nanotek.EntityTypeSupport;
import org.nanotek.beans.EntityBeanInfo;
import org.nanotek.proxy.map.BrainzEntityType;
import org.nanotek.proxy.map.BrainzMetaBuddy;

public class BrainzEntityMetaModel<X,Y>{

	private Class<Y> metaModelClass;

	private Class<X> entityClass;
	
	private EntityType<X> entityType;

	private Set<AttributeMetaModel<?>> attributeMetaModel;

	private Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge>  modelGraph;
	
	private EntityTypeSupport<?,X> entityTypeSupport;
	
	private EntityBeanInfo<?> entityTypeBeanInfo;


	public BrainzEntityMetaModel() {
		super();
		attributeMetaModel = new HashSet<>();
	}

	public EntityTypeSupport<?, X> getEntityTypeSupport() {
		return entityTypeSupport;
	}

	public EntityBeanInfo<?> getEntityTypeBeanInfo() {
		return entityTypeBeanInfo;
	}

	public <K extends EntityType<X>> BrainzEntityMetaModel(Class<Y> metaModelClass, Class<X> entityClass,EntityType<?> entityType1) {
		super();
		this.metaModelClass = metaModelClass;
		this.entityClass = entityClass;
		prepareMetaModelEntityInfo(metaModelClass,entityType1);
		this.entityType = BrainzEntityType.<K,X>with(entityType1);
		attributeMetaModel = new HashSet<>();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void prepareMetaModelEntityInfo(Class<Y> metaModelClass2, EntityType<?> entityType1) {
		this.entityTypeSupport = BrainzMetaBuddy.with(metaModelClass).newInstance(entityType1);	
		entityTypeBeanInfo = new EntityBeanInfo(entityTypeSupport.getClass()); 
	}

	public BrainzEntityMetaModel(Class<Y> metaModelClass, Class<X> entityClass) {
		super();
		this.metaModelClass = metaModelClass;
		this.entityClass = entityClass;
		attributeMetaModel = new HashSet<>();
	}

	public Class<Y> getMetaModelClass() {
		return metaModelClass;
	}

	public void setMetaModelClass(Class<Y> metaModelClass) {
		this.metaModelClass = metaModelClass;
	}

	public Class<X> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<X> entityClass) {
		this.entityClass = entityClass;
	}

	public Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge>  getModelGraph() {
		return modelGraph;
	}

	public void setModelGraph(Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge>  modelGraph) {
		this.modelGraph = modelGraph;
	}

	public Set<AttributeMetaModel<?>> getAttributeMetaModel() {
		return attributeMetaModel;
	}

	public void setAttributeMetaModel(Set<AttributeMetaModel<?>> attributeMetaModel) {
		this.attributeMetaModel = attributeMetaModel;
	}
	
	public EntityType<X> getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType<X> entityType) {
		this.entityType = entityType;
	}
	
	public String getEntityClassName() {
		return entityClass.getSimpleName();
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
		BrainzEntityMetaModel other = (BrainzEntityMetaModel) obj;
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