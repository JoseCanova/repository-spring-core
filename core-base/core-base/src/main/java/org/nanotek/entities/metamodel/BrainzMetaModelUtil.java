package org.nanotek.entities.metamodel;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.metamodel.internal.MetamodelImpl;
import org.jgrapht.Graph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.nanotek.BaseEntity;
import org.nanotek.BaseException;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.beans.EntityBeanInfo;
import org.nanotek.beans.sun.introspect.PropertyInfo;
import org.reflections.Reflections;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BrainzMetaModelUtil implements InitializingBean{

	@PersistenceContext
	EntityManager entityManager;

	@Autowired 
	Reflections reflections;

	MetamodelImpl metaModel;

	Map<Class<?> , BrainzEntityMetaModel<?,?>> metaModelMap;

	public BrainzMetaModelUtil() {
	}

	public BrainzMetaModelUtil(EntityManager entityManager, Reflections reflections) {
		super();
		this.entityManager = entityManager;
		this.reflections = reflections;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		metaModel = (MetamodelImpl) entityManager.getMetamodel();
		Set<Class<?>>types =  reflections.getTypesAnnotatedWith(StaticMetamodel.class);
		Map<Class<?> , BrainzEntityMetaModel<?,?>>  tempMap = processEntityBrainzMetaModel(types);
		metaModelMap = prepareEntityGraph(tempMap);
	}

	@SuppressWarnings("unchecked")
	public <X,Y> BrainzEntityMetaModel<X,Y> getMetaModel(Class<? super  X> clazz){
		return (BrainzEntityMetaModel<X, Y>) metaModelMap.get(clazz);
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
			if(metaModelGraph.edgeSet() !=null)
				metaModelGraph
				.edgeSet()
				.forEach(es ->{
					if(es.getSource() !=null && es.getTarget()!=null) {
						if(!graph.containsVertex(es.getSource())) {
							graph.addVertex(es.getSource());
						}
						if(!graph.containsVertex(es.getTarget())) {
							graph.addVertex(es.getTarget());
						}
						if(!graph.containsEdge(es)) {
							graph.addEdge(es.getSource(),es.getTarget());
						}
					}
				});

		});

		return graph;
	}

	private Class<BaseEntity<?, ?>> converToBaseEntityClass(Class<?> javaType) {
		return (Class<BaseEntity<?, ?>>) javaType.asSubclass(BaseEntity.class);
	}
	
	private Boolean verifyBrainzKey(Class<?> cls) {
		EntityBeanInfo<?>  entityBeanInfo = new EntityBeanInfo<>(converToBaseEntityClass(cls));
		Collection<PropertyInfo> values = entityBeanInfo.getProperties().values();
		return  values.stream().filter(p ->{
			if ( p.getReadMethod() !=null) {
				if(p.getReadMethod().getAnnotation(BrainzKey.class)!=null) {
					return true;
				}
			}
			return false;
		}).findFirst().isPresent();
	}

	private Map<Class<?> , BrainzEntityMetaModel<?,?>>  prepareEntityGraph(Map<Class<?>, BrainzEntityMetaModel<?, ?>> tempMap) {
		Map<Class<?> , BrainzEntityMetaModel<?,?>> shallowMap = new HashMap<>();
//		System.err.println(tempMap);
		tempMap.entrySet()
		.stream()
		.filter(e -> verifyBrainzKey(e.getKey()))
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
					if(graph.addVertex(attributeEntity)) {;
						MetaModelEdge emodel = graph.addEdge(theEntity, attributeEntity);
						Field af  = getField(a.getName(),theEntity);
						boolean isRequired = checkAnnotations(af);
						if(isRequired) { 
							graph.setEdgeWeight(emodel, 2d);
						}else { 
							graph.setEdgeWeight(emodel,1d);
						}
					}
				}
			});
			theEntity.setModelGraph(graph);
			e.setValue(theEntity);
			return e;
		})
		.forEach(e->shallowMap.put(e.getKey(), e.getValue()));
		return shallowMap;

	}

	private boolean checkAnnotations(Field af) {
		return af.getAnnotation(NotNull.class) !=null || af.getAnnotation(NotBlank.class)!=null;
	}

	private Field getField(String name, BrainzEntityMetaModel<?, ?> theEntity) {
		try {
			System.out.println(name + "  " + theEntity.getEntityClass().getName());
			return theEntity.getEntityClass().getField(name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e);
		}
	}

	private  Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge> buildDirectedSimpleGraph() {
		return GraphTypeBuilder
				.<BrainzEntityMetaModel<?,?>, MetaModelEdge>directed() .allowingMultipleEdges(true)
				.allowingSelfLoops(true).edgeClass(MetaModelEdge.class).weighted(true).buildGraph();
	}

	private  Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge> buildUnDirectedSimpleGraph() {
		return GraphTypeBuilder
				.<BrainzEntityMetaModel<?,?>, MetaModelEdge>undirected() .allowingMultipleEdges(true)
				.allowingSelfLoops(true).edgeClass(MetaModelEdge.class).weighted(false).buildGraph();
	}

	private Map<Class<?> , BrainzEntityMetaModel<?,?>>  processEntityBrainzMetaModel(Set<Class<?>> types) {
		Map<Class<?> , BrainzEntityMetaModel<?,?>>  tempMap = new HashMap<Class<?>,BrainzEntityMetaModel<?,?>>();
		types.stream().forEach(c->{
			BrainzEntityMetaModel<?,?> brainzMetaModel = prepareEntityBrainzMetaModel(c);
			if(brainzMetaModel !=null)
				tempMap.put(brainzMetaModel.getEntityClass(), brainzMetaModel);
		});
		return tempMap;
	}


	private BrainzEntityMetaModel<?, ?> prepareEntityBrainzMetaModel(Class<?> c) {
		StaticMetamodel sm = c.getAnnotation(StaticMetamodel.class);
		Class<?> entityClass = sm.value();
		Class<?> metaModelClass = c;
		Set<EntityType<?>> entities = metaModel.getEntities();
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
				.filter(e->e.getJavaType().equals(entityClass))
				.findFirst().orElse(null);
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


	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public Reflections getReflections() {
		return reflections;
	}


	public void setReflections(Reflections reflections) {
		this.reflections = reflections;
	}

	public MetamodelImpl getMetaModel() {
		return metaModel;
	}

    public  Map<Class<?> , BrainzEntityMetaModel<?,?>>  getMetaModelMap() {
    	 return metaModelMap;

    }
}
