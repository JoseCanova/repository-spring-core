package org.nanotek.opencsv;

import java.beans.PropertyDescriptor;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.BaseException;
import org.nanotek.Entity;
import org.nanotek.MutatorSupport;
import org.nanotek.PredicateBase;
import org.nanotek.beans.EntityBeanInfo;
import org.nanotek.beans.csv.ArtistBean;

public class CsvOnResultPredicate 
<K extends   BaseBean<K,ID>, ID extends BaseEntity<?,?>>
implements PredicateBase<K,ID>{

	private static final long serialVersionUID = -486052859326166447L;
	
	@Override
	public Optional<ID> evaluate(K immutable) {
		filterProperties(immutable);
		return Optional.ofNullable(immutable.getId());
	}
	
	
	private void filterProperties(K immutable) {
		EntityBeanInfo<?> entityBeanInfo = new EntityBeanInfo<>(immutable.getId().getClass());
		Optional<PropertyDescriptor[]> pd = MutatorSupport.getPropertyDescriptors(immutable.getClass());
		Map<PropertyDescriptor , Optional<PropertyDescriptor>> equivalenceMap = Stream.of(pd.get())
				.map(p->{ return new Map.Entry<PropertyDescriptor,Optional<PropertyDescriptor>>(
				) {
				    PropertyDescriptor key = p; 
				    
				    Optional<PropertyDescriptor> value = verifyPropertyOnId(p, immutable.getId(),entityBeanInfo);
		    
						@Override
						public PropertyDescriptor getKey() {
							return key;
						}
					
						@Override
						public Optional<PropertyDescriptor> getValue() {
							return value;
						}
					
						@Override
						public Optional<PropertyDescriptor> setValue(Optional<PropertyDescriptor> value) {
							return this.value = value;
						}
				}; 
			}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		transfer(equivalenceMap,immutable,entityBeanInfo );
	}


	private void transfer(Map<PropertyDescriptor, Optional<PropertyDescriptor>> equivalenceMap, K immutable,EntityBeanInfo<?> entityBeanInfo ) {
			equivalenceMap.keySet().stream().forEach(p-> {
				try {
					if (Objects.nonNull(p.getReadMethod())) {
						Object value = p.getReadMethod().invoke(immutable, new Object[]{});
						if(Objects.nonNull(value)  && BaseBean.class.isAssignableFrom(value.getClass())) {
							equivalenceMap.get(p).ifPresent(pid ->{
								BaseBean<?,?> valueBean = BaseBean.class.cast(value);
								Entity<?> baseEntity = Entity.class.cast(valueBean.getId());
								try {
										pid.getWriteMethod().invoke(immutable.getId(), new Object[] {baseEntity});
								}catch (Exception ex) {
									throw new BaseException(ex);
								}
							});
						}
					}
				}catch (Exception ex) {
					throw new BaseException(ex);
				}
			});	
	}


	private Optional<PropertyDescriptor> verifyPropertyOnId(PropertyDescriptor p, ID id,EntityBeanInfo<?> entityBeanInfo) {
		return Optional.ofNullable(entityBeanInfo.getPropertyDescriptorInfo().get(p.getName()));
	}


	@Override
	public int compareTo(ID o) {
		return 0;
	}
	
	public static void main(String[] args) {
		new CsvOnResultPredicate().evaluate(new ArtistBean());
		
	}
}
