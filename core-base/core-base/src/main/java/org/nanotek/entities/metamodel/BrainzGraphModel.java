package org.nanotek.entities.metamodel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.Attribute.PersistentAttributeType;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.metamodel.internal.MetamodelImpl;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.nanotek.BaseEntity;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.beans.EntityBeanInfo;
import org.nanotek.beans.sun.introspect.PropertyInfo;
import org.nanotek.opencsv.PriorityEdge;
import org.reflections.Reflections;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@SuppressWarnings("rawtypes")
public class BrainzGraphModel implements InitializingBean{

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired 
	Reflections reflections;
	
	Graph<Class<? extends BaseEntity> , ?> entityGraph = BrainzGraphModel.buildEmptySimpleGraph();
	
	Graph<Class<? extends BaseEntity> , PriorityEdge> entityDirectedGraph = BrainzGraphModel.buildDirectedSimpleGraph();
	
	MetamodelImpl metaModel;
	
	Set<ManagedType<?>>  metamodelEntities;
	
	
	public BrainzGraphModel() {
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		prePrepareModelGraph();
	}
	
	public <X> void prePrepareModelGraph() {
		
		metaModel = (MetamodelImpl) entityManager.getMetamodel();
		
		metamodelEntities =  metaModel.getManagedTypes();
		
		metamodelEntities.stream().forEach(c->{
			Class<BaseEntity<?,?>> clazz= converToBaseEntityClass(c.getJavaType());
			prepareAttributeForEntityGraph(c,clazz);
			prepareAttributeForDirectedGratph(c,clazz);
		});
		
		
	}

	private void prepareAttributeForDirectedGratph(ManagedType<?> c, Class<BaseEntity<?, ?>> clazz) {
		Class<BaseEntity<?, ?>> clazzBase = clazz;
		if(verifyBrainzKey(clazzBase))
			c.getAttributes().stream()
			.filter(a-> filterByAttributeType(a))
			.filter(a->a.isAssociation())
			.filter(a->verifyCardinality1(a))
			.forEach(a ->{
				if(!entityDirectedGraph.containsVertex(clazzBase))
					entityDirectedGraph.addVertex(clazzBase);
				Class<?> brainsType = getBrainzType(a);
				Class<BaseEntity<?,?>> clazz1= converToBaseEntityClass(brainsType);
				if(!entityDirectedGraph.containsVertex(clazz1)) {
						entityDirectedGraph.addVertex(clazz1);
					}
				if(!entityDirectedGraph.containsEdge(clazzBase,clazz1))
					entityDirectedGraph.addEdge(clazzBase,clazz1);
			});
	}

	private Class<?> getBrainzType(Attribute<?, ?> a) {
		if(a.getPersistentAttributeType().equals(PersistentAttributeType.ONE_TO_MANY)
				||  a.getPersistentAttributeType().equals(PersistentAttributeType.MANY_TO_MANY)){
			PluralAttribute<?, ?,?> sa = PluralAttribute.class.cast(a);
			return sa.getElementType().getJavaType();
		}
		return a.getJavaType();
	}

	private Boolean verifyCardinality1(Attribute<?, ?> a) {
		if(a.getPersistentAttributeType().equals(PersistentAttributeType.ONE_TO_ONE) 
					|| a.getPersistentAttributeType().equals(PersistentAttributeType.MANY_TO_ONE)) {
			SingularAttribute<?, ?> sa = SingularAttribute.class.cast(a);
			OneToOne one = null;
			ManyToOne many = null;
			if(sa.getJavaMember().getClass().equals(Field.class)) {
				Field field = (Field) sa.getJavaMember();
				one = 			field.getAnnotation(OneToOne.class);
				many = 			field.getAnnotation(ManyToOne.class);
			}else if (sa.getJavaMember().getClass().equals(Method.class)) {
				Method method = (Method) sa.getJavaMember();
				one = 			method.getAnnotation(OneToOne.class);
				many = 			method.getAnnotation(ManyToOne.class);
			}
			if((one !=null &&  !one.optional() )|| (many !=null && !many.optional()))
				return verifyBrainzKey(a.getJavaType());
			return false;
		}
//		else if (a.getPersistentAttributeType().equals(PersistentAttributeType.ONE_TO_MANY)
//				||  a.getPersistentAttributeType().equals(PersistentAttributeType.MANY_TO_MANY)){
//			PluralAttribute<?, ?,?> sa = PluralAttribute.class.cast(a);
//			Class<?> cls =  sa.getElementType().getJavaType();
//			return verifyBrainzKey(cls);
//		}
		return false;
	}

