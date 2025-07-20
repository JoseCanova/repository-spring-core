package org.nanotek.opencsv.task;

import java.util.Optional;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.opencsv.CsvResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class CsvLoggerProcessorCallBack
<R extends CsvResult<?, B>,
B extends BrainzBaseEntity<B>> 
implements ListenableFutureCallback<R> {

//	@Autowired
//	@Qualifier("MusicBrainzCsvService")
//	MusicBrainzCsvService<B,?,?> service;
	
	private static Logger log = LoggerFactory.getLogger(CsvProcessorCallBack.class);
   
	private boolean active = true;
	
	public CsvLoggerProcessorCallBack() {
	}

	@Override
	public void onSuccess(R result) {
		Optional.ofNullable(result).ifPresentOrElse(r -> {
			Optional<?> valid = r.on();
			valid.ifPresent(v->{
				if(result.isValid())
				{ 
					log.info("here is where the entity is persisted in database " + r.getId());
//					service.verifyBrainzBaseEntity(r.getId());
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
