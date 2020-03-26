package org.nanotek.opencsv.task;

import java.util.Optional;

import org.nanotek.opencsv.CsvResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class CsvProcessorCallBack
<R extends CsvResult<?, ?>> 
implements ListenableFutureCallback<R> {

	private static Logger log = LoggerFactory.getLogger(CsvProcessorCallBack.class);

	public CsvProcessorCallBack() {
	}

	//TODO: Put the tranformation here.
	@Override
	public void onSuccess(R result) {
		Optional.ofNullable(result).ifPresent(r -> {
			if (r.getValid() == false) { 
				log.debug("Result not valid " + r.withUUID().toString());
			}
		});
		Optional.ofNullable(result).ifPresentOrElse(r -> {
			Optional.of(r);//tranformation here... 
		}, new Runnable() {

			@Override
			public void run() {
					
					System.exit(0);
			}
			
		});
	}

	@Override
	public void onFailure(Throwable ex) {
		log.debug("Exception thrown " ,ex);
		System.exit(0);
	}

}
