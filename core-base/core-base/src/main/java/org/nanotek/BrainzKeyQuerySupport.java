package org.nanotek;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.nanotek.annotations.BrainzKey;
import org.nanotek.beans.EntityBeanInfo;
import org.nanotek.beans.PropertyDescriptor;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.proxy.map.bean.ForwardMapBean;
import org.nanotek.repository.jpa.BrainzBaseEntityRepository;
import org.springframework.data.domain.Example;

public interface BrainzKeyQuerySupport<B extends BrainzBaseEntity<B>> {

	EntityManager getEntityManager();
	
	BrainzBaseEntityRepository<B> getBaseRepository();
	
	default Optional<List<?>> findByBrainzId(Class<B> clazz,Object instance) {
		EntityBeanInfo<B> entityBeanInfo = new EntityBeanInfo<>(clazz);
		return filterPropertyByClass(entityBeanInfo,instance);
	}
	
	
	default  <X extends B> Optional<List<?>> filterPropertyByClass(EntityBeanInfo<X> entityBeanInfo,Object instance) {
	    
		Optional<PropertyDescriptor> optPropertyDescriptorInfo = 
			entityBeanInfo
			.getPropertyDescriptorInfo()
			.values()
			.stream()
			.filter(p ->{
				if ( p.getReadMethod() !=null) {
					if(p.getReadMethod().getAnnotation(BrainzKey.class)!=null) {
						return true;
					}
				}
				return false;
			}).findFirst();
			
		return optPropertyDescriptorInfo.map(p ->{
			try {
				
				Class<X> clzz = entityBeanInfo.getEntityClass();
				String brainzIdPropertyName = p.getName();
				
				ForwardMapBean<X> from = new ForwardMapBean<X>(clzz , clzz.cast(instance));
				ForwardMapBean<X> to = new ForwardMapBean<X>(clzz);
				Optional<?> optValueId = from.read(brainzIdPropertyName);
				if (optValueId.isPresent()) {
					to.write(brainzIdPropertyName,optValueId.get());
					Example<X> example = Example.of(to.to());
					return getBaseRepository().findAll(example);
				}else { 
					return null;
				}
			}catch(Exception ex) {
	        	throw new BaseException (ex);
	        }
		});
				
	}
	
}
