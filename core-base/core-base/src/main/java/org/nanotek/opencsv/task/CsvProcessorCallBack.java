package org.nanotek.opencsv.task;

import org.nanotek.opencsv.CsvResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class CsvProcessorCallBack<R extends CsvResult<?, ?>> implements ListenableFutureCallback<R> {

	public CsvProcessorCallBack() {
	}

	@Override
	public void onSuccess(R result) {
		
	}

	@Override
	public void onFailure(Throwable ex) {
	}

}
