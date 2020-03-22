package org.nanotek;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -3632076639762410854L;

	public BaseException() {
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
