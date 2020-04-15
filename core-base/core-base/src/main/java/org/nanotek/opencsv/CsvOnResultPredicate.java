package org.nanotek.opencsv;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.metamodel.Attribute;

import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.BaseException;
import org.nanotek.Entity;
import org.nanotek.EntityTypeSupport;
import org.nanotek.MutatorSupport;
import org.nanotek.PredicateBase;
import org.nanotek.beans.EntityBeanInfo;
import org.nanotek.beans.Introspector;
import org.nanotek.beans.PropertyDescriptor;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.entities.metamodel.BrainzEntityMetaModel;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;
import org.nanotek.proxy.map.bean.ForwardBean;
import org.nanotek.proxy.map.bean.ForwardMapBean;
import org.springframework.context.ApplicationContext;

public class CsvOnResultPredicate 
<K extends   BaseBean<K,ID>, ID extends BaseEntity<?,?>>
implements PredicateBase<K,ID>{

	private static final long serialVersionUID = -486052859326166447L;
	
	private ApplicationContext applicationContext;
	
	BrainzEntityMetaModel<?,?> baseClassMetaModel; 
	
	EntityTypeSupport<?, ID> entityTypeSupport;
	
	public CsvOnResultPredicate() {}
	
	public CsvOnResultPredicate(ApplicationContext applicationContext) { 
		this.applicationContext = applicationContext;
	}
	
	

	@Override
	public Optional<ID> evaluate(K immutable) {
		populateMetaModel(immutable);
		ID id = filterProperties(immutable);
		return Optional.ofNullable(id);
	}
	
	
	private void populateMetaModel(K immutable) {
		Class<?> clazz = immutable.getBaseClass();
		BrainzMetaModelUtil brainzMetaModelUtil = applicationContext.getBean(BrainzMetaModelUtil.class);
		baseClassMetaModel = brainzMetaModelUtil.getMetaModel(clazz);
		entityTypeSupport = (EntityTypeSupport<?, ID>) baseClassMetaModel.getEntityTypeSupport();
		immutable.getIdBase();
	}

	private ID filterProperties(K immutable) {
		
		Set<Attribute<? super ID,?>> attributeSet = entityTypeSupport.getAttributes();
		ID id = immutable.getId();
		attributeSet
		.stream()
		.forEach(a->{
			Optional<?> optValue = immutable.getProperty(a.getName());
			Field f = Field.class.cast(a.getJavaMember());
			optValue.ifPresent(propertyValue -> {
				if(BaseBean.class.isInstance(propertyValue)) { 
					BaseBean<?,?> b = BaseBean.class.cast(propertyValue);
					writeValue(f,id,b);
				}
			});
		});
		return id;
	}


	private void writeValue(Field f, ID id, BaseBean<?,?> b) {
		try { 
			f.set(id, b.getId());
		}catch(Exception ex) { 
			ex.printStackTrace();
			throw new BaseException(ex);
		}
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
										if(pid.getWriteMethod() !=null)
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
