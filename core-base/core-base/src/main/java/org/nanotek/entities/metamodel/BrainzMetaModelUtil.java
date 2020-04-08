package org.nanotek.entities.metamodel;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.metamodel.internal.MetamodelImpl;
import org.jgrapht.Graph;
import org.reflections.Reflections;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BrainzMetaModelUtil implements InitializingBean{

	@Autowired
	BrainzGraphModel graphModel;
	
	@PersistenceContext
	EntityManager entityManager;

	@Autowired 
	Reflections reflections;
	
	MetamodelImpl metaModel;
	
	public BrainzMetaModelUtil() {
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		metaModel = (MetamodelImpl) entityManager.getMetamodel();
		Set<Class<?>>types =  reflections.getTypesAnnotatedWith(StaticMetamodel.class);
		processEntityBrainzMetaModel(types,reflections);
	}

	
	private void processEntityBrainzMetaModel(Set<Class<?>> types, Reflections reflections) {
		types.stream().forEach(c->{
			EntityBrainzMetaModel<?,?> brainzMetaModel = prepareEntityBrainzMetaModel(c,reflections);
		});
	}


	private EntityBrainzMetaModel<?, ?> prepareEntityBrainzMetaModel(Class<?> c, Reflections reflections) {
		StaticMetamodel sm = c.getAnnotation(StaticMetamodel.class);
		Class<?> entityClass = sm.value();
		Class<?> metaModelClass = c;
		Set<AttributeMetaModel<?>> attributes = getMetamodelAttributes(c.getFields());
		return null;
	}


	@SuppressWarnings("unchecked")
	private <X> Set<AttributeMetaModel<?>> getMetamodelAttributes(Field[] fields) {
		return Stream
		.of(fields)
		.filter(f ->  {
				return Stream
				.of(f.getType())
				.filter(cls->cls.getPackageName().contains("javax.persistence.metamodel"))
				.findAny().isPresent();
			})
		.map(f->
					{
						Attribute<?,X> metaModelAttibute = Attribute.class.cast(f.getType());
						AttributeMetaModel<X> model = verifyTypeDeclaration1(f.getGenericType(), f.getName());
						model.setMetaModelAttibute(metaModelAttibute);
						return model;
					})
		.collect(Collectors.toSet());
	}

	@SuppressWarnings("unchecked")
	private<X> AttributeMetaModel<X> verifyTypeDeclaration1(Type parametrizeType,String name) {
		ParameterizedType ptype = ParameterizedType.class.cast(parametrizeType);
		Type[] baseBeanTypeArguments = ptype.getActualTypeArguments();
		Class<X> propertyType = Class.class.cast(baseBeanTypeArguments[0]);
		return new AttributeMetaModel<>(name,propertyType);
		
	}

	
	
	private Attribute<?,?> getAttributeIdAny(Field f) {
		return null;
	}


	class EntityBrainzMetaModel<X,Y>{
		
		private Class<X> metaModelClass;
		
		private Class<Y> entityClass;
		
		private Set<AttributeMetaModel<?>> attributeMetaModel;
		
		Graph<Y,?> modelGraph;
		
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

		public Graph<Y, ?> getModelGraph() {
			return modelGraph;
		}

		public void setModelGraph(Graph<Y, ?> modelGraph) {
			this.modelGraph = modelGraph;
		}

		public Set<AttributeMetaModel<?>> getAttributeMetaModel() {
			return attributeMetaModel;
		}

		public void setAttributeMetaModel(Set<AttributeMetaModel<?>> attributeMetaModel) {
			this.attributeMetaModel = attributeMetaModel;
		}
		
	}
	
	class AttributeMetaModel<X>{
		
		private String name;
		
		private Class<X> attributeClass;
		
		private Attribute<?,X>metaModelAttibute;
		
		public AttributeMetaModel(String name, Class<X> attributeClass, Attribute<?, X> metaModelAttibute) {
			super();
			this.name = name;
			this.attributeClass = attributeClass;
			this.metaModelAttibute = metaModelAttibute;
		}

		public AttributeMetaModel() {
			super();
		}

		public AttributeMetaModel(String name, Class<X> attribute) {
			super();
			this.attributeClass = attribute;
			this.name = name;
		}

		public Class<?> getAttributeClass() {
			return attributeClass;
		}

		public String getName() {
			return name;
		}

		public Attribute<?, X> getMetaModelAttibute() {
			return metaModelAttibute;
		}

		public void setMetaModelAttibute(Attribute<?, X> metaModelAttibute) {
			this.metaModelAttibute = metaModelAttibute;
		}

	}
	
	
}
