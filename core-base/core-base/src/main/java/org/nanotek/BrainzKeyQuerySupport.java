package org.nanotek;

import java.util.Collection;
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
	
	default Optional<Stream<?>> prepareDinamicQuery(Class<B> clazz,Object instance) {
		EntityBeanInfo<?> entityBeanInfo = new EntityBeanInfo<>(clazz);
		return filterPropertyByClass(entityBeanInfo,instance);
	}
	

	@SuppressWarnings("unchecked")
	default  Optional<Stream<?>> filterPropertyByClass(EntityBeanInfo<?> entityBeanInfo,Object instance) {
	    
		Collection<PropertyInfo> values = entityBeanInfo.getProperties().values();
		
		Stream<PropertyInfo> stream = values.stream();
		
		Stream<?> theStream  = null;
		
		stream.forEach(v ->{
	    	System.out.println("'");
	    	Stream<?> innerStream = null;
	    	if ( v.getReadMethod() !=null) {
	    		System.out.println("''");
	    		BrainzKey clz = v.getReadMethod().getAnnotation(BrainzKey.class);
	    		if (clz !=null) {
	    			try {
	    				System.out.println("'''");
						CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
				        CriteriaQuery<?> query = cb.createQuery(clz.entityClass());
				        Root<?> entity = query.from(clz.entityClass());
				        Path<?> path = entity.get(clz.pathName());
				        Object value = v.getReadMethod().invoke(instance, new Object[] {});
				        Predicate predicate = cb.equal(path, value);
				        
				        query.select((Selection) entity).where(predicate);
				        
				        TypedQuery<?> typeQuery = getEntityManager().createQuery(query);
				        innerStream =  typeQuery.getResultStream();
						}catch(Exception ex) {
							ex.printStackTrace();
							throw new Error(ex);
						}
	    		}
	    	}
	    	
	    });	
		
		
				
		return Optional.ofNullable(theStream);
	}
	
}
