package org.nanotek.entities.metamodel;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.metamodel.internal.MetamodelImpl;
import org.jgrapht.Graph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.beans.entity.SequenceLongBase;
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

	Map<Class<?> , EntityBrainzMetaModel<?,?>> metaModelMap;

	public BrainzMetaModelUtil() {
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		metaModel = (MetamodelImpl) entityManager.getMetamodel();
		Set<Class<?>>types =  reflections.getTypesAnnotatedWith(StaticMetamodel.class);
		Map<Class<?> , EntityBrainzMetaModel<?,?>>  tempMap = processEntityBrainzMetaModel(types,reflections);
		metaModelMap = prepareEntityGraph(tempMap);
		Graph<EntityBrainzMetaModel<?,?>, MetaModelEdge> resultingGraph = getModelGraph();
		System.out.println("");
	}

	public EntityBrainzMetaModel<?,?> getMetaModel(Class<?> clazz){
		return metaModelMap.get(clazz);
	}


	public Graph<EntityBrainzMetaModel<?, ?>, MetaModelEdge> getModelGraph() {
		Graph<EntityBrainzMetaModel<?,?>, MetaModelEdge> graph = buildUnDirectedSimpleGraph();
		metaModelMap
		.entrySet()
		.stream()
		.forEach(e ->{
			EntityBrainzMetaModel <?,?> meta = e.getValue();
			Graph<EntityBrainzMetaModel<?,?>, MetaModelEdge> 
			metaModelGraph = meta.getModelGraph();
			metaModelGraph
			.edgeSet()
			.forEach(es ->{
				if(!graph.containsVertex(es.getSource())) {
					graph.addVertex(es.getSource());
				}
				if(!graph.containsVertex(es.getTarget())) {
					graph.addVertex(es.getTarget());
				}
				if(!graph.containsEdge(es)) {
					graph.addEdge(es.getSource(),es.getTarget());
				}
			});

		});

		return graph;
	}


	private Map<Class<?> , EntityBrainzMetaModel<?,?>>  prepareEntityGraph(Map<Class<?>, EntityBrainzMetaModel<?, ?>> tempMap) {
		Map<Class<?> , EntityBrainzMetaModel<?,?>> shallowMap = new HashMap<>();
		tempMap.entrySet()
		.stream()
		.map(e ->{
			Graph<EntityBrainzMetaModel<?,?>, MetaModelEdge> graph = buildDirectedSimpleGraph();
			EntityBrainzMetaModel<?,?> theEntity = e.getValue();
			graph.addVertex(theEntity);
			theEntity
			.getAttributeMetaModel()
			.stream()
			.forEach(a->{
				EntityBrainzMetaModel<?,?> attributeEntity = tempMap.get(a.getAttributeClass());
				if(attributeEntity!=null) {
					graph.addVertex(attributeEntity);
					graph.addEdge(theEntity, attributeEntity);
				}
			});
			theEntity.setModelGraph(graph);
			e.setValue(theEntity);
			return e;
		})
		.forEach(e->shallowMap.put(e.getKey(), e.getValue()));
		return shallowMap;

	}

	private  Graph<EntityBrainzMetaModel<?,?>, MetaModelEdge> buildDirectedSimpleGraph() {
		return GraphTypeBuilder
				.<EntityBrainzMetaModel<?,?>, MetaModelEdge>directed() .allowingMultipleEdges(true)
				.allowingSelfLoops(true).edgeClass(MetaModelEdge.class).weighted(false).buildGraph();
	}

	private  Graph<EntityBrainzMetaModel<?,?>, MetaModelEdge> buildUnDirectedSimpleGraph() {
		return GraphTypeBuilder
				.<EntityBrainzMetaModel<?,?>, MetaModelEdge>undirected() .allowingMultipleEdges(true)
				.allowingSelfLoops(true).edgeClass(MetaModelEdge.class).weighted(false).buildGraph();
	}

	private Map<Class<?> , EntityBrainzMetaModel<?,?>>  processEntityBrainzMetaModel(Set<Class<?>> types, Reflections reflections) {
		Map<Class<?> , EntityBrainzMetaModel<?,?>>  tempMap = new HashMap<Class<?>,EntityBrainzMetaModel<?,?>>();
		types.stream().forEach(c->{
			EntityBrainzMetaModel<?,?> brainzMetaModel = prepareEntityBrainzMetaModel(c,reflections);
			if(brainzMetaModel !=null)
				tempMap.put(brainzMetaModel.getEntityClass(), brainzMetaModel);
		});
		return tempMap;
	}


	private EntityBrainzMetaModel<?, ?> prepareEntityBrainzMetaModel(Class<?> c, Reflections reflections) {
		StaticMetamodel sm = c.getAnnotation(StaticMetamodel.class);
		Class<?> entityClass = sm.value();
		Class<?> metaModelClass = c;
		Set<EntityType<?>> entities = metaModel.getEntities();
		System.out.println(entityClass);
		EntityBrainzMetaModel<?,?> brainzMetaModel =null;
		EntityType<?> et  =null;
		et = getEntityType(entityClass,entities);
		if(et !=null) {
			Set<AttributeMetaModel<?>> attributes = getMetamodelAttributes(c.getFields(),et);
			brainzMetaModel = new EntityBrainzMetaModel<>(metaModelClass,entityClass); 
			brainzMetaModel.setAttributeMetaModel(attributes);
		}
		return brainzMetaModel;
	}


	private EntityType<?> getEntityType(Class<?> entityClass, Set<EntityType<?>> entities) {
		return entities
				.stream()
				.filter(e->e.getJavaType().equals(entityClass)).findFirst().orElse(null);

	}


	@SuppressWarnings("unchecked")
	private <X> Set<AttributeMetaModel<?>> getMetamodelAttributes(Field[] fields,EntityType<?> et) {
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
					Attribute<?,X> metaModelAttibute = (Attribute<?, X>) et.getAttribute(f.getName());
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
		Class<X> propertyType = Class.class.cast(baseBeanTypeArguments[1]);
		return new AttributeMetaModel<>(name,propertyType);

	}


}
