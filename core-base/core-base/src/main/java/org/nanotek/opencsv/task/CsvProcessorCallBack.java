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
		log.debug("Processor Callback");
		Optional.ofNullable(result).ifPresentOrElse(r -> {
			Optional.of(r);//dispatch to another task .
		}, new Runnable() {

			@Override
			public void run() {
					System.out.println("eixting program");
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
