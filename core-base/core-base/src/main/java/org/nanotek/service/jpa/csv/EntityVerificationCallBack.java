package org.nanotek.service.jpa.csv;

import java.util.Optional;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.service.jpa.BrainzPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class EntityVerificationCallBack <B extends BrainzBaseEntity<B>>
implements 
ListenableFutureCallback<Optional<B>>{


	@Autowired
	BrainzPersistenceService<B> theService;
	
	private static final Logger logger = LoggerFactory.getLogger(EntityVerificationCallBack.class);


	@Override
	public void onFailure(Throwable ex) {
		logger.debug("error executing verification of entity", ex);
	}


	@Override
	public void onSuccess(Optional<B> result) {
		//TODO: From here to where.
	}

	
}
