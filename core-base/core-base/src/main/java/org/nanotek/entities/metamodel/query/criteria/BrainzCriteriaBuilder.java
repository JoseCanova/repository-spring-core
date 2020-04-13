package org.nanotek.entities.metamodel.query.criteria;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;

import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.ExpressionImplementor;
import org.hibernate.query.criteria.internal.expression.ParameterExpressionImpl;
import org.hibernate.query.criteria.internal.predicate.BetweenPredicate;
import org.hibernate.query.criteria.internal.predicate.ComparisonPredicate;
import org.hibernate.query.criteria.internal.predicate.ComparisonPredicate.ComparisonOperator;
import org.hibernate.query.criteria.internal.predicate.InPredicate;
import org.hibernate.query.criteria.internal.predicate.LikePredicate;
import org.hibernate.query.criteria.internal.predicate.NegatedPredicateWrapper;
import org.hibernate.query.criteria.internal.predicate.PredicateImplementor;
import org.nanotek.BaseEntity;
import org.nanotek.BaseException;
import org.nanotek.EntityTypeSupport;
import org.nanotek.IdBase;
import org.nanotek.beans.entity.Artist;
import org.nanotek.entities.metamodel.BrainzEntityMetaModel;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;
import org.nanotek.entities.metamodel.query.criteria.predicate.BrainzBetweenPredicate;
import org.nanotek.entities.metamodel.query.criteria.predicate.BrainzComparisonPredicate;
import org.nanotek.entities.metamodel.query.criteria.predicate.BrainzInPredicate;
import org.nanotek.entities.metamodel.query.criteria.predicate.BrainzLikePredicate;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

public class BrainzCriteriaBuilder implements CriteriaBuilder{

	private CriteriaBuilderImpl delegateCriteriaBuilder;
	
	private SessionFactoryImpl sessionFactory;
	
	@Autowired
	protected BrainzMetaModelUtil brainzMetaModelUtil;

	private Reflections reflections;

	
	public BrainzCriteriaBuilder() {
	}
	
	public BrainzCriteriaBuilder(SessionFactoryImpl sessionFactoryImpl) {
		this.sessionFactory = sessionFactoryImpl;
		createBuddy(sessionFactory);
		createReflections();
		createMetaModel();
	}

