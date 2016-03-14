package exceptions;

public class ColorDAOException extends Exception {

	private static final long serialVersionUID = -4951142479879779853L;

	public ColorDAOException() {
		super();
	}

	public ColorDAOException(String message) {
		super(message);
	}

	public ColorDAOException(Throwable cause) {
		super(cause);
	}

	public ColorDAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
