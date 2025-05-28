package org.nanotek.service.jpa.csv;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.metamodel.Attribute;
import javax.validation.Validator;

import org.nanotek.BaseEntity;
import org.nanotek.EntityTypeSupport;
import org.nanotek.MutatorSupport;
import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.beans.PropertyDescriptor;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.entities.metamodel.BrainzEntityMetaModel;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;
import org.nanotek.entities.metamodel.query.criteria.BrainzCriteriaBuilder;
import org.nanotek.proxy.map.bean.ForwardMapBean;
import org.nanotek.repository.BaseEntityRepository;
import org.nanotek.service.jpa.BrainzPersistenceService;
import org.nanotek.spring.data.elastic.service.ElasticBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier(value="MusicBrainzCsvService")
public class MusicBrainzCsvService 
<B extends BrainzBaseEntity<B>,X extends BaseEntity<X,?>,S extends X> 
{

	private final Logger logger = LoggerFactory.getLogger(MusicBrainzCsvService.class);

	@Autowired
	BrainzPersistenceService<B> brainzPeristenceService;
	
	@Autowired
	@Qualifier("brainzBaseEntityRepository")
	BaseEntityRepository<X,?> baseEntityRepository;

	@Autowired
	BrainzMetaModelUtil brainzMetaModelUtil;

	@Autowired
	Validator validator;
	
	@Autowired
	ElasticBaseService<B> elasticBaseService;

	public MusicBrainzCsvService() {
	}

	@Transactional
	public void  verifyBrainzBaseEntity(BaseEntity<?, ?> id) {
		try {
			Class<B> clazz = castClass(id);
				if(notFoundByBrainzId(clazz , id)) {
							B b = convertObject(id,clazz);
							prepareProperties(b);
							b = save(b);
//							saveProperties(b);
				}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

//	private void saveProperties(B b) {
//		BrainzEntityMetaModel<?,B> brainzEntityMetaModel = brainzMetaModelUtil.getMetaModel(b.getClass());
//		EntityTypeSupport<?, ?> typeSupport = brainzEntityMetaModel.getEntityTypeSupport();
//		Class<?> bClass = b.getClass();
//		ForwardMapBean<B> dm = new ForwardMapBean<B>(bClass,b);
//		typeSupport
//		.getAttributes()
//		.stream()
//		.forEach(a ->{
//			if(a.getJavaType().getAnnotation(Entity.class) !=null) {
//				if(!brainzKeyAnnotationPresent(a.getJavaType())) {
//					Optional<X>  optProperty = dm.read(a.getName());
////					optProperty.ifPresent(p->populateParentRelationShipIfPresent(b,a,p));
//					optProperty.ifPresent(p-> { 
//						X p1 = baseEntityRepository.save(p);
//						dm.write(a.getName() , p1);
//					});
//				}
//			}
//		});
//	}


//	@SuppressWarnings("unchecked")
//	private void populateParentRelationShipIfPresent(B b, Attribute<?, ?> a, X p) {
//		ForwardMapBean<X> dmX = new ForwardMapBean<X>(p.getClass(),p);
//		Class<X> attributeClass = (Class<X>) p.getClass();
//		BrainzEntityMetaModel<X,?> attributeMetaModel = brainzMetaModelUtil.getMetaModel(attributeClass);
//		EntityTypeSupport<?,X> attributeEntityType = attributeMetaModel.getEntityTypeSupport();
//		if(attributeEntityType
//			.getAttributes()
//			.stream()
//			.anyMatch(a1->a1.getJavaType().equals(b.getClass()))) {
//			Attribute<? super X,?> parentAttribute = attributeEntityType
//											.getAttributes()
//											.stream()
//											.filter(a1->a1.getJavaType().equals(b.getClass()))
//											.findFirst().orElse(null);
//			String attributeName = parentAttribute.getName();
//			dmX.write(attributeName, b);
//		}
//	}

	@Transactional
	private B save(B b) {
		Set<?> validationConstraints = validator.validate(b, new Class[] {PrePersistValidationGroup.class});
		if(validationConstraints.size()>0) {
			validationConstraints.stream().forEach(v->logger.debug(v.toString()));
		}else { 
//			saveProperties(b);
			brainzPeristenceService.save(b);
			elasticBaseService.save(b);
		}
		return b;
	}

	@Transactional
	public boolean notFoundByBrainzId(Class<B> clazz , BaseEntity<?, ?> id) { 
		Optional<List<?>> theStream = brainzPeristenceService.findByBrainzId(clazz, id);
		return theStream.isPresent() && theStream.get().size()==0;
	}
    /**
     *  A simple method that scans all attributes of an Entity verifying which attributes belongs to the persistence model, 
     *  if present perform executes a findByBrainzId to verify if the entity (child entity is present), if the entity 
     *  is not a valid brainz entity class verify if its valid to be inserted in the persistence provider (Postgresql usually).
     * @param b - the entity about to be persisted in the persistence server (ElasticSearch - Postgresql)
     */
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
						Optional<?>  retVal = findByBrainzId(t , a.getName());
						if(retVal.isPresent())
							dm.write(a.getName(), retVal.get());
						else
							dm.write(a.getName(), null);
					});
				}else if(!valid(a , dm )) { 
					dm.write(a.getName(), null);
				}
			}
		});
	}

	private boolean valid(Attribute<?, ?> a, ForwardMapBean<B> dm) {
		Optional<?> optAttributeValue = dm.read(a.getName());
		return optAttributeValue.map(value->{
							
							Set<?> constraints = validator.validate(value, new Class[] {PrePersistValidationGroup.class});
							
							if(constraints.size()>0) { 
//								constraints.stream().forEach(c->logger.debug(c.toString()));
								logger.debug(value.toString());
								return false;
							}
						logger.debug("[entity passed]");
						return true;
							
				}).orElse(false);
	}

	private Optional<?> findByBrainzId(Object brainzType , String typeName) {
		Optional<?> result = Optional.empty();
		String brainzIdPropertyName = getBrainzPropertyName(brainzType.getClass());
		Class<X> clzz = castClass(brainzType.getClass());
		ForwardMapBean<X> from = new ForwardMapBean<X>(clzz , clzz.cast(brainzType));
		ForwardMapBean<X> to = new ForwardMapBean<X>(clzz);
		Optional<?> optValueId = from.read(brainzIdPropertyName);
		if (optValueId.isPresent()) { 
			to.write(brainzIdPropertyName,optValueId.get());
			Example<X> example = Example.of(to.to());
			result = baseEntityRepository.findOne(example);
		}
		return result;
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
