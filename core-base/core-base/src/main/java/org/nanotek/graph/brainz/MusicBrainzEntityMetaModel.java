package org.nanotek.graph.brainz;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.metamodel.EntityType;

import org.jgrapht.Graph;
import org.nanotek.EntityTypeSupport;
import org.nanotek.beans.EntityBeanInfo;
import org.nanotek.entities.metamodel.AttributeMetaModel;
import org.nanotek.entities.metamodel.MetaModelEdge;
import org.nanotek.proxy.map.BrainzEntityType;
import org.nanotek.proxy.map.BrainzMetaBuddy;

/**
 * Represents a metadata model for a specific MusicBrainz entity, serving as a vertex
 * within a larger graph that defines relationships between different MusicBrainz entity types.
 * This class encapsulates information about an entity's class, its associated meta-model class,
 * JPA {@link EntityType} metadata, and a set of custom {@link AttributeMetaModel}s.
 * It acts as a bridge, integrating concepts from JPA's metamodel with a custom
 * attribute-level metadata framework, all within the context of a JGraphT-based graph structure.
 * The primary purpose is to allow for dynamic introspection, mapping, and traversal
 * of MusicBrainz entity relationships at a metadata level.
 *
 * @param <X> The type of the actual entity (e.g., Artist, Release).
 * @param <Y> The type of the meta-model class associated with the entity.
 */
public class MusicBrainzEntityMetaModel<X,Y>{

	/**
	 * The {@code Class} object representing the meta-model for the entity {@code X}.
	 * This class typically defines constants or methods to access entity attributes
	 * in a type-safe manner.
	 */
	private Class<Y> metaModelClass;

	/**
	 * The {@code Class} object representing the actual MusicBrainz entity (e.g., Artist.class, Release.class).
	 */
	private Class<X> entityClass;
	
	/**
	 * The JPA {@link EntityType} metadata for the entity {@code X}. This provides access to
	 * persistent attributes and relationships as defined by the JPA specification.
	 */
	private EntityType<X> entityType;

	/**
	 * A {@code Set} of custom {@link AttributeMetaModel} instances, each representing metadata
	 * for a specific attribute of the entity {@code X}. This allows for more granular control
	 * and definition beyond standard JPA metamodel capabilities.
	 */
	private Set<AttributeMetaModel<?>> attributeMetaModel;

	/**
	 * The graph (from JGraphT library) to which this {@code MusicBrainzEntityMetaModel} instance
	 * belongs. In this graph, nodes are instances of {@code MusicBrainzEntityMetaModel} (representing
	 * different entity types), and edges are {@link MetaModelEdge} instances, representing relationships
	 * between entity types.
	 */
	private Graph<MusicBrainzEntityMetaModel<?,?>, MetaModelEdge> modelGraph;
	
	/**
	 * A custom utility support class for the entity type, providing additional capabilities
	 * or integrations specific to the Nanotek framework's entity handling.
	 */
	private EntityTypeSupport<?,X> entityTypeSupport;
	
	/**
	 * Bean information (e.g., properties, getters/setters) for the entity type,
	 * typically derived using Java's Introspector or similar mechanisms.
	 */
	private EntityBeanInfo<?> entityTypeBeanInfo;


	/**
	 * Default constructor. Initializes the {@code attributeMetaModel} as an empty {@code HashSet}.
	 */
	public MusicBrainzEntityMetaModel() {
		super();
		attributeMetaModel = new HashSet<>();
	}

	/**
	 * Returns the {@link EntityTypeSupport} instance associated with this entity meta-model.
	 * @return The {@code EntityTypeSupport} for the entity.
	 */
	public EntityTypeSupport<?, X> getEntityTypeSupport() {
		return entityTypeSupport;
	}

	/**
	 * Returns the {@link EntityBeanInfo} instance associated with this entity meta-model.
	 * @return The {@code EntityBeanInfo} for the entity.
	 */
	public EntityBeanInfo<?> getEntityTypeBeanInfo() {
		return entityTypeBeanInfo;
	}

	/**
	 * Constructor to initialize the entity meta-model with its meta-model class, entity class,
	 * and JPA entity type. It also prepares internal entity information using {@link BrainzMetaBuddy}.
	 * @param <K> The specific type of the JPA EntityType.
	 * @param metaModelClass The class representing the meta-model for the entity.
	 * @param entityClass The class representing the actual MusicBrainz entity.
	 * @param entityType1 The JPA {@link EntityType} instance for the entity.
	 */
	public <K extends EntityType<X>> MusicBrainzEntityMetaModel(Class<Y> metaModelClass, Class<X> entityClass,EntityType<?> entityType1) {
		super();
		this.metaModelClass = metaModelClass;
		this.entityClass = entityClass;
		prepareMetaModelEntityInfo(metaModelClass,entityType1);
		this.entityType = BrainzEntityType.<K,X>with(entityType1);
		attributeMetaModel = new HashSet<>();
	}
	
