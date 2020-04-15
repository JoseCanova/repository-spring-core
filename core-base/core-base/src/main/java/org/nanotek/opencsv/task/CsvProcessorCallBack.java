package org.nanotek.opencsv.task;

import java.util.Optional;
import java.util.stream.Stream;

import org.nanotek.BaseException;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.service.jpa.BrainzPersistenceService;
import org.nanotek.service.jpa.csv.MusicBrainzCsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class CsvProcessorCallBack
<R extends CsvResult<?, B>,
B extends BrainzBaseEntity<B>> 
implements ListenableFutureCallback<R> {

	@Autowired
	MusicBrainzCsvService<B> service;
	
	private static Logger log = LoggerFactory.getLogger(CsvProcessorCallBack.class);
    private boolean active = true;
	public CsvProcessorCallBack() {
	}

	//TODO: Put the tranformation here.  
	@Override
	public void onSuccess(R result) {
		Optional.ofNullable(result).ifPresentOrElse(r -> {
			Optional<?> valid = r.on();
			valid.ifPresent(v->{
				if(result.isValid())
				{ 
					service.verifyBrainzBaseEntity(r.getId());
				}
			});
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
