package org.nanotek.opencsv.task;

import java.util.Optional;

import org.nanotek.BaseException;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.service.jpa.AreaTypePersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class CsvProcessorCallBack
<R extends CsvResult<?, ?>> 
implements ListenableFutureCallback<R> {

	@Autowired
	AreaTypePersistenceService<?,?> service;
	
	private static Logger log = LoggerFactory.getLogger(CsvProcessorCallBack.class);
    private boolean active = true;
	public CsvProcessorCallBack() {
	}

	//TODO: Put the tranformation here.  
	@Override
	public void onSuccess(R result) {
		Optional.ofNullable(result).ifPresentOrElse(r -> {
			Optional<?> valid = r.on();
			try { 
			service.prepareDinamicQuery(AreaType.class);
			log.debug("Valid Bean?  " + valid.get()); 
			}catch(Exception ex) {
				ex.printStackTrace();
				throw new Error(ex);
			}
		}, new Runnable() {
			
			@Override
			public void run() {
					active = false;
			}
			
		});
		
	}

	@Override
	public void onFailure(Throwable ex) {
		log.debug("Exception thrown " ,ex);
		System.exit(0);
	}

	public boolean isActive() {
		return active;
	}

}
