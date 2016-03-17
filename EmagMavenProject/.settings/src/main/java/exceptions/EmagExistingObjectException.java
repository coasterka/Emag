package exceptions;

public class EmagExistingObjectException extends Exception {
	
	private static final long serialVersionUID = -1310487058823250663L;
	
	public EmagExistingObjectException() {
		super();
	}

	public EmagExistingObjectException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmagExistingObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmagExistingObjectException(String message) {
		super(message);
	}

	public EmagExistingObjectException(Throwable cause) {
		super(cause);
	}
	
}
