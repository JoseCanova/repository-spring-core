package org.nanotek.entities.metamodel.query.criteria;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.nanotek.Id;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;

public 	abstract class  AbstractBrainzRoot<Z> implements Root<Z>,Id<Class<Z>>{ 
	
	protected BrainzMetaModelUtil brainzMetaModelUtil;
	
	protected Root<Z> delegateRootElement;

	protected CriteriaQuery<?> criteriaQuery;
	
	
	public AbstractBrainzRoot (){}
	
	public AbstractBrainzRoot (Root<Z> delegate) { 
		this.delegateRootElement = delegate;
	}
	
	public AbstractBrainzRoot(BrainzMetaModelUtil brainzMetaModelUtil, Root<Z> delegateRootElement) {
		super();
		this.brainzMetaModelUtil = brainzMetaModelUtil;
		this.delegateRootElement = delegateRootElement;
	}

	public AbstractBrainzRoot(CriteriaQuery<?> criteriaQuery, EntityType<Z> entityType,BrainzMetaModelUtil brainzMetaModelUtil) {
		this.delegateRootElement = criteriaQuery.from(entityType);
		this.brainzMetaModelUtil = brainzMetaModelUtil;
		this.criteriaQuery = criteriaQuery;
	}

	@Override
	public EntityType<Z> getModel() {
		return brainzMetaModelUtil.getMetaModel(getId()).getEntityType();
	}

	public Predicate isNull() {
		return delegateRootElement.isNull();
	}

	public Class<? extends Z> getJavaType() {
		return delegateRootElement.getJavaType();
	}

	public Selection<Z> alias(String name) {
		return delegateRootElement.alias(name);
	}

	public Set<Fetch<Z, ?>> getFetches() {
		return delegateRootElement.getFetches();
	}

	public Predicate isNotNull() {
		return delegateRootElement.isNotNull();
	}

	public String getAlias() {
		return delegateRootElement.getAlias();
	}

	public Predicate in(Object... values) {
		return delegateRootElement.in(values);
	}

	public boolean isCompoundSelection() {
		return delegateRootElement.isCompoundSelection();
	}

	public Path<?> getParentPath() {
		return delegateRootElement.getParentPath();
	}

	public <Y> Fetch<Z, Y> fetch(SingularAttribute<? super Z, Y> attribute) {
		return delegateRootElement.fetch(attribute);
	}

	public List<Selection<?>> getCompoundSelectionItems() {
		return delegateRootElement.getCompoundSelectionItems();
	}

	public <Y> Path<Y> get(SingularAttribute<? super Z, Y> attribute) {
		return delegateRootElement.get(attribute);
	}

	public Set<Join<Z, ?>> getJoins() {
		return delegateRootElement.getJoins();
	}

	public Predicate in(Expression<?>... values) {
		return delegateRootElement.in(values);
	}

	public <Y> Fetch<Z, Y> fetch(SingularAttribute<? super Z, Y> attribute, JoinType jt) {
		return delegateRootElement.fetch(attribute, jt);
	}

	public <E, C extends Collection<E>> Expression<C> get(PluralAttribute<Z, C, E> collection) {
		return delegateRootElement.get(collection);
	}

	public Predicate in(Collection<?> values) {
		return delegateRootElement.in(values);
	}

	public boolean isCorrelated() {
		return delegateRootElement.isCorrelated();
	}

	public <Y> Fetch<Z, Y> fetch(PluralAttribute<? super Z, ?, Y> attribute) {
		return delegateRootElement.fetch(attribute);
	}

	public Predicate in(Expression<Collection<?>> values) {
		return delegateRootElement.in(values);
	}

	public <K, V, M extends Map<K, V>> Expression<M> get(MapAttribute<Z, K, V> map) {
		return delegateRootElement.get(map);
	}

	public From<Z, Z> getCorrelationParent() {
		return delegateRootElement.getCorrelationParent();
	}