	/**
	 * A private helper method to prepare and initialize {@link EntityTypeSupport} and {@link EntityBeanInfo}
	 * based on the provided meta-model class and JPA entity type.
	 * @param metaModelClass2 The meta-model class.
	 * @param entityType1 The JPA {@link EntityType} instance.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void prepareMetaModelEntityInfo(Class<Y> metaModelClass2, EntityType<?> entityType1) {
		this.entityTypeSupport = BrainzMetaBuddy.with(metaModelClass2).newInstance(entityType1);	
		entityTypeBeanInfo = new EntityBeanInfo(entityTypeSupport.getClass()); // Assuming EntityBeanInfo expects a Class
	}

	/**
	 * Constructor to initialize the entity meta-model with its meta-model class and entity class.
	 * @param metaModelClass The class representing the meta-model for the entity.
	 * @param entityClass The class representing the actual MusicBrainz entity.
	 */
	public MusicBrainzEntityMetaModel(Class<Y> metaModelClass, Class<X> entityClass) {
		super();
		this.metaModelClass = metaModelClass;
		this.entityClass = entityClass;
		attributeMetaModel = new HashSet<>();
	}

	/**
	 * Returns the meta-model class associated with this entity.
	 * @return The {@code Class} object for the meta-model.
	 */
	public Class<Y> getMetaModelClass() {
		return metaModelClass;
	}

	/**
	 * Sets the meta-model class for this entity.
	 * @param metaModelClass The {@code Class} object for the meta-model.
	 */
	public void setMetaModelClass(Class<Y> metaModelClass) {
		this.metaModelClass = metaModelClass;
	}

	/**
	 * Returns the actual entity class.
	 * @return The {@code Class} object for the entity.
	 */
	public Class<X> getEntityClass() {
		return entityClass;
	}

	/**
	 * Sets the actual entity class.
	 * @param entityClass The {@code Class} object for the entity.
	 */
	public void setEntityClass(Class<X> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Returns the graph to which this entity meta-model belongs.
	 * @return The {@code Graph} of {@code MusicBrainzEntityMetaModel} instances.
	 */
	public Graph<MusicBrainzEntityMetaModel<?,?>, MetaModelEdge> getModelGraph() {
		return modelGraph;
	}

	/**
	 * Sets the graph to which this entity meta-model belongs.
	 * @param modelGraph The {@code Graph} of {@code MusicBrainzEntityMetaModel} instances.
	 */
	public void setModelGraph(Graph<MusicBrainzEntityMetaModel<?,?>, MetaModelEdge> modelGraph) {
		this.modelGraph = modelGraph;
	}

	/**
	 * Returns the set of custom attribute meta-models for this entity.
	 * @return A {@code Set} of {@link AttributeMetaModel} instances.
	 */
	public Set<AttributeMetaModel<?>> getAttributeMetaModel() {
		return attributeMetaModel;
	}

	/**
	 * Sets the set of custom attribute meta-models for this entity.
	 * @param attributeMetaModel A {@code Set} of {@link AttributeMetaModel} instances.
	 */
	public void setAttributeMetaModel(Set<AttributeMetaModel<?>> attributeMetaModel) {
		this.attributeMetaModel = attributeMetaModel;
	}
	
	/**
	 * Returns the JPA {@link EntityType} instance for this entity.
	 * @return The {@code EntityType} for the entity.
	 */
	public EntityType<X> getEntityType() {
		return entityType;
	}

	/**
	 * Sets the JPA {@link EntityType} instance for this entity.
	 * @param entityType The {@code EntityType} for the entity.
	 */
	public void setEntityType(EntityType<X> entityType) {
		this.entityType = entityType;
	}
	
	/**
	 * Returns the simple name of the entity class.
	 * @return A {@code String} representing the simple name of the entity class.
	 */
	public String getEntityClassName() {
		return entityClass.getSimpleName();
	}
	
	/**
	 * Computes the hash code for this {@code MusicBrainzEntityMetaModel} based on
	 * its {@code entityClass} and {@code metaModelClass}.
	 * @return The hash code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entityClass == null) ? 0 : entityClass.hashCode());
		result = prime * result + ((metaModelClass == null) ? 0 : metaModelClass.hashCode());
		return result;
	}

	/**
	 * Compares this {@code MusicBrainzEntityMetaModel} to the specified object.
	 * The result is true if and only if the argument is not null and is a
	 * {@code MusicBrainzEntityMetaModel} object that represents the same entity class.
	 * @param obj The object to compare with.
	 * @return {@code true} if the objects are the same; {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MusicBrainzEntityMetaModel other = (MusicBrainzEntityMetaModel) obj;
		if (entityClass == null) {
			if (other.entityClass != null)
				return false;
		} else if (!entityClass.equals(other.entityClass))
			return false;
		// The original equals implementation only checked entityClass,
		// but hashCode includes metaModelClass. For consistency, if metaModelClass
		// is also part of the identity, it should be included here too.
		// For now, adhering to the provided logic, which only checks entityClass.
		return true;
	}

	/**
	 * Returns a string representation of this {@code MusicBrainzEntityMetaModel},
	 * primarily showing the simple name of the entity class.
	 * @return A string representation of the object.
	 */
	@Override
	public String toString() {
		return "EntityBrainzMetaModel [entityClass=" + entityClass.getSimpleName() + "]"; // Changed to simpleName for cleaner output
	}

}