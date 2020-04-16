package org.nanotek;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.nanotek.annotations.BrainzKey;
import org.nanotek.beans.EntityBeanInfo;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.beans.sun.introspect.PropertyInfo;

public interface BrainzKeyQuerySupport<B extends BrainzBaseEntity<?>> {

	EntityManager getEntityManager();
	
	default Optional<List<?>> findByBrainzId(Class<B> clazz,Object instance) {
		EntityBeanInfo<?> entityBeanInfo = new EntityBeanInfo<>(clazz);
		return filterPropertyByClass(entityBeanInfo,instance);
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	default  Optional<List<?>> filterPropertyByClass(EntityBeanInfo<?> entityBeanInfo,Object instance) {
	    
		Collection<PropertyInfo> values = entityBeanInfo.getProperties().values();
		
		Stream<PropertyInfo> streamOfProperties = values.stream();
		
		Optional<PropertyInfo> optPropertyInfo = streamOfProperties.filter(p ->{
			if ( p.getReadMethod() !=null) {
				if(p.getReadMethod().getAnnotation(BrainzKey.class)!=null) {
					return true;
				}
			}
			return false;
		}).findFirst();
		
		return optPropertyInfo.map(p ->{
			try {
					BrainzKey clz = p.getReadMethod().getAnnotation(BrainzKey.class);
					CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			        CriteriaQuery<?> query = cb.createQuery(clz.entityClass());
			        Root<?> entity = query.from(clz.entityClass());
			        Path<?> path = entity.get(clz.pathName());
			        Object value = p.getReadMethod().invoke(instance, new Object[] {});
			        Predicate predicate = cb.equal(path, value);
			        query.select((Selection) entity).where(predicate);
			        TypedQuery<?> typeQuery = getEntityManager().createQuery(query);
			        return typeQuery.getResultList();
			}catch(Exception ex) {
	        	throw new BaseException (ex);
	        }
		});
				
	}
	
}
