package org.nanotek.opencsv.task;

import org.nanotek.opencsv.CsvResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class CsvProcessorCallBack<R extends CsvResult<?, ?>> implements ListenableFutureCallback<R> {

	public CsvProcessorCallBack() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onSuccess(R result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailure(Throwable ex) {
		// TODO Auto-generated method stub
		
	}

}