	private void createMetaModel() {
		this.brainzMetaModelUtil = new BrainzMetaModelUtil(sessionFactory.createEntityManager(),reflections);
		try {
			this.brainzMetaModelUtil.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createReflections() {
		this.reflections = BrainzCriteriaBuilder.newReflections();
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	public static <X extends Artist> void main(String[] args) throws Exception { 
		SessionFactoryImpl emf = (SessionFactoryImpl) Persistence.createEntityManagerFactory("spring-core-music-brainz");
		EntityManager em = emf.createEntityManager();
		BrainzCriteriaBuilder criteriaBuilder = new BrainzCriteriaBuilder(emf);
		BrainzCriteriaQuery<?,?> criteriaQuery = criteriaBuilder.createBrainzCriteriaQuery(Artist.class);
		BrainzEntityMetaModel<Artist,?> artistMetaModel = criteriaBuilder.getBrainzMetaModelUtil().getMetaModel(Artist.class);
		EntityTypeSupport<?,Artist> typeSupport = artistMetaModel.getEntityTypeSupport();
		Root<Artist> r = criteriaQuery.from(typeSupport);
		Object ret = r.join("area" , JoinType.LEFT); 
		criteriaQuery.where(criteriaBuilder.notLike(r.get("artistName"),"D%"));
		criteriaQuery.orderBy(criteriaBuilder.asc(r.get("artistName")).reverse());
		//		ch.entityManager = entityManager;
		
		//		ch.brainzMetaModelUtil = metaModel;
		//		ch.to(Release.class);
		TypedQuery<?> q = em.createQuery(criteriaQuery.select(r.get("artistName")));
		List<?> results = q.getResultList();

		System.out.println("");
	}
	
	public static Reflections newReflections() { 
		return new Reflections(new ConfigurationBuilder()
				.setUrls(ClasspathHelper.forPackage("org.nanotek.beans.entity"))
				.setScanners(new SubTypesScanner(), 
						new TypeAnnotationsScanner()));
	}
	
	private void createBuddy (SessionFactoryImpl sessionFactory){ 
		try {
		this.delegateCriteriaBuilder = new ByteBuddy(ClassFileVersion.JAVA_V8)
		.subclass(CriteriaBuilderImpl.class)
		.name("org.nanotek.brainz.buddy.BrainzOrder")
		.make()
		  .load(
				    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
				  .getLoaded().getConstructor(SessionFactoryImpl.class).newInstance(new Object[] {sessionFactory});
		}catch(Exception ex) { 
			throw new BaseException(ex);
		}
	}

	public SessionFactoryImpl getEntityManagerFactory() {
		return delegateCriteriaBuilder.getEntityManagerFactory();
	}

	public CriteriaQuery<Object> createQuery() {
		return delegateCriteriaBuilder.createQuery();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> CriteriaQuery<T> createQuery(Class<T> resultClass) {
		return  new BrainzCriteriaQuery(castClass(resultClass), sessionFactory, this , delegateCriteriaBuilder.createQuery(resultClass));//  new BrainzCriteriaQuery(this, delegateCriteriaBuilder.createQuery(resultClass),resultClass);
	}

	
	@SuppressWarnings({  "unchecked" })
	private <X extends IdBase<X,T>,T extends BaseEntity<?,?>> Class<T> castClass(Class<?> resultClass) {
		return (Class<T>) resultClass.asSubclass(BaseEntity.class);
	}


	private <X extends IdBase<X,T>,T extends BaseEntity<?,?>> BrainzCriteriaQuery<X, T> 
	createBrainzCriteriaQuery(Class<T> class1) {
		return new BrainzCriteriaQuery<X,T>(class1, sessionFactory  , this , delegateCriteriaBuilder.createQuery(class1));
	}
	
	public CriteriaQuery<Tuple> createTupleQuery() {
		return delegateCriteriaBuilder.createTupleQuery();
	}

	public <T> CriteriaUpdate<T> createCriteriaUpdate(Class<T> targetEntity) {
		return delegateCriteriaBuilder.createCriteriaUpdate(targetEntity);
	}

	public <T> CriteriaDelete<T> createCriteriaDelete(Class<T> targetEntity) {
		return delegateCriteriaBuilder.createCriteriaDelete(targetEntity);
	}

	public CompoundSelection<Tuple> tuple(Selection<?>... selections) {
		return delegateCriteriaBuilder.tuple(selections);
	}

	public CompoundSelection<Tuple> tuple(List<Selection<?>> selections) {
		return delegateCriteriaBuilder.tuple(selections);
	}

	public CompoundSelection<Object[]> array(Selection<?>... selections) {
		return delegateCriteriaBuilder.array(selections);
	}

	public CompoundSelection<Object[]> array(List<Selection<?>> selections) {
		return delegateCriteriaBuilder.array(selections);
	}

	public <Y> CompoundSelection<Y> array(Class<Y> type, List<Selection<?>> selections) {
		return delegateCriteriaBuilder.array(type, selections);
	}

	public <Y> CompoundSelection<Y> construct(Class<Y> result, Selection<?>... selections) {
		return delegateCriteriaBuilder.construct(result, selections);
	}

	public <Y> CompoundSelection<Y> construct(Class<Y> result, List<Selection<?>> selections) {
		return delegateCriteriaBuilder.construct(result, selections);
	}

	public Order asc(Expression<?> x) {
		return new AbstractBrainzOrder (this , createBuddyOrder (delegateCriteriaBuilder.desc(x))) ;
	}

	public Order desc(Expression<?> x) {
		return new AbstractBrainzOrder (this , createBuddyOrder (delegateCriteriaBuilder.desc(x))) ;
	}
	

	public Predicate wrap(Expression<Boolean> expression) {
		return delegateCriteriaBuilder.wrap(expression);
	}

	public String toString() {
		return delegateCriteriaBuilder.toString();
	}

	public Predicate not(Expression<Boolean> expression) {
		return delegateCriteriaBuilder.not(expression);
	}

	public Predicate and(Expression<Boolean> x, Expression<Boolean> y) {
		return delegateCriteriaBuilder.and(x, y);
	}

	public Predicate or(Expression<Boolean> x, Expression<Boolean> y) {
		return delegateCriteriaBuilder.or(x, y);
	}

	public Predicate and(Predicate... restrictions) {
		return delegateCriteriaBuilder.and(restrictions);
	}

	public Predicate or(Predicate... restrictions) {
		return delegateCriteriaBuilder.or(restrictions);
	}

	public Predicate conjunction() {
		return delegateCriteriaBuilder.conjunction();
	}

	public Predicate disjunction() {
		return delegateCriteriaBuilder.disjunction();
	}

	public Predicate isTrue(Expression<Boolean> expression) {
		return delegateCriteriaBuilder.isTrue(expression);
	}

	public Predicate isFalse(Expression<Boolean> expression) {
		return delegateCriteriaBuilder.isFalse(expression);
	}

	public Predicate isNull(Expression<?> x) {
		return delegateCriteriaBuilder.isNull(x);
	}

	public Predicate isNotNull(Expression<?> x) {
		return delegateCriteriaBuilder.isNotNull(x);
	}

	public Predicate equal(Expression<?> x, Expression<?> y) {
		return delegateCriteriaBuilder.equal(x, y);
	}

	public Predicate notEqual(Expression<?> x, Expression<?> y) {
		return delegateCriteriaBuilder.notEqual(x, y);
	}

	public Predicate equal(Expression<?> x, Object y) {
		return delegateCriteriaBuilder.equal(x, y);
	}

	public Predicate notEqual(Expression<?> x, Object y) {
		return delegateCriteriaBuilder.notEqual(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> x,
			Expression<? extends Y> y) {
		return delegateCriteriaBuilder.greaterThan(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> x, Expression<? extends Y> y) {
		return delegateCriteriaBuilder.lessThan(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> x,
			Expression<? extends Y> y) {
		return delegateCriteriaBuilder.greaterThanOrEqualTo(x, y);
	}

	public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> x,
			Expression<? extends Y> y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
							(this,createBuddyComparisonPredicate( this, 
									ComparisonOperator.LESS_THAN_OR_EQUAL, x, y ));
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> x, Y y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
							(this,createBuddyComparisonPredicate(this, 
									ComparisonOperator.GREATER_THAN, x, y ));
	}

	public <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> x, Y y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
						(this,createBuddyComparisonPredicate(this, 
								ComparisonOperator.LESS_THAN, x, y ));
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> x, Y y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
								(this,createBuddyComparisonPredicate(this, 
										ComparisonOperator.GREATER_THAN_OR_EQUAL, x, y ));
	}

	public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> x, Y y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
							(this,createBuddyComparisonPredicate(this, 
									ComparisonOperator.LESS_THAN_OR_EQUAL, x, y ));
	}

	public Predicate gt(Expression<? extends Number> x, Expression<? extends Number> y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
						(this,createBuddyComparisonPredicateNumber
								(this, ComparisonOperator.GREATER_THAN, x, y ));
	}

	public Predicate lt(Expression<? extends Number> x, Expression<? extends Number> y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
								(this,createBuddyComparisonPredicateNumber(this, 
										ComparisonOperator.LESS_THAN, x, y ));
	}

	public Predicate ge(Expression<? extends Number> x, Expression<? extends Number> y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
								(this,createBuddyComparisonPredicateNumber
										(this, ComparisonOperator.GREATER_THAN_OR_EQUAL, x, y ));
	}

	public Predicate le(Expression<? extends Number> x, Expression<? extends Number> y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
								(this,createBuddyComparisonPredicateNumber
										(this, ComparisonOperator.LESS_THAN_OR_EQUAL, x, y ));
	}

	public Predicate gt(Expression<? extends Number> x, Number y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
						(this,createBuddyComparisonPredicateNumber
								(this, ComparisonOperator.GREATER_THAN, x, y ));
	}

	public Predicate lt(Expression<? extends Number> x, Number y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
							(this,createBuddyComparisonPredicateNumber
									(this, ComparisonOperator.LESS_THAN, x, y ));
	}

	public Predicate ge(Expression<? extends Number> x, Number y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
					(this,createBuddyComparisonPredicateNumber
							(this, ComparisonOperator.GREATER_THAN_OR_EQUAL, x, y ));
	}

	public Predicate le(Expression<? extends Number> x, Number y) {
		return new BrainzComparisonPredicate<ComparisonPredicate>
						(this,createBuddyComparisonPredicateNumber
								(this, ComparisonOperator.LESS_THAN_OR_EQUAL, x, y ));
	}

	public <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> expression, Y lowerBound,
			Y upperBound) {
		return new BrainzBetweenPredicate<>(this,constructBuddyBetweenPredicate(expression,lowerBound,upperBound));
	}

	
	public <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> expression,
			Expression<? extends Y> lowerBound, Expression<? extends Y> upperBound) {
		return new BrainzBetweenPredicate<>(this,constructBuddyBetweenPredicate(expression,lowerBound,upperBound));
	}

	public <T> In<T> in(Expression<? extends T> expression) {
		return new BrainzInPredicate<>(this,createInPredicate(expression));
	}


	@SuppressWarnings("unchecked")
	public <T> In<T> in(Expression<? extends T> expression, Expression<? extends T>... values) {
		return new BrainzInPredicate<>(this,createInPredicate(expression,values));
	}
	
	@SuppressWarnings("unchecked")
	public <T> In<T> in(Expression<? extends T> expression, T... values) {
		return new BrainzInPredicate<>(this,createInPredicate(expression,values));
	}
	
	public <T> In<T> in(Expression<? extends T> expression, Collection<T> values) {
		return new BrainzInPredicate<>(this,createInPredicate(expression,values));
	}
	
	public Predicate like(Expression<String> matchExpression, Expression<String> pattern) {
		return  new BrainzLikePredicate<>(this, createLikePredicate(this,matchExpression,pattern))  ;
	}
	

	public Predicate like(Expression<String> matchExpression, 
			Expression<String> pattern,
			Expression<Character> escapeCharacter) {
		return new BrainzLikePredicate<>(this, createLikePredicate(this,matchExpression,pattern,escapeCharacter));
	}

	public Predicate like(Expression<String> matchExpression, Expression<String> pattern, char escapeCharacter) {
		return new BrainzLikePredicate<>(this, createLikePredicate(this,matchExpression,pattern,escapeCharacter));
	}


	public Predicate like(Expression<String> matchExpression, String pattern) {
		return new BrainzLikePredicate<>(this, createLikePredicate(this,matchExpression,pattern));
	}

	public Predicate like(Expression<String> matchExpression, String pattern, Expression<Character> escapeCharacter) {
		return new BrainzLikePredicate <> (this, createLikePredicate(this,matchExpression, pattern , escapeCharacter));
	}

	
	public Predicate like(Expression<String> matchExpression, String pattern, char escapeCharacter) {
		return new BrainzLikePredicate<>( this, createLikePredicate(this, matchExpression, pattern,escapeCharacter));
	}

	
	public Predicate notLike(Expression<String> matchExpression, Expression<String> pattern) {
		return new BrainzNegatedPredicateWrapper<>(this,createNegatePredicateWrapper(like(matchExpression, pattern)));
	}
	
	public Predicate notLike(Expression<String> matchExpression, Expression<String> pattern,
			Expression<Character> escapeCharacter) {
		return new BrainzNegatedPredicateWrapper<>(this, createNegatePredicateWrapper(like(matchExpression,pattern,escapeCharacter)));
	}
	
	public Predicate notLike(Expression<String> matchExpression, Expression<String> pattern, char escapeCharacter) {
		return new BrainzNegatedPredicateWrapper<>(this, createNegatePredicateWrapper(like(matchExpression,pattern,escapeCharacter)));
	}

	
	public Predicate notLike(Expression<String> matchExpression, String pattern) {
		return new BrainzNegatedPredicateWrapper<>(this, createNegatePredicateWrapper(like(matchExpression,pattern)));
	}

	
	public Predicate notLike(Expression<String> matchExpression, String pattern,
			Expression<Character> escapeCharacter) {
		return new BrainzNegatedPredicateWrapper<>(this, createNegatePredicateWrapper(like(matchExpression,pattern)));
	}

	
	public Predicate notLike(Expression<String> matchExpression, String pattern, char escapeCharacter) {
		return new BrainzNegatedPredicateWrapper<>(this, createNegatePredicateWrapper(like(matchExpression,pattern)));
	}

	@SuppressWarnings("unchecked")
	public <T> ParameterExpression<T> parameter(Class<T> paramClass) {
		return new BrainzParameterExpressionImpl<T>(this, ParameterExpressionImpl.class.cast(delegateCriteriaBuilder.parameter(paramClass)));
	}

	@SuppressWarnings("unchecked")
	public <T> ParameterExpression<T> parameter(Class<T> paramClass, String name) {
		return new BrainzParameterExpressionImpl<T>(this, ParameterExpressionImpl.class.cast(delegateCriteriaBuilder.parameter(paramClass, name)));
	}

	public <T> Expression<T> literal(T value) {
		return delegateCriteriaBuilder.literal(value);
	}

	public <T> Expression<T> nullLiteral(Class<T> resultClass) {
		return delegateCriteriaBuilder.nullLiteral(resultClass);
	}

	public <N extends Number> Expression<Double> avg(Expression<N> x) {
		return delegateCriteriaBuilder.avg(x);
	}

	public <N extends Number> Expression<N> sum(Expression<N> x) {
		return delegateCriteriaBuilder.sum(x);
	}

	public Expression<Long> sumAsLong(Expression<Integer> x) {
		return delegateCriteriaBuilder.sumAsLong(x);
	}

	public Expression<Double> sumAsDouble(Expression<Float> x) {
		return delegateCriteriaBuilder.sumAsDouble(x);
	}

	public <N extends Number> Expression<N> max(Expression<N> x) {
		return delegateCriteriaBuilder.max(x);
	}

	public <N extends Number> Expression<N> min(Expression<N> x) {
		return delegateCriteriaBuilder.min(x);
	}

	public <X extends Comparable<? super X>> Expression<X> greatest(Expression<X> x) {
		return delegateCriteriaBuilder.greatest(x);
	}

	public <X extends Comparable<? super X>> Expression<X> least(Expression<X> x) {
		return delegateCriteriaBuilder.least(x);
	}

	public Expression<Long> count(Expression<?> x) {
		return delegateCriteriaBuilder.count(x);
	}

	public Expression<Long> countDistinct(Expression<?> x) {
		return delegateCriteriaBuilder.countDistinct(x);
	}

	public <T> Expression<T> function(String name, Class<T> returnType, Expression<?>... arguments) {
		return delegateCriteriaBuilder.function(name, returnType, arguments);
	}

	public <T> Expression<T> function(String name, Class<T> returnType) {
		return delegateCriteriaBuilder.function(name, returnType);
	}

	public <N extends Number> Expression<N> abs(Expression<N> expression) {
		return delegateCriteriaBuilder.abs(expression);
	}

	public Expression<Double> sqrt(Expression<? extends Number> expression) {
		return delegateCriteriaBuilder.sqrt(expression);
	}

	public Expression<Date> currentDate() {
		return delegateCriteriaBuilder.currentDate();
	}

	public Expression<Timestamp> currentTimestamp() {
		return delegateCriteriaBuilder.currentTimestamp();
	}

	public Expression<Time> currentTime() {
		return delegateCriteriaBuilder.currentTime();
	}

	public Expression<String> substring(Expression<String> value, Expression<Integer> start) {
		return delegateCriteriaBuilder.substring(value, start);
	}

	public Expression<String> substring(Expression<String> value, int start) {
		return delegateCriteriaBuilder.substring(value, start);
	}

	public Expression<String> substring(Expression<String> value, Expression<Integer> start,
			Expression<Integer> length) {
		return delegateCriteriaBuilder.substring(value, start, length);
	}

	public Expression<String> substring(Expression<String> value, int start, int length) {
		return delegateCriteriaBuilder.substring(value, start, length);
	}

	public Expression<String> trim(Expression<String> trimSource) {
		return delegateCriteriaBuilder.trim(trimSource);
	}

	public Expression<String> trim(Trimspec trimspec, Expression<String> trimSource) {
		return delegateCriteriaBuilder.trim(trimspec, trimSource);
	}

	public Expression<String> trim(Expression<Character> trimCharacter, Expression<String> trimSource) {
		return delegateCriteriaBuilder.trim(trimCharacter, trimSource);
	}

	public Expression<String> trim(Trimspec trimspec, Expression<Character> trimCharacter,
			Expression<String> trimSource) {
		return delegateCriteriaBuilder.trim(trimspec, trimCharacter, trimSource);
	}

	public Expression<String> trim(char trimCharacter, Expression<String> trimSource) {
		return delegateCriteriaBuilder.trim(trimCharacter, trimSource);
	}

	public Expression<String> trim(Trimspec trimspec, char trimCharacter, Expression<String> trimSource) {
		return delegateCriteriaBuilder.trim(trimspec, trimCharacter, trimSource);
	}

	public Expression<String> lower(Expression<String> value) {
		return delegateCriteriaBuilder.lower(value);
	}

	public Expression<String> upper(Expression<String> value) {
		return delegateCriteriaBuilder.upper(value);
	}

	public Expression<Integer> length(Expression<String> value) {
		return delegateCriteriaBuilder.length(value);
	}

	public Expression<Integer> locate(Expression<String> string, Expression<String> pattern) {
		return delegateCriteriaBuilder.locate(string, pattern);
	}

	public Expression<Integer> locate(Expression<String> string, Expression<String> pattern,
			Expression<Integer> start) {
		return delegateCriteriaBuilder.locate(string, pattern, start);
	}

	public Expression<Integer> locate(Expression<String> string, String pattern) {
		return delegateCriteriaBuilder.locate(string, pattern);
	}

	public Expression<Integer> locate(Expression<String> string, String pattern, int start) {
		return delegateCriteriaBuilder.locate(string, pattern, start);
	}

	public <N extends Number> Expression<N> neg(Expression<N> expression) {
		return delegateCriteriaBuilder.neg(expression);
	}

	public <N extends Number> Expression<N> sum(Expression<? extends N> expression1,
			Expression<? extends N> expression2) {
		return delegateCriteriaBuilder.sum(expression1, expression2);
	}

	public <N extends Number> Expression<N> prod(Expression<? extends N> expression1,
			Expression<? extends N> expression2) {
		return delegateCriteriaBuilder.prod(expression1, expression2);
	}

	public <N extends Number> Expression<N> diff(Expression<? extends N> expression1,
			Expression<? extends N> expression2) {
		return delegateCriteriaBuilder.diff(expression1, expression2);
	}

	public <N extends Number> Expression<N> sum(Expression<? extends N> expression, N n) {
		return delegateCriteriaBuilder.sum(expression, n);
	}

	public <N extends Number> Expression<N> prod(Expression<? extends N> expression, N n) {
		return delegateCriteriaBuilder.prod(expression, n);
	}

	public <N extends Number> Expression<N> diff(Expression<? extends N> expression, N n) {
		return delegateCriteriaBuilder.diff(expression, n);
	}

	public <N extends Number> Expression<N> sum(N n, Expression<? extends N> expression) {
		return delegateCriteriaBuilder.sum(n, expression);
	}

	public <N extends Number> Expression<N> prod(N n, Expression<? extends N> expression) {
		return delegateCriteriaBuilder.prod(n, expression);
	}

	public <N extends Number> Expression<N> diff(N n, Expression<? extends N> expression) {
		return delegateCriteriaBuilder.diff(n, expression);
	}

	public Expression<Number> quot(Expression<? extends Number> expression1, Expression<? extends Number> expression2) {
		return delegateCriteriaBuilder.quot(expression1, expression2);
	}

	public Expression<Number> quot(Expression<? extends Number> expression, Number number) {
		return delegateCriteriaBuilder.quot(expression, number);
	}

	public Expression<Number> quot(Number number, Expression<? extends Number> expression) {
		return delegateCriteriaBuilder.quot(number, expression);
	}

	public Expression<Integer> mod(Expression<Integer> expression1, Expression<Integer> expression2) {
		return delegateCriteriaBuilder.mod(expression1, expression2);
	}

	public Expression<Integer> mod(Expression<Integer> expression, Integer integer) {
		return delegateCriteriaBuilder.mod(expression, integer);
	}

	public Expression<Integer> mod(Integer integer, Expression<Integer> expression) {
		return delegateCriteriaBuilder.mod(integer, expression);
	}

	public ExpressionImplementor<Long> toLong(Expression<? extends Number> expression) {
		return delegateCriteriaBuilder.toLong(expression);
	}

	public ExpressionImplementor<Integer> toInteger(Expression<? extends Number> expression) {
		return delegateCriteriaBuilder.toInteger(expression);
	}

	public ExpressionImplementor<Float> toFloat(Expression<? extends Number> expression) {
		return delegateCriteriaBuilder.toFloat(expression);
	}

	public ExpressionImplementor<Double> toDouble(Expression<? extends Number> expression) {
		return delegateCriteriaBuilder.toDouble(expression);
	}

	public ExpressionImplementor<BigDecimal> toBigDecimal(Expression<? extends Number> expression) {
		return delegateCriteriaBuilder.toBigDecimal(expression);
	}

	public ExpressionImplementor<BigInteger> toBigInteger(Expression<? extends Number> expression) {
		return delegateCriteriaBuilder.toBigInteger(expression);
	}

	public ExpressionImplementor<String> toString(Expression<Character> characterExpression) {
		return delegateCriteriaBuilder.toString(characterExpression);
	}

	public <X, T, V extends T> Join<X, V> treat(Join<X, T> join, Class<V> type) {
		return delegateCriteriaBuilder.treat(join, type);
	}

	public <X, T, E extends T> CollectionJoin<X, E> treat(CollectionJoin<X, T> join, Class<E> type) {
		return delegateCriteriaBuilder.treat(join, type);
	}

	public <X, T, E extends T> SetJoin<X, E> treat(SetJoin<X, T> join, Class<E> type) {
		return delegateCriteriaBuilder.treat(join, type);
	}

	public <X, T, E extends T> ListJoin<X, E> treat(ListJoin<X, T> join, Class<E> type) {
		return delegateCriteriaBuilder.treat(join, type);
	}

	public <X, K, T, V extends T> MapJoin<X, K, V> treat(MapJoin<X, K, T> join, Class<V> type) {
		return delegateCriteriaBuilder.treat(join, type);
	}

	public <X, T extends X> Path<T> treat(Path<X> path, Class<T> type) {
		return delegateCriteriaBuilder.treat(path, type);
	}

	public <X, T extends X> Root<T> treat(Root<X> root, Class<T> type) {
		return delegateCriteriaBuilder.treat(root, type);
	}

	public Predicate exists(Subquery<?> subquery) {
		return delegateCriteriaBuilder.exists(subquery);
	}

	public <Y> Expression<Y> all(Subquery<Y> subquery) {
		return delegateCriteriaBuilder.all(subquery);
	}

	public <Y> Expression<Y> some(Subquery<Y> subquery) {
		return delegateCriteriaBuilder.some(subquery);
	}

	public <Y> Expression<Y> any(Subquery<Y> subquery) {
		return delegateCriteriaBuilder.any(subquery);
	}

	public <Y> Expression<Y> coalesce(Expression<? extends Y> exp1, Expression<? extends Y> exp2) {
		return delegateCriteriaBuilder.coalesce(exp1, exp2);
	}

	public <Y> Expression<Y> coalesce(Class<Y> type, Expression<? extends Y> exp1, Expression<? extends Y> exp2) {
		return delegateCriteriaBuilder.coalesce(type, exp1, exp2);
	}

	public <Y> Expression<Y> coalesce(Expression<? extends Y> exp1, Y exp2) {
		return delegateCriteriaBuilder.coalesce(exp1, exp2);
	}

	public <Y> Expression<Y> coalesce(Class<Y> type, Expression<? extends Y> exp1, Y exp2) {
		return delegateCriteriaBuilder.coalesce(type, exp1, exp2);
	}

	public <T> Coalesce<T> coalesce() {
		return delegateCriteriaBuilder.coalesce();
	}

	public <T> Coalesce<T> coalesce(Class<T> type) {
		return delegateCriteriaBuilder.coalesce(type);
	}

	public Expression<String> concat(Expression<String> string1, Expression<String> string2) {
		return delegateCriteriaBuilder.concat(string1, string2);
	}

	public Expression<String> concat(Expression<String> string1, String string2) {
		return delegateCriteriaBuilder.concat(string1, string2);
	}

	public Expression<String> concat(String string1, Expression<String> string2) {
		return delegateCriteriaBuilder.concat(string1, string2);
	}

	public <Y> Expression<Y> nullif(Expression<Y> exp1, Expression<?> exp2) {
		return delegateCriteriaBuilder.nullif(exp1, exp2);
	}

	public <Y> Expression<Y> nullif(Class<Y> type, Expression<Y> exp1, Expression<?> exp2) {
		return delegateCriteriaBuilder.nullif(type, exp1, exp2);
	}

	public <Y> Expression<Y> nullif(Expression<Y> exp1, Y exp2) {
		return delegateCriteriaBuilder.nullif(exp1, exp2);
	}

	public <Y> Expression<Y> nullif(Class<Y> type, Expression<Y> exp1, Y exp2) {
		return delegateCriteriaBuilder.nullif(type, exp1, exp2);
	}

	public <C, R> SimpleCase<C, R> selectCase(Expression<? extends C> expression) {
		return delegateCriteriaBuilder.selectCase(expression);
	}

	public <C, R> SimpleCase<C, R> selectCase(Class<R> type, Expression<? extends C> expression) {
		return delegateCriteriaBuilder.selectCase(type, expression);
	}

	public <R> Case<R> selectCase() {
		return delegateCriteriaBuilder.selectCase();
	}

	public <R> Case<R> selectCase(Class<R> type) {
		return delegateCriteriaBuilder.selectCase(type);
	}

	public <C extends Collection<?>> Expression<Integer> size(C c) {
		return delegateCriteriaBuilder.size(c);
	}

	public <C extends Collection<?>> Expression<Integer> size(Expression<C> exp) {
		return delegateCriteriaBuilder.size(exp);
	}

	public <V, M extends Map<?, V>> Expression<Collection<V>> values(M map) {
		return delegateCriteriaBuilder.values(map);
	}

	public <K, M extends Map<K, ?>> Expression<Set<K>> keys(M map) {
		return delegateCriteriaBuilder.keys(map);
	}

	public <C extends Collection<?>> Predicate isEmpty(Expression<C> collectionExpression) {
		return delegateCriteriaBuilder.isEmpty(collectionExpression);
	}

	public <C extends Collection<?>> Predicate isNotEmpty(Expression<C> collectionExpression) {
		return delegateCriteriaBuilder.isNotEmpty(collectionExpression);
	}

	public <E, C extends Collection<E>> Predicate isMember(E e, Expression<C> collectionExpression) {
		return delegateCriteriaBuilder.isMember(e, collectionExpression);
	}

	public <E, C extends Collection<E>> Predicate isNotMember(E e, Expression<C> cExpression) {
		return delegateCriteriaBuilder.isNotMember(e, cExpression);
	}

	public <E, C extends Collection<E>> Predicate isMember(Expression<E> elementExpression,
			Expression<C> collectionExpression) {
		return delegateCriteriaBuilder.isMember(elementExpression, collectionExpression);
	}

	public <E, C extends Collection<E>> Predicate isNotMember(Expression<E> eExpression, Expression<C> cExpression) {
		return delegateCriteriaBuilder.isNotMember(eExpression, cExpression);
	}

	public <M extends Map<?, ?>> Predicate isMapEmpty(Expression<M> mapExpression) {
		return delegateCriteriaBuilder.isMapEmpty(mapExpression);
	}

	public <M extends Map<?, ?>> Predicate isMapNotEmpty(Expression<M> mapExpression) {
		return delegateCriteriaBuilder.isMapNotEmpty(mapExpression);
	}

	public <M extends Map<?, ?>> Expression<Integer> mapSize(Expression<M> mapExpression) {
		return delegateCriteriaBuilder.mapSize(mapExpression);
	}

	public <M extends Map<?, ?>> Expression<Integer> mapSize(M map) {
		return delegateCriteriaBuilder.mapSize(map);
	}

	private NegatedPredicateWrapper
	createNegatePredicateWrapper(Predicate like){
		try {
			return new ByteBuddy(ClassFileVersion.JAVA_V8)
					.subclass(NegatedPredicateWrapper.class)
					.name("org.nanotek.brainz.buddy.BuddyLikePredicate")
					.make()
					  .load(
							  getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
							  .getLoaded().
							  getConstructor(PredicateImplementor.class).newInstance(new Object[] 
											  {	  like
											  });
					}catch(Exception ex) { 
						throw new BaseException(ex);
					}
	}
	
	private  LikePredicate 
	createLikePredicate(
			BrainzCriteriaBuilder brainzCriteriaBuilder,
			Expression<String> matchExpression, 
			String pattern, 
			char escapeCharacter) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(LikePredicate.class)
				.name("org.nanotek.brainz.buddy.BuddyLikePredicate")
				.make()
				  .load(
						  getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  Expression.class,
								  String.class,
								  char.class).newInstance(new Object[] 
										  {	  brainzCriteriaBuilder.delegateCriteriaBuilder, 
											  matchExpression,
											  pattern,
											  escapeCharacter
										  });
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}
	
	private  LikePredicate 
	createLikePredicate(
			BrainzCriteriaBuilder brainzCriteriaBuilder,
			Expression<String> matchExpression, 
			String pattern, 
			Expression<Character> escapeCharacter) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(LikePredicate.class)
				.name("org.nanotek.brainz.buddy.BuddyLikePredicate")
				.make()
				  .load(
						  getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  Expression.class,
								  String.class,
								  Expression.class).newInstance(new Object[] 
										  {	  brainzCriteriaBuilder.delegateCriteriaBuilder, 
											  matchExpression,
											  pattern,
											  escapeCharacter
										  });
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}
	
	private  LikePredicate 
	createLikePredicate(
			BrainzCriteriaBuilder brainzCriteriaBuilder,
			Expression<String> matchExpression, 
			String pattern) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(LikePredicate.class)
				.name("org.nanotek.brainz.buddy.BuddyLikePredicate")
				.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  Expression.class,
								  String.class).newInstance(new Object[] 
										  {brainzCriteriaBuilder.delegateCriteriaBuilder , 
											  matchExpression,
											  pattern});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}
	
	
	private  LikePredicate 
	createLikePredicate(
			BrainzCriteriaBuilder brainzCriteriaBuilder,
			Expression<String> matchExpression, 
			Expression<String> pattern,
			char escapeCharacter) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(LikePredicate.class)
				.name("org.nanotek.brainz.buddy.BuddyLikePredicate")
				.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  Expression.class,
								  Expression.class,
								  char.class).newInstance(new Object[] 
										  {brainzCriteriaBuilder.delegateCriteriaBuilder , 
											  matchExpression,
											  pattern,
											  escapeCharacter});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}
	
	
	
	private  LikePredicate 
	createLikePredicate(
			BrainzCriteriaBuilder brainzCriteriaBuilder,
			Expression<String> matchExpression, 
			Expression<String> pattern,
			Expression<Character> escapeCharacter) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(LikePredicate.class)
				.name("org.nanotek.brainz.buddy.BuddyLikePredicate")
				.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  Expression.class,
								  Expression.class,
								  Expression.class).newInstance(new Object[] 
										  {brainzCriteriaBuilder.delegateCriteriaBuilder , 
											  matchExpression,
											  pattern,
											  escapeCharacter});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}
	
	
	private  LikePredicate 
	createLikePredicate(
			BrainzCriteriaBuilder brainzCriteriaBuilder,
			Expression<String> matchExpression, 
			Expression<String> pattern) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(LikePredicate.class)
				.name("org.nanotek.brainz.buddy.BuddyLikePredicate")
				.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  Expression.class,
								  Expression.class).newInstance(new Object[] 
										  {brainzCriteriaBuilder.delegateCriteriaBuilder , 
											  matchExpression,
											  pattern});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}

	
	//Buddy Builders
	private Order createBuddyOrder (Order delegateOrder){ 
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
		.subclass(delegateOrder.getClass())
		.name("org.nanotek.brainz.buddy.BrainzOrder")
		.make()
		  .load(
				    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
				  .getLoaded().getConstructor(Expression.class).newInstance(new Object[] {delegateOrder.getExpression()});
		}catch(Exception ex) { 
			throw new BaseException(ex);
		}
	}
	
	private <Y extends Comparable<? super Y>> ComparisonPredicate 
	createBuddyComparisonPredicate(
			BrainzCriteriaBuilder brainzCriteriaBuilder,
			ComparisonOperator comparisonOperator, 
			Expression<? extends Y> x, 
			Expression<? extends Y> y) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(ComparisonPredicate.class)
				.name("org.nanotek.brainz.buddy.BuddyComparisonPredicate")
				.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  ComparisonOperator.class,
								  Expression.class,
								  Expression.class).newInstance(new Object[] 
										  {brainzCriteriaBuilder.delegateCriteriaBuilder , 
											  comparisonOperator,
												  x,
												  y});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}
	
	private <Y extends Comparable<? super Y>> ComparisonPredicate 
	createBuddyComparisonPredicate(
			BrainzCriteriaBuilder brainzCriteriaBuilder,
			ComparisonOperator comparisonOperator, 
			Expression<? extends Y> x, 
			Y y) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(ComparisonPredicate.class)
				.name("org.nanotek.brainz.buddy.BuddyComparisonPredicate")
				.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  ComparisonOperator.class,
								  Expression.class,
								  Comparable.class).newInstance(new Object[] 
										  {brainzCriteriaBuilder.delegateCriteriaBuilder , 
											  comparisonOperator,
												  x,
												  y});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}

	private <Y extends Comparable<? super Y>> ComparisonPredicate 
	createBuddyComparisonPredicateNumber(
			BrainzCriteriaBuilder brainzCriteriaBuilder,
			ComparisonOperator comparisonOperator, 
			Expression<? extends Number> x, 
			Expression<? extends Number> y) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(ComparisonPredicate.class)
				.name("org.nanotek.brainz.buddy.BuddyComparisonPredicate")
				.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  ComparisonOperator.class,
								  Expression.class,
								  Expression.class).newInstance(new Object[] 
										  {brainzCriteriaBuilder.delegateCriteriaBuilder , 
											  comparisonOperator,
												  x,
												  y});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}
	
	private <Y extends Comparable<? super Y>> ComparisonPredicate 
	createBuddyComparisonPredicateNumber(
			BrainzCriteriaBuilder brainzCriteriaBuilder,
			ComparisonOperator comparisonOperator, 
			Expression<? extends Number> x, 
			Number y) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(ComparisonPredicate.class)
				.name("org.nanotek.brainz.buddy.BuddyComparisonPredicate")
				.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  ComparisonOperator.class,
								  Expression.class,
								  Comparable.class).newInstance(new Object[] 
										  {brainzCriteriaBuilder.delegateCriteriaBuilder , 
											  comparisonOperator,
												  x,
												  y});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}
	
	@SuppressWarnings("unchecked")
	private <X extends BetweenPredicate<Y> , Y extends Comparable<? super Y>> BetweenPredicate<Y> 
			constructBuddyBetweenPredicate(Expression<? extends Y> expression,
					Expression<? extends Y> lowerBound, Expression<? extends Y> upperBound) {
		
			try {
			return new ByteBuddy(ClassFileVersion.JAVA_V8)
					.subclass(BetweenPredicate.class)
					.name("org.nanotek.brainz.buddy.BetweenPredicate")
					.make()
					  .load(
							    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
							  .getLoaded().
							  getConstructor(CriteriaBuilderImpl.class,
									  Expression.class,
									  Expression.class,
									  Expression.class).newInstance(new Object[] 
											  { delegateCriteriaBuilder , 
												  expression,
												  lowerBound,
												  upperBound});
					}catch(Exception ex) { 
						throw new BaseException(ex);
					}
		}
	
	
	@SuppressWarnings("unchecked")
	private <X extends BetweenPredicate<Y> , Y extends Comparable<? super Y>> BetweenPredicate<Y> 
			constructBuddyBetweenPredicate(Expression<? extends Y> expression, 
			Y lowerBound, Y upperBound) {
		
			try {
			return new ByteBuddy(ClassFileVersion.JAVA_V8)
					.subclass(BetweenPredicate.class)
					.name("org.nanotek.brainz.buddy.BetweenPredicate")
					.make()
					  .load(
							    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
							  .getLoaded().
							  getConstructor(CriteriaBuilderImpl.class,
									  Expression.class,
									  Comparable.class,
									  Comparable.class).newInstance(new Object[] 
											  { delegateCriteriaBuilder , 
												  expression,
												  lowerBound,
												  upperBound});
					}catch(Exception ex) { 
						throw new BaseException(ex);
					}
		}

	@SuppressWarnings("unchecked")
	private <X extends InPredicate<T> , T> InPredicate<T> 
				createInPredicate
				(Expression<? extends T> expression,Collection<T>values) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(InPredicate.class)
				.name("org.nanotek.brainz.buddy.InPredicate")
				.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  Expression.class,
								  Collection.class).newInstance(new Object[] 
										  { delegateCriteriaBuilder , 
											  expression,
											  values});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}

	@SuppressWarnings("unchecked")
	private <X extends InPredicate<T> , T> InPredicate<T> 
				createInPredicate
				(Expression<? extends T> expression,T... values) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(InPredicate.class)
				.name("org.nanotek.brainz.buddy.InPredicate")
				.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  Expression.class,
								  Object[].class).newInstance(new Object[] 
										  { delegateCriteriaBuilder , 
											  expression,
											  values});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}

	@SuppressWarnings("unchecked")
	private <X extends InPredicate<T> , T> InPredicate<T> 
				createInPredicate
				(Expression<? extends T> expression,Expression<? extends T>... values) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(InPredicate.class)
				.name("org.nanotek.brainz.buddy.InPredicate")
				.make()
				  .load(
						  getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  Expression.class,
								  Expression[].class).newInstance(new Object[] 
										  { delegateCriteriaBuilder , 
											  expression,
											  values});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}
	
	
	@SuppressWarnings("unchecked")
	private <X extends InPredicate<T> , T> InPredicate<T> createInPredicate(Expression<? extends T> expression) {
		try {
		return new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(InPredicate.class)
				.name("org.nanotek.brainz.buddy.InPredicate")
				.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded().
						  getConstructor(CriteriaBuilderImpl.class,
								  Expression.class).newInstance(new Object[] 
										  { delegateCriteriaBuilder , 
											  expression});
				}catch(Exception ex) { 
					throw new BaseException(ex);
				}
	}
	
	
	public CriteriaBuilderImpl getDelegateCriteriaBuilder() {
		return delegateCriteriaBuilder;
	}

	public BrainzMetaModelUtil getBrainzMetaModelUtil() {
		return brainzMetaModelUtil;
	}

	
}
