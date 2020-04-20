package org.nanotek.spring.boot.diagnostics;
import org.nanotek.BaseException;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;

public class BaseExceptionFailureAnalyzer
implements FailureAnalyzer{

	public BaseExceptionFailureAnalyzer() {
	}

	@Override
	public FailureAnalysis analyze(Throwable failure) {
		FailureAnalysis fa  = null;
		if(failure.getClass().equals(BaseException.class)) {
			String description = "BaseException is a RuntimeException and will try to finish the execution  of the application";
			String action = "Check Exception stack trace to identity root cause of the problem";
			fa = new FailureAnalysis(description, action, failure) ;
		}
		return fa;
	}

}
