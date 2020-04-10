package org.nanotek.entities.metamodel.query.criteria;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.nanotek.BaseEntity;
import org.nanotek.IdBase;
import org.nanotek.beans.entity.Artist;
import org.nanotek.entities.metamodel.BrainzEntityMetaModel;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;
import org.nanotek.entities.metamodel.query.CriteriaHelper;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public class BrainzCriteriaQuery
<X extends IdBase<X,Y>, Y extends BaseEntity<?,?>> 
implements IdBase<X,Class<Y>> , CriteriaQuery<Y>{
	
	private static final long serialVersionUID = 7036293271761390183L;

	protected Class<Y> id;
	
	protected EntityManagerFactory entityManagerFactory;
	
	@Autowired
	protected BrainzMetaModelUtil brainzMetaModelUtil;
	
	protected CriteriaBuilder criteriaBuilder;
	
	protected CriteriaQuery<Y> criteriaQuery;

	public BrainzCriteriaQuery(CriteriaBuilder criteriaBuilder, Class<Y> returnType) {
		this.id = returnType;
		this.criteriaBuilder = criteriaBuilder;
		afterPropertiesSet();
	}
	
	public BrainzCriteriaQuery(BrainzCriteriaQuery brainzCriteriaQuery) {
		this.id = brainzCriteriaQuery.id;
		this.entityManagerFactory = brainzCriteriaQuery.entityManagerFactory;
		this.brainzMetaModelUtil = brainzCriteriaQuery.brainzMetaModelUtil;
		this.criteriaBuilder = brainzCriteriaQuery.criteriaBuilder;
		this.criteriaQuery = brainzCriteriaQuery.criteriaQuery;
		afterPropertiesSet();
	}
	

	public BrainzCriteriaQuery(Class<Y> id, EntityManagerFactory entityManagerFactory,
			BrainzMetaModelUtil brainzMetaModelUtil, CriteriaBuilder criteriaBuilder, CriteriaQuery<Y> criteriaQuery) {
		super();
		this.id = id;
		this.entityManagerFactory = entityManagerFactory;
		this.brainzMetaModelUtil = brainzMetaModelUtil;
		this.criteriaBuilder = criteriaBuilder;
		this.criteriaQuery = criteriaQuery;
	}


	private void afterPropertiesSet() {
	}

	@Override
	public Class<Y> getId() {
		return id;
	}
	
	
	@SuppressWarnings("hiding")
	@Override
	public <Y> Root<Y> from(Class<Y> entityClass) {
		return new CriteriaQueryFromCopier<>(this).from(entityClass);
	}

	@SuppressWarnings("rawtypes")
	public CriteriaQuery<Y> select(Selection<? extends Y> selection) {
		return new CriteriaQuerySelectCopier(this,selection).select(selection);
	}

	public <U> Subquery<U> subquery(Class<U> type) {
		return criteriaQuery.subquery(type);
	}

	public Predicate getRestriction() {
		return new AbstractBrainzPredicate(this,criteriaQuery.getRestriction()) {
		}; 
	}

	public <Z> Root<Z> from(EntityType<Z> entity) {
		return from(entity.getJavaType());
	}

	public CriteriaQuery<Y> multiselect(Selection<?>... selections) {
		return criteriaQuery.multiselect(selections);
	}

	public Set<Root<?>> getRoots() {
		return criteriaQuery.getRoots();
	}

	public CriteriaQuery<Y> multiselect(List<Selection<?>> selectionList) {
		return criteriaQuery.multiselect(selectionList);
	}

	public Selection<Y> getSelection() {
		return criteriaQuery.getSelection();
	}

	public List<Expression<?>> getGroupList() {
		return criteriaQuery.getGroupList();
	}

	public Predicate getGroupRestriction() {
		return criteriaQuery.getGroupRestriction();
	}

	public boolean isDistinct() {
		return criteriaQuery.isDistinct();
	}

	public Class<Y> getResultType() {
		return criteriaQuery.getResultType();
	}

	public CriteriaQuery<Y> where(Expression<Boolean> restriction) {
		return criteriaQuery.where(restriction);
	}

	public CriteriaQuery<Y> where(Predicate... restrictions) {
		return criteriaQuery.where(restrictions);
	}

	public CriteriaQuery<Y> groupBy(Expression<?>... grouping) {
		return criteriaQuery.groupBy(grouping);
	}

	public CriteriaQuery<Y> groupBy(List<Expression<?>> grouping) {
		return criteriaQuery.groupBy(grouping);
	}

	public CriteriaQuery<Y> having(Expression<Boolean> restriction) {
		return criteriaQuery.having(restriction);
	}

	public CriteriaQuery<Y> having(Predicate... restrictions) {
		return criteriaQuery.having(restrictions);
	}

	public CriteriaQuery<Y> orderBy(Order... o) {
		return criteriaQuery.orderBy(o);
	}

	public CriteriaQuery<Y> orderBy(List<Order> o) {
		return criteriaQuery.orderBy(o);
	}

	public CriteriaQuery<Y> distinct(boolean distinct) {
		return criteriaQuery.distinct(distinct);
	}

	public List<Order> getOrderList() {
		return criteriaQuery.getOrderList();
	}

	public Set<ParameterExpression<?>> getParameters() {
		return criteriaQuery.getParameters();
	}

	
	
@SuppressWarnings("unused")
public static <X extends Artist> void main(String[] args) throws Exception { 
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("spring-core-music-brainz");
		Reflections reflections = newReflections();
		EntityManager entityManager = emf.createEntityManager();
		BrainzMetaModelUtil metaModel = new BrainzMetaModelUtil(entityManager,reflections);
		metaModel.afterPropertiesSet();
		
		Metamodel metaModelE= entityManager.getMetamodel();
		
		CriteriaBuilder cb =  entityManager.getCriteriaBuilder();
		CriteriaQuery<?> query = cb.createQuery(Artist.class);
		
		BrainzCriteriaQuery<?, ?> bcq =  new BrainzCriteriaQuery(Artist.class, emf, metaModel, cb, query);
		Root<Artist> r = bcq.from(Artist.class);

//		ch.entityManager = entityManager;
//		ch.brainzMetaModelUtil = metaModel;
//		ch.to(Release.class);
		TypedQuery<?> q = entityManager.createQuery(query);
		List<?> results = q.getResultList();
		
		System.out.println("");
	}
	
	public static Reflections newReflections() { 
			return new Reflections(new ConfigurationBuilder()
				     .setUrls(ClasspathHelper.forPackage("org.nanotek.beans.entity"))
				     .setScanners(new SubTypesScanner(), 
			                  new TypeAnnotationsScanner()));
	}
	
	
}