	private Boolean filterByAttributeType(Attribute<?, ?> a) {
		return a.getPersistentAttributeType().equals(PersistentAttributeType.ONE_TO_ONE)
				|| a.getPersistentAttributeType().equals(PersistentAttributeType.ONE_TO_MANY)
				|| a.getPersistentAttributeType().equals(PersistentAttributeType.MANY_TO_ONE)
				||  a.getPersistentAttributeType().equals(PersistentAttributeType.MANY_TO_MANY);
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

	private void prepareAttributeForEntityGraph(ManagedType<?> c, Class<BaseEntity<?, ?>> clazz) {
		if(!entityGraph.containsVertex(clazz))
			entityGraph.addVertex(clazz);
		c.getSingularAttributes().stream()
		.filter(a->a.getBindableType().equals(Bindable.BindableType.SINGULAR_ATTRIBUTE))//TODO:refactor here.
		.filter(a->a.isAssociation() || a.isCollection())
		.forEach(a ->{
			Class<BaseEntity<?,?>> clazz1= converToBaseEntityClass(a.getJavaType());
			if(!entityGraph.containsVertex(clazz1))
				entityGraph.addVertex(clazz1);
			if(!entityGraph.containsEdge(clazz , clazz1))
				entityGraph.addEdge(clazz  ,  clazz1);
		});
		
	}

	private Annotation hasAnnotation(Field field, Class<? extends Annotation> class1) {
		return field.getAnnotation(class1);
	}

	private Class<BaseEntity<?, ?>> converToBaseEntityClass(Class<?> javaType) {
		return (Class<BaseEntity<?, ?>>) javaType.asSubclass(BaseEntity.class);
	}
	
	private static Graph<Class<? extends BaseEntity>, PriorityEdge> buildEmptySimpleGraph()
    {
        return GraphTypeBuilder
            .<Class<? extends BaseEntity>, DefaultEdge>undirected().allowingMultipleEdges(true)
            .allowingSelfLoops(true).edgeClass(PriorityEdge.class).weighted(false).buildGraph();
    }

	private static Graph<Class<? extends BaseEntity>, PriorityEdge> buildDirectedSimpleGraph() {
		return GraphTypeBuilder
	            .<Class<? extends BaseEntity>, DefaultEdge>directed() .allowingMultipleEdges(true)
	            .allowingSelfLoops(false).edgeClass(PriorityEdge.class).weighted(false).buildGraph();
	}
	
	public Graph<Class<? extends BaseEntity>, ?> getEntityGraph() {
		return entityGraph;
	}

	public Graph<Class<? extends BaseEntity>, ?> getEntityDirectedGraph() {
		return entityDirectedGraph;
	}

	public Reflections getReflections() {
		return reflections;
	}

	public MetamodelImpl getMetaModel() {
		return metaModel;
	}

	public Set<ManagedType<?>> getMetamodelEntities() {
		return metamodelEntities;
	}

	
	public BreadthFirstIterator<Class<? extends BaseEntity>,PriorityEdge>
				getBreadthFirstIterator(){
		return new BreadthFirstIterator<Class<? extends BaseEntity>,PriorityEdge> (entityDirectedGraph);
	}

	/**
	 * 		System.out.println(entityGraph.containsVertex(Artist.class));
		System.out.println(entityGraph.containsVertex(ArtistCredit.class));
		
		JohnsonShortestPaths jsp = new JohnsonShortestPaths(entityGraph);
						
		GraphPath<Class<? extends BaseEntity>, ?> path=  jsp.getPath(ArtistCredit.class, Area.class);
		
		GraphMeasurer measurer = new GraphMeasurer(entityGraph,jsp);
		System.out.println(measurer.getDiameter());
		System.out.println(measurer.getRadius());
		if (path !=null)
			System.out.println(path.toString());
		path=  jsp.getPath(Artist.class, Recording.class);
		if (path !=null)
			System.out.println(path.toString());
		path=  jsp.getPath(Artist.class, Release.class);
		if (path !=null)
			System.out.println(path.toString());
		System.out.println(entityGraph.toString());
		new org.jgrapht.nio.json.JSONExporter().exportGraph(entityGraph, System.out);
	 */
	
}
