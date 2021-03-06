package org.nanotek.opencsv;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Set;

import javax.persistence.metamodel.Attribute;

import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.BaseException;
import org.nanotek.EntityTypeSupport;
import org.nanotek.PredicateBase;
import org.nanotek.entities.metamodel.BrainzEntityMetaModel;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;
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
	}

	private ID filterProperties(K immutable) {
		
		Set<Attribute<? super ID,?>> attributeSet = entityTypeSupport.getAttributes();
		ID i = immutable.getId();
		attributeSet
		.stream()
		.filter(a->BaseEntity.class.isAssignableFrom(a.getJavaType()))
		.forEach(a->{
			Optional<?> optValue = immutable.getProperty(a.getName());
			Field f = Field.class.cast(a.getJavaMember());
			optValue.ifPresent(propertyValue -> {
					BaseBean<?,?> b = BaseBean.class.cast(propertyValue);
					writeValue(f,b,i);
			});
		});
		return i;
	}


	private void writeValue(Field f  , BaseBean<?,?> b , ID i) {
		try { 
			    	f.set(i, b.getId());
		}catch(Exception ex) { 
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}

}
