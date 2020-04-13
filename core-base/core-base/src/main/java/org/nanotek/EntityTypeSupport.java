package org.nanotek;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.graph.spi.SubGraphImplementor;
import org.hibernate.metamodel.model.domain.internal.EntityTypeImpl;
import org.hibernate.metamodel.model.domain.spi.BagPersistentAttribute;
import org.hibernate.metamodel.model.domain.spi.EntityTypeDescriptor;
import org.hibernate.metamodel.model.domain.spi.IdentifiableTypeDescriptor;
import org.hibernate.metamodel.model.domain.spi.ListPersistentAttribute;
import org.hibernate.metamodel.model.domain.spi.ManagedTypeDescriptor;
import org.hibernate.metamodel.model.domain.spi.MapPersistentAttribute;
import org.hibernate.metamodel.model.domain.spi.PersistentAttributeDescriptor;
import org.hibernate.metamodel.model.domain.spi.PluralPersistentAttribute;
import org.hibernate.metamodel.model.domain.spi.SetPersistentAttribute;
import org.hibernate.metamodel.model.domain.spi.SimpleTypeDescriptor;
import org.hibernate.metamodel.model.domain.spi.SingularPersistentAttribute;

public interface EntityTypeSupport<Z extends EntityTypeSupport<Z,X>, X> extends EntityTypeDescriptor<X> , MutatorSupport<X>{
	
	EntityTypeImpl<X> getEntityType();
	
	@SuppressWarnings("unchecked")
	default <A extends Attribute<?,Y>,Y> Optional<A> get(String  atttributeName){
		return getProperty(atttributeName).map(a -> {
			return (A)a;
		});
	}

	@Override
	default PersistentAttributeDescriptor<? super X, ?> findAttribute(String name) {
		return getEntityType().findAttribute(name);
	}
	
	@Override
	default <S extends X> ManagedTypeDescriptor<S> findSubType(Class<S> type) {
		return getEntityType().findSubType(type);
	}
	@Override
	default void collectIdClassAttributes(Set<SingularPersistentAttribute<? super X, ?>> attributes) {
		getEntityType().collectIdClassAttributes(attributes);
	}
	@Override
	default Set<Attribute<? super X, ?>> getAttributes() {
		return getEntityType().getAttributes();
	}
	
	@Override
	default PersistentAttributeDescriptor<X, ?> findDeclaredAttribute(String name) {
		return getEntityType().findDeclaredAttribute(name);
	}
	
	@Override
	default <S extends X> ManagedTypeDescriptor<S> findSubType(String subTypeName) {
		return getEntityType().findSubType(subTypeName);
	}
	
	@Override
	default PersistentAttributeDescriptor<? super X, ?> getAttribute(String name) {
		return getEntityType().getAttribute(name);
	}
	
	@Override
	default Class<X> getBindableJavaType() {
		return getEntityType().getBindableJavaType();
	}
	
	@Override
	default Class<X> getJavaType() {
		return getEntityType().getJavaType();
	}
	
	@Override
	default <E> BagPersistentAttribute<? super X, E> getCollection(String name, Class<E> elementType) {
		return getEntityType().getCollection(name, elementType);
	}
	
	@Override
	default PersistentAttributeDescriptor<X, ?> getDeclaredAttribute(String name) {
		return getEntityType().getDeclaredAttribute(name);
	}

	
	@Override
	default BindableType getBindableType() {
		return getEntityType().getBindableType();
	}
	
	@Override
	default Set<Attribute<X, ?>> getDeclaredAttributes() {
		return getEntityType().getDeclaredAttributes();
	}
	
	@Override
	default <Y> SingularPersistentAttribute<X, Y> getDeclaredId(Class<Y> type) {
		return getEntityType().getDeclaredId(type);
	}
	
	@Override
	default Set<PluralAttribute<X, ?, ?>> getDeclaredPluralAttributes() {
		return getEntityType().getDeclaredPluralAttributes();
	}
	
	@Override
	default <Y> SingularPersistentAttribute<X, Y> getDeclaredSingularAttribute(String name, Class<Y> type) {
		return getEntityType().getDeclaredSingularAttribute(name, type);
	}
	
	@Override
	default Set<SingularAttribute<X, ?>> getDeclaredSingularAttributes() {
		return getEntityType().getDeclaredSingularAttributes();
	}
	
	@Override
	default <Y> SingularPersistentAttribute<X, Y> getDeclaredVersion(Class<Y> type) {
		return getEntityType().getDeclaredVersion(type);
	}
	
	@Override
	default <Y> SingularPersistentAttribute<? super X, Y> getId(Class<Y> type) {
		return getEntityType().getId(type);
	}
	
	@Override
	default Set<SingularAttribute<? super X, ?>> getIdClassAttributes() {
		return getEntityType().getIdClassAttributes();
	}
	
	@Override
	default SimpleTypeDescriptor<?> getIdType() {
		return getEntityType().getIdType();
	}
	
	@Override
	default InFlightAccess<X> getInFlightAccess() {
		return getEntityType().getInFlightAccess();
	}
	
	@Override
	default <Y> SingularPersistentAttribute<? super X, Y> getVersion(Class<Y> type) {
		return getEntityType().getVersion(type);
	}
	
