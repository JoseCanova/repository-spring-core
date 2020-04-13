package org.nanotek.service.jpa.csv;

import java.util.Optional;
import java.util.stream.Stream;

import org.nanotek.BaseEntity;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.service.jpa.BrainzPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MusicBrainzCsvService 
<B extends BrainzBaseEntity<B>>
{

	private static final Logger logger = LoggerFactory.getLogger(MusicBrainzCsvService.class);
	
	@Autowired
	BrainzPersistenceService<B> brainzPeristenceService;
	
	@Autowired
	EntityVerificationCallBack<B> verificationCallBack;
	
	public MusicBrainzCsvService() {
	}
	
	
	@Async
	@Transactional
	public  AsyncResult<?>   verifyBrainzBaseEntity(BaseEntity<?, ?> id) {
		Optional <B> theOptional = Optional.empty();
		
		Class<B> clazz = castClass(id);
		Optional<Stream<?>> theStream = brainzPeristenceService.findByBrainzId(clazz, id);
		theStream.ifPresent(s->{
			Optional<?> o = s.findFirst();
			try {
				if(!o.isPresent()) {
					B b = convertObject(id,clazz);
					brainzPeristenceService.save(b);
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		AsyncResult<Optional<B>> asyncResult = new AsyncResult<Optional<B>>(theOptional);
		asyncResult.addCallback(verificationCallBack);
		return asyncResult;
	}


	private B convertObject(BaseEntity<?, ?> id,Class<B> clazz) {
		return clazz.cast(id);
	}


	@SuppressWarnings("unchecked")
	private Class<B> castClass(BaseEntity<?, ?> id) {
		return ((Class<B>) id.getClass().asSubclass(BrainzBaseEntity.class));
	}

}
