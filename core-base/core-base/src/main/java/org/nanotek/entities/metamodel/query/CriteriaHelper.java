package org.nanotek.entities.metamodel.query;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.graph.RootGraph;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.jgrapht.GraphPath;
import org.nanotek.BaseEntity;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.Area_;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.Artist_;
import org.nanotek.beans.entity.Release;
import org.nanotek.entities.metamodel.BrainzEntityMetaModel;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;
import org.nanotek.entities.metamodel.query.criteria.BrainzCriteriaQuery;
import org.nanotek.proxy.map.BrainzMetaBuddy;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriteriaHelper<X extends BaseEntity<?,?>> {

	@PersistenceContext 
	EntityManager entityManager; 
	
	@Autowired
	BrainzMetaModelUtil brainzMetaModelUtil;
	
	protected CriteriaBuilder criteriaBuilder;

	protected Class<X> entityClass;

	protected CriteriaHelper<X>
			.QueryHolder<CriteriaQuery<?>,Root<?>> queryHolder;
	
	public CriteriaHelper() {
	}
	
	private <Y extends X> CriteriaHelper(Class<X> entityClass) {
		this.entityClass = entityClass;
	}

	public static <X extends BaseEntity<?,?>> CriteriaHelper<? super X> from(Class<X> entityClass) {
		return new CriteriaHelper<X>(entityClass);
	}
	
	public  void join(Class<?> entityClass) { 
	}
	
	public void createQuery() {
		
		CriteriaBuilderImpl cb = (CriteriaBuilderImpl) entityManager.getCriteriaBuilder();
		this.criteriaBuilder = cb;
		BrainzCriteriaQuery<?, ?>  criteriaQuery = new BrainzCriteriaQuery<>(cb,entityClass);
		
		CriteriaQuery<?> query = cb.createQuery(entityClass);
		
		BrainzEntityMetaModel<?, ?> clsMeta =   brainzMetaModelUtil.getMetaModel(Artist.class);
		EntityType<?> metaModelClass = clsMeta.getEntityType();
		Object bt = BrainzMetaBuddy.with(clsMeta.getMetaModelClass()).<Artist_>newInstance(metaModelClass);
		
//		criteriaQuery.from(metaModelClass).join(bt.area);
		RootGraph rootGraph;
		
		Set<?> set = clsMeta.getAttributeMetaModel();
		
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
	
	private <Z extends BaseEntity<?,?>> void to(Class<? super Z> class1) {
		
	}
	
	public static <X extends Artist> void main(String[] args) throws Exception { 
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("spring-core-music-brainz");
		Reflections reflections = newReflections();
		EntityManager entityManager = emf.createEntityManager();
		BrainzMetaModelUtil metaModel = new BrainzMetaModelUtil(entityManager,reflections);
		metaModel.afterPropertiesSet();
		Metamodel metaModelE= entityManager.getMetamodel();
		
		Class<Artist> cls = Artist.class;
		CriteriaHelper<? super Artist<?>> ch =  CriteriaHelper.from(cls);
		ch.entityManager = entityManager;
		ch.brainzMetaModelUtil = metaModel;
		ch.to(Release.class);
		ch.createQuery();
		
		System.out.println("");
	}
	
	public static Reflections newReflections() { 
			return new Reflections(new ConfigurationBuilder()
				     .setUrls(ClasspathHelper.forPackage("org.nanotek.beans.entity"))
				     .setScanners(new SubTypesScanner(), 
			                  new TypeAnnotationsScanner()));
	}
	
	class QueryHolder<Z,Y> { 
		
		public Z query;
		
		private Y from;

		public  QueryHolder(Z z) {
			this.query = z;
		}

		public <K extends Y> void addFrom(K from) {
			this.from = from;
		}
		
	}
 
}
