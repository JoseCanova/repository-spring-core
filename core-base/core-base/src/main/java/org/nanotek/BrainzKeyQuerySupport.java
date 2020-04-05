package org.nanotek;

import java.util.Optional;

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
import org.nanotek.beans.entity.AreaType;

public interface BrainzKeyQuerySupport<T extends IdBase<?,?>> {

	EntityManager getEntityManager();
	
	default <V extends T> Optional<?> prepareDinamicQuery(Class<V> clazz) {
		EntityBeanInfo<?> entityBeanInfo = new EntityBeanInfo<>(clazz);
		return filterPropertyByClass(entityBeanInfo);
	}
	

	@SuppressWarnings("unchecked")
	default  Optional<?> filterPropertyByClass(EntityBeanInfo<?> entityBeanInfo) {
	    
	    return  Optional.ofNullable(entityBeanInfo.getProperties().values().stream().map(v ->{
	    	
	    	if ( v.getReadMethod() !=null) {
	    		
	    		BrainzKey clz = v.getReadMethod().getAnnotation(BrainzKey.class);
	    		if (clz !=null) {
	    			try {
						CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
				        CriteriaQuery<?> query = cb.createQuery(clz.entityClass());
				        Root<?> entity = query.from(clz.entityClass());
				        Path<?> path = entity.get(clz.pathName());
				        Object value = v.getReadMethod().invoke(this, new Object[] {});
				        Predicate predicate = cb.equal(path, value);
				        
				        query.select((Selection) entity).where(predicate);
				        
				        TypedQuery<?> typeQuery = getEntityManager().createQuery(query);
				        return typeQuery.getResultStream();
						}catch(Exception ex) {
							throw new BaseException(ex);
						}
	    		}
	    	}
	    	
	    	return null;
	    }));		
	};

	public static void main(String[] args) {
		AreaType<?> type = new AreaType<>();
	}
	
}
