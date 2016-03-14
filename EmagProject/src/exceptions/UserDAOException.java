package exceptions;

public class UserDAOException extends Exception {

	private static final long serialVersionUID = -2094208839081292632L;

	public UserDAOException() {
		super();
	}

	public UserDAOException(String message) {
		super(message);
	}

	public UserDAOException(Throwable cause) {
		super(cause);
	}

	public UserDAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