	public <Y> Fetch<Z, Y> fetch(PluralAttribute<? super Z, ?, Y> attribute, JoinType jt) {
		return delegateRootElement.fetch(attribute, jt);
	}

	public <X> Expression<X> as(Class<X> type) {
		return delegateRootElement.as(type);
	}

	public Expression<Class<? extends Z>> type() {
		return delegateRootElement.type();
	}

	public <X, Y> Fetch<X, Y> fetch(String attributeName) {
		return delegateRootElement.fetch(attributeName);
	}

	public <Y> Join<Z, Y> join(SingularAttribute<? super Z, Y> attribute) {
		return delegateRootElement.join(attribute);
	}

	public <Y> Path<Y> get(String attributeName) {
		return delegateRootElement.get(attributeName);
	}

	public <Y> Join<Z, Y> join(SingularAttribute<? super Z, Y> attribute, JoinType jt) {
		return delegateRootElement.join(attribute, jt);
	}

	public <X, Y> Fetch<X, Y> fetch(String attributeName, JoinType jt) {
		return delegateRootElement.fetch(attributeName, jt);
	}

	public <Y> CollectionJoin<Z, Y> join(CollectionAttribute<? super Z, Y> collection) {
		return delegateRootElement.join(collection);
	}

	public <Y> SetJoin<Z, Y> join(SetAttribute<? super Z, Y> set) {
		return delegateRootElement.join(set);
	}

	public <Y> ListJoin<Z, Y> join(ListAttribute<? super Z, Y> list) {
		return delegateRootElement.join(list);
	}

	public <K, V> MapJoin<Z, K, V> join(MapAttribute<? super Z, K, V> map) {
		return delegateRootElement.join(map);
	}

	public <Y> CollectionJoin<Z, Y> join(CollectionAttribute<? super Z, Y> collection, JoinType jt) {
		return delegateRootElement.join(collection, jt);
	}

	public <Y> SetJoin<Z, Y> join(SetAttribute<? super Z, Y> set, JoinType jt) {
		return delegateRootElement.join(set, jt);
	}

	public <Y> ListJoin<Z, Y> join(ListAttribute<? super Z, Y> list, JoinType jt) {
		return delegateRootElement.join(list, jt);
	}

	public <K, V> MapJoin<Z, K, V> join(MapAttribute<? super Z, K, V> map, JoinType jt) {
		return delegateRootElement.join(map, jt);
	}

	public <X, Y> Join<X, Y> join(String attributeName) {
		return delegateRootElement.join(attributeName);
	}

	public <X, Y> CollectionJoin<X, Y> joinCollection(String attributeName) {
		return delegateRootElement.joinCollection(attributeName);
	}

	public <X, Y> SetJoin<X, Y> joinSet(String attributeName) {
		return delegateRootElement.joinSet(attributeName);
	}

	public <X, Y> ListJoin<X, Y> joinList(String attributeName) {
		return delegateRootElement.joinList(attributeName);
	}

	public <X, K, V> MapJoin<X, K, V> joinMap(String attributeName) {
		return delegateRootElement.joinMap(attributeName);
	}

	public <X, Y> Join<X, Y> join(String attributeName, JoinType jt) {
		return delegateRootElement.join(attributeName, jt);
	}

	public <X, Y> CollectionJoin<X, Y> joinCollection(String attributeName, JoinType jt) {
		return delegateRootElement.joinCollection(attributeName, jt);
	}

	public <X, Y> SetJoin<X, Y> joinSet(String attributeName, JoinType jt) {
		return delegateRootElement.joinSet(attributeName, jt);
	}

	public <X, Y> ListJoin<X, Y> joinList(String attributeName, JoinType jt) {
		return delegateRootElement.joinList(attributeName, jt);
	}

	public <X, K, V> MapJoin<X, K, V> joinMap(String attributeName, JoinType jt) {
		return delegateRootElement.joinMap(attributeName, jt);
	}
}
