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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.metamodel.internal.MetamodelImpl;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
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

	Map<Class<?> , BrainzEntityMetaModel<?,?>> metaModelMap;

	public BrainzMetaModelUtil() {
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		metaModel = (MetamodelImpl) entityManager.getMetamodel();
		Set<Class<?>>types =  reflections.getTypesAnnotatedWith(StaticMetamodel.class);
		Map<Class<?> , BrainzEntityMetaModel<?,?>>  tempMap = processEntityBrainzMetaModel(types,reflections);
		metaModelMap = prepareEntityGraph(tempMap);
		Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge> resultingGraph = getModelGraph();
		System.out.println("");
	}

	public BrainzEntityMetaModel<?,?> getMetaModel(Class<?> clazz){
		return metaModelMap.get(clazz);
	}


	public Graph<BrainzEntityMetaModel<?, ?>, MetaModelEdge> getModelGraph() {
		Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge> graph = buildUnDirectedSimpleGraph();
		metaModelMap
		.entrySet()
		.stream()
		.forEach(e ->{
			BrainzEntityMetaModel <?,?> meta = e.getValue();
			Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge> 
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


	private Map<Class<?> , BrainzEntityMetaModel<?,?>>  prepareEntityGraph(Map<Class<?>, BrainzEntityMetaModel<?, ?>> tempMap) {
		Map<Class<?> , BrainzEntityMetaModel<?,?>> shallowMap = new HashMap<>();
		tempMap.entrySet()
		.stream()
		.map(e ->{
			Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge> graph = buildDirectedSimpleGraph();
			BrainzEntityMetaModel<?,?> theEntity = e.getValue();
			graph.addVertex(theEntity);
			theEntity
			.getAttributeMetaModel()
			.stream()
			.forEach(a->{
				BrainzEntityMetaModel<?,?> attributeEntity = tempMap.get(a.getAttributeClass());
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

	private  Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge> buildDirectedSimpleGraph() {
		return GraphTypeBuilder
				.<BrainzEntityMetaModel<?,?>, MetaModelEdge>directed() .allowingMultipleEdges(true)
				.allowingSelfLoops(true).edgeClass(MetaModelEdge.class).weighted(false).buildGraph();
	}

	private  Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge> buildUnDirectedSimpleGraph() {
		return GraphTypeBuilder
				.<BrainzEntityMetaModel<?,?>, MetaModelEdge>undirected() .allowingMultipleEdges(true)
				.allowingSelfLoops(true).edgeClass(MetaModelEdge.class).weighted(false).buildGraph();
	}

	private Map<Class<?> , BrainzEntityMetaModel<?,?>>  processEntityBrainzMetaModel(Set<Class<?>> types, Reflections reflections) {
		Map<Class<?> , BrainzEntityMetaModel<?,?>>  tempMap = new HashMap<Class<?>,BrainzEntityMetaModel<?,?>>();
		types.stream().forEach(c->{
			BrainzEntityMetaModel<?,?> brainzMetaModel = prepareEntityBrainzMetaModel(c,reflections);
			if(brainzMetaModel !=null)
				tempMap.put(brainzMetaModel.getEntityClass(), brainzMetaModel);
		});
		return tempMap;
	}


	private BrainzEntityMetaModel<?, ?> prepareEntityBrainzMetaModel(Class<?> c, Reflections reflections) {
		StaticMetamodel sm = c.getAnnotation(StaticMetamodel.class);
		Class<?> entityClass = sm.value();
		Class<?> metaModelClass = c;
		Set<EntityType<?>> entities = metaModel.getEntities();
		System.out.println(entityClass);
		BrainzEntityMetaModel<?,?> brainzMetaModel =null;
		EntityType<?> et  =null;
		et = getEntityType(entityClass,entities);
		if(et !=null) {
			Set<AttributeMetaModel<?>> attributes = getMetamodelAttributes(c.getFields(),et);
			brainzMetaModel = new BrainzEntityMetaModel<>(metaModelClass,entityClass,et); 
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

	
	public void createQuery(Class<?> entityClass , GraphPath<?,?> graphPath) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<?> query = cb.createQuery(entityClass);
		Root<?> from = query.from(entityClass);
		
//		from.join(collection)
		
//        CriteriaQuery<?> query = cb.createQuery(clz.entityClass());
//        Root<?> entity = query.from(clz.entityClass());
//        Path<?> path = entity.get(clz.pathName());
//        Object value = p.getReadMethod().invoke(instance, new Object[] {});
//        Predicate predicate = cb.equal(path, value);
//        
//        query.select((Selection) entity).where(predicate);
//        
//        TypedQuery<?> typeQuery = getEntityManager().createQuery(query);
	}

}
