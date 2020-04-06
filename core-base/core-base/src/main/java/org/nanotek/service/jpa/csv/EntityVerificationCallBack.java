package org.nanotek.service.jpa.csv;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class EntityVerificationCallBack 
implements 
ListenableFutureCallback<Optional<?>>{

	private static final Logger logger = LoggerFactory.getLogger(EntityVerificationCallBack.class);

	@Override
	public void onSuccess(Optional<?> result) {
		logger.debug("arrived from the test prepared for update on database");
	}

	@Override
	public void onFailure(Throwable ex) {
		logger.debug("error executing verification of entity", ex);
	}

	
}
