package org.nanotek.opencsv;

@SuppressWarnings("serial")
public class MappingStrategyException extends RuntimeException {

	public MappingStrategyException() {
	}

	public MappingStrategyException(String message) {
		super(message);
	}

	public MappingStrategyException(Throwable cause) {
		super(cause);
	}

	public MappingStrategyException(String message, Throwable cause) {
		super(message, cause);
	}

	public MappingStrategyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
