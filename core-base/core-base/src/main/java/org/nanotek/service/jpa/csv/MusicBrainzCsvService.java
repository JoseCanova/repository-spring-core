package org.nanotek.service.jpa.csv;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.hibernate.criterion.Example;
import org.nanotek.Base;
import org.nanotek.BaseEntity;
import org.nanotek.BaseException;
import org.nanotek.EntityTypeSupport;
import org.nanotek.MutatorSupport;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.beans.EntityBeanInfo;
import org.nanotek.beans.PropertyDescriptor;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.entities.metamodel.BrainzEntityMetaModel;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;
import org.nanotek.entities.metamodel.query.criteria.BrainzCriteriaBuilder;
import org.nanotek.entities.metamodel.query.criteria.BrainzCriteriaQuery;
import org.nanotek.proxy.map.bean.ForwardMapBean;
import org.nanotek.service.jpa.BrainzPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON  , proxyMode = ScopedProxyMode.NO)
public class MusicBrainzCsvService 
<B extends BrainzBaseEntity<B>> 
{

	private static final Logger logger = LoggerFactory.getLogger(MusicBrainzCsvService.class);

	@Autowired
	BrainzPersistenceService<B> brainzPeristenceService;

	@Autowired
	EntityVerificationCallBack<B> verificationCallBack;

	@Autowired
	BrainzMetaModelUtil brainzMetaModelUtil;

	@Autowired 
	BrainzCriteriaBuilder brainzCriteriaBuilder;

	@Autowired
	Validator validator;

	public MusicBrainzCsvService() {
	}


	@Async(value = "serviceTaskExecutor")
	@Transactional
	public  AsyncResult<?>   verifyBrainzBaseEntity(BaseEntity<?, ?> id) {
		try {
			Optional <B> theOptional = Optional.empty();

			Class<B> clazz = castClass(id);
			Optional<Stream<?>> theStream = brainzPeristenceService.findByBrainzId(clazz, id);
			theStream.ifPresent(s->{
				Optional<?> o = s.findFirst();
				try {
					if(!o.isPresent()) {
						B b = convertObject(id,clazz);
						verifyProperties(b);
						brainzPeristenceService.save(b);
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			});
			AsyncResult<Optional<B>> asyncResult = new AsyncResult<Optional<B>>(theOptional);
			asyncResult.addCallback(verificationCallBack);
			return asyncResult;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		throw new BaseException();
	}


	private void verifyProperties(B b) {
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
						List<?> result = findByBrainzId(t , a.getName());
						if(result.size() !=1) throw new BaseException("Brain ID not found pr invalid" + brainzType);
						dm.write(a.getName(), result.get(0));
					});
				}else if(!valid(a , dm )) { 
					dm.write(a.getName(), null);
				}
			}
		});
	}



	private boolean valid(Attribute<?, ?> a, ForwardMapBean<B> dm) {
		Optional<?> optAttributeValue = dm.read(a.getName());
		return optAttributeValue.map(value->validator.validate(value, Default.class).size() == 0).orElse(false);
	}


	private <X extends BaseEntity<?,?>> List<X> findByBrainzId(Object brainzType , String typeName) {
		EntityTypeSupport<?, X> typeSupport = (EntityTypeSupport<?, X>) brainzMetaModelUtil.getMetaModel(brainzType.getClass()).getEntityTypeSupport();
		BrainzCriteriaQuery<?,X> criteriaQuery = brainzCriteriaBuilder.createBrainzCriteriaQuery(typeSupport.getJavaType());
		String brainzIdPropertyName = getBrainzPropertyName(brainzType.getClass());
		ForwardMapBean<?> dm = new ForwardMapBean<>(brainzType.getClass() , Base.class.cast(brainzType));
		Optional<?> optValueId = dm.read(brainzIdPropertyName);
		Root<?> r  = criteriaQuery
				.from(typeSupport);
		criteriaQuery.where(brainzCriteriaBuilder.equal(r.get(brainzIdPropertyName), optValueId.get()));
		List<X> result = brainzCriteriaBuilder.<X>getResultList(criteriaQuery);
		return result;
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
