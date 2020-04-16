package org.nanotek.service.jpa.csv;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.metamodel.Attribute;
import javax.validation.Validator;

import org.nanotek.BaseEntity;
import org.nanotek.BaseException;
import org.nanotek.EntityTypeSupport;
import org.nanotek.MutatorSupport;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.beans.PropertyDescriptor;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.entities.metamodel.BrainzEntityMetaModel;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;
import org.nanotek.entities.metamodel.query.criteria.BrainzCriteriaBuilder;
import org.nanotek.opencsv.CsvImportValidation;
import org.nanotek.proxy.map.bean.ForwardMapBean;
import org.nanotek.repository.BaseEntityRepository;
import org.nanotek.service.jpa.BrainzPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Qualifier(value="MusicBrainzCsvService")
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON  , proxyMode = ScopedProxyMode.NO)
public class MusicBrainzCsvService 
<B extends BrainzBaseEntity<B>,X extends BaseEntity<X,?>,S extends X> 
{

	private static final Logger logger = LoggerFactory.getLogger(MusicBrainzCsvService.class);

	@Autowired
	BrainzPersistenceService<B> brainzPeristenceService;
	
	@Autowired
	@Qualifier("brainzBaseEntityRepository")
	BaseEntityRepository<X,?> baseEntityRepository;

	@Autowired
	BrainzMetaModelUtil brainzMetaModelUtil;

	@Autowired 
	BrainzCriteriaBuilder brainzCriteriaBuilder;

	@Autowired
	Validator validator;

	public MusicBrainzCsvService() {
	}


	public void  verifyBrainzBaseEntity(BaseEntity<?, ?> id) {
		try {
			Class<B> clazz = castClass(id);
				if(notFoundByBrainzId(clazz , id)) {
							B b = convertObject(id,clazz);
							prepareProperties(b);
							save(b);
							saveProperties(b);
						}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void saveProperties(B b) {
		BrainzEntityMetaModel<?,B> brainzEntityMetaModel = brainzMetaModelUtil.getMetaModel(b.getClass());
		EntityTypeSupport<?, ?> typeSupport = brainzEntityMetaModel.getEntityTypeSupport();
		Class<?> bClass = b.getClass();
		ForwardMapBean<B> dm = new ForwardMapBean<B>(bClass,b);
		typeSupport
		.getAttributes()
		.stream()
		.forEach(a ->{
			if(a.getJavaType().getAnnotation(Entity.class) !=null) {
				if(!brainzKeyAnnotationPresent(a.getJavaType())) {
					Optional<X>  optProperty = dm.read(a.getName());
					optProperty.ifPresent(p-> baseEntityRepository.save(p));
				}
			}
		});
	}


	@Transactional
	private void save(B b) {
		brainzPeristenceService.save(b);		
	}


	public boolean notFoundByBrainzId(Class<B> clazz , BaseEntity<?, ?> id) { 
		Optional<List<?>> theStream = brainzPeristenceService.findByBrainzId(clazz, id);
		return theStream.map(s->
		s.size() == 0).orElse(false);
	}

	private void prepareProperties(B b) {
		BrainzEntityMetaModel<?,B> brainzEntityMetaModel = brainzMetaModelUtil.getMetaModel(b.getClass());
		EntityTypeSupport<?, ?> typeSupport = brainzEntityMetaModel.getEntityTypeSupport();
		Class<?> bClass = b.getClass();
		ForwardMapBean<B> dm = new ForwardMapBean<B>(bClass,b);
		typeSupport
		.getAttributes()
		.stream()
		.forEach(a ->{
			if(a.getJavaType().getAnnotation(Entity.class) !=null) {
				if(brainzKeyAnnotationPresent(a.getJavaType())) {
					Optional<?> brainzType = dm.read(a.getName());
					brainzType.ifPresent(t->{
						findByBrainzId(t , a.getName())
						.ifPresentOrElse(r ->{
							dm.write(a.getName(), r);
						},BaseException::new);
					});
				}else if(!valid(a , dm )) { 
					dm.write(a.getName(), null);
				}
			}
		});
	}

	private boolean valid(Attribute<?, ?> a, ForwardMapBean<B> dm) {
		Optional<?> optAttributeValue = dm.read(a.getName());
		return optAttributeValue.map(value->validator.validate(value, CsvImportValidation.class).size() == 0).orElse(false);
	}

	private Optional<?> findByBrainzId(Object brainzType , String typeName) {
		String brainzIdPropertyName = getBrainzPropertyName(brainzType.getClass());
		Class<X> clzz = castClass(brainzType.getClass());
		ForwardMapBean<X> from = new ForwardMapBean<X>(clzz , clzz.cast(brainzType));
		ForwardMapBean<X> to = new ForwardMapBean<X>(clzz);
		Optional<?> optValueId = from.read(brainzIdPropertyName);
		to.write(brainzIdPropertyName,optValueId.get());
		Example<X> example = Example.of(to.to());
		return baseEntityRepository.findOne(example);
	}

	private  Class<X> castClass(Class<?> class1) {
		return (Class<X>) class1.asSubclass(BaseEntity.class);
	}

	private boolean brainzKeyAnnotationPresent(Class<?> javaType) {
		Boolean returnValue = false; 
		PropertyDescriptor[] ps = MutatorSupport.getPropertyDescriptors(javaType).orElse(null);
		if(ps !=null) { 
			for (PropertyDescriptor value : ps) { 
				if(value.getReadMethod()!=null && value.getReadMethod().getAnnotation(BrainzKey.class)!=null)
					returnValue = true;
			}
		}
		return returnValue;
	}

	public String getBrainzPropertyName(Class<?> javaType) {
		String propertyName = ""; 
		PropertyDescriptor[] ps = MutatorSupport.getPropertyDescriptors(javaType).orElse(null);
		if(ps !=null) { 
			for (PropertyDescriptor value : ps) { 
				if(value.getReadMethod()!=null && value.getReadMethod().getAnnotation(BrainzKey.class)!=null)
					propertyName = value.getName();
			}
		}
		return propertyName;
	}

	private B convertObject(BaseEntity<?, ?> id,Class<B> clazz) {
		return clazz.cast(id);
	}


	@SuppressWarnings("unchecked")
	private Class<B> castClass(BaseEntity<?, ?> id) {
		return ((Class<B>) id.getClass().asSubclass(BrainzBaseEntity.class));
	}

}
