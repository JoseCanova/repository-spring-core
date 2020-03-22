package org.nanotek;

public class BaseIntegrationException extends RuntimeException {

	private static final long serialVersionUID = -4188804787803721072L;

	public BaseIntegrationException() {
	}

	public BaseIntegrationException(String message) {
		super(message);
	}

	public BaseIntegrationException(Throwable cause) {
		super(cause);
	}

	public BaseIntegrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseIntegrationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