	@Override
	default SubGraphImplementor<X> makeSubGraph() {
		return getEntityType().makeSubGraph();
	}
	
	@Override
	default <Y> SingularPersistentAttribute<? super X, Y> getSingularAttribute(String name, Class<Y> type) {
		return getEntityType().getSingularAttribute(name, type);
	}
	
	@Override
	default IdentifiableTypeDescriptor<? super X> getSupertype() {
		return getEntityType().getSuperType();
	}
	
	@Override
	default SingularAttribute<? super X, ?> getSingularAttribute(String name) {
		return getEntityType().getSingularAttribute(name);
	}
	
	@Override
	default <E> SetAttribute<? super X, E> getSet(String name, Class<E> elementType) {
		return getEntityType().getSet(name, elementType);
	}
	
	@Override
	default <S extends X> SubGraphImplementor<S> makeSubGraph(Class<S> subType) {
		return getEntityType().makeSubGraph(subType);
	}
	
	@Override
	default void visitIdClassAttributes(Consumer<SingularPersistentAttribute<? super X, ?>> attributeConsumer) {
		getEntityType().visitIdClassAttributes(attributeConsumer);
	}
	
	@Override
	default SetPersistentAttribute<? super X, ?> getSet(String name) {
		return getEntityType().getSet(name);
	}
	
	@Override
	default String getName() {
		return getEntityType().getName();
	}
	
	@Override
	default CollectionAttribute<? super X, ?> getCollection(String name) {
		return getEntityType().getCollection(name);
	}
	
	@Override
	default <K, V> MapAttribute<? super X, K, V> getMap(String name, Class<K> keyType, Class<V> valueType) {
		return getEntityType().getMap(name, keyType, valueType);
	}
	
	@Override
	default MapPersistentAttribute<? super X, ?, ?> getMap(String name) {
		return getEntityType().getMap(name);
	}
	
	@Override
	default <E> ListAttribute<? super X, E> getList(String name, Class<E> elementType) {
		return getEntityType().getList(name, elementType);
	}
	
	@Override
	default ListPersistentAttribute<? super X, ?> getList(String name) {
		return getEntityType().getList(name);
	}
	
	@Override
	default SubGraphImplementor<X> getDefaultGraph() {
		return getEntityType().getDefaultGraph();
	}
	
	@Override
	default SingularAttribute<X, ?> getDeclaredSingularAttribute(String name) {
		return getEntityType().getDeclaredSingularAttribute(name);
	}
	
	@Override
	default <E> SetAttribute<X, E> getDeclaredSet(String name, Class<E> elementType) {
		return getEntityType().getDeclaredSet(name, elementType);
	}
	
	@Override
	default SetPersistentAttribute<X, ?> getDeclaredSet(String name) {
		return getEntityType().getDeclaredSet(name);
	}
	
	@Override
	default PersistenceType getPersistenceType() {
		return getEntityType().getPersistenceType();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	default <C, E> PluralPersistentAttribute<X, C, E> getPluralAttribute(String name) {
		return (PluralPersistentAttribute<X, C, E>) getEntityType().getPluralAttribute(name);
	}
	
	@Override
	default Set<PluralAttribute<? super X, ?, ?>> getPluralAttributes() {
		return getEntityType().getPluralAttributes();
	}
	
	@Override
	default Set<SingularAttribute<? super X, ?>> getSingularAttributes() {
		return getEntityType().getSingularAttributes();
	}
	
	@Override
	default <K, V> MapAttribute<X, K, V> getDeclaredMap(String name, Class<K> keyType, Class<V> valueType) {
		return getEntityType().getDeclaredMap(name, keyType, valueType);
	}
	
	@Override
	default MapPersistentAttribute<X, ?, ?> getDeclaredMap(String name) {
		return getEntityType().getDeclaredMap(name);
	}
	
	@Override
	default IdentifiableTypeDescriptor<? super X> getSuperType() {
		return getEntityType().getSuperType();
	}
	
	@Override
	default <E> ListAttribute<X, E> getDeclaredList(String name, Class<E> elementType) {
		return getEntityType().getDeclaredList(name, elementType);
	}
	
	@Override
	default ListPersistentAttribute<X, ?> getDeclaredList(String name) {
		return getEntityType().getDeclaredList(name);
	}
	
	@Override
	default <E> CollectionAttribute<X, E> getDeclaredCollection(String name, Class<E> elementType) {
		return getEntityType().getDeclaredCollection(name,elementType);
	}
	
	@Override
	default CollectionAttribute<X, ?> getDeclaredCollection(String name) {
		return getEntityType().getDeclaredCollection(name);
	}
	
	@Override
	default SingularPersistentAttribute<? super X, ?> locateVersionAttribute() {
		return getEntityType().locateVersionAttribute();
	}
	
	@Override
	default SingularPersistentAttribute<? super X, ?> locateIdAttribute() {
		return getEntityType().locateIdAttribute();
	}
	
	@Override
	default boolean hasVersionAttribute() {
		return getEntityType().hasVersionAttribute();
	}
	
	@Override
	default boolean hasSingleIdAttribute() {
		return getEntityType().hasSingleIdAttribute();
	}
	
	@Override
	default String getTypeName() {
		return getEntityType().getTypeName();
	}

	@Override
	default boolean hasIdClass() {
		return getEntityType().hasIdClass();
	}
	
}
