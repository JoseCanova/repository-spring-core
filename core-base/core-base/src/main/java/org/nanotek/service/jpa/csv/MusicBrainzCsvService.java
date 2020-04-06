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

@Service
public class MusicBrainzCsvService 
<B extends BrainzBaseEntity<B>>
{

	private static final Logger logger = LoggerFactory.getLogger(MusicBrainzCsvService.class);
	
	@Autowired
	BrainzPersistenceService<B> brainzPeristenceService;
	
	@Autowired
	EntityVerificationCallBack verificationCallBack;
	
	public MusicBrainzCsvService() {
	}
	
	
	@Async
	public  AsyncResult<?>   verifyBrainzBaseEntity(BaseEntity<?, ?> id) {
		Optional <B> theOptional = Optional.empty();
		
		Class<B> clazz = castClass(id);
		Optional<Stream<?>> theStream = brainzPeristenceService.findByBrainzId(clazz, id);
		if(!theStream.isPresent()) {
			theOptional = convertObject(theStream.get(),clazz);
		}
		AsyncResult<Optional<?>> asyncResult = new AsyncResult<Optional<?>>(theOptional);
		asyncResult.addCallback(verificationCallBack);
		return asyncResult;
	}

	private Optional<B> convertObject(Stream<?> stream,Class<B> clazz) {
		B result = stream.findAny().map(o->{
			return clazz.cast(o);
		}).get();
		return Optional.ofNullable(result);
	}


	@SuppressWarnings("unchecked")
	private Class<B> castClass(BaseEntity<?, ?> id) {
		return ((Class<B>) id.getClass().asSubclass(BrainzBaseEntity.class));
	}

}
