package org.nanotek;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.hibernate.metamodel.model.domain.spi.ManagedTypeDescriptor;
import org.nanotek.beans.PropertyDescriptor;

public interface AttributeCopier<K extends ManagedTypeDescriptor<?>>  extends Copier<K> {
	
	@Override
	default  void copy(K k) {
		if(k!=null) {
			Optional<PropertyDescriptor[]> thisProperties = MutatorSupport.getPropertyDescriptors(this.getClass());
			Map<String,PropertyDescriptor> descriptorMap = toMap(thisProperties);
			descriptorMap
				.keySet().stream().forEach(v-> { 
				try {
					Object valueToCopy = k.getAttribute(v);
					PropertyDescriptor p = descriptorMap.get(v);
					if(p!=null && valueToCopy !=null) { 
						p.getWriteMethod().invoke(this, valueToCopy);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BaseException(e);
				}
			});
		}
	}

	default Map<String, PropertyDescriptor> toMap(Optional<PropertyDescriptor[]> thisProperties){
		Map<String,PropertyDescriptor> theMap = new HashMap<>();

		thisProperties.ifPresent(ps ->{
			Stream
			.of(ps)
			.forEach(p->{
				if(p.getName()!=null && !p.getName().isEmpty() && p.getWriteMethod()!=null)
					theMap.put(p.getName(), p);
			});
		});
		return theMap;
	}
}
