package exceptions;

public class ProductDAOException extends Exception {

	private static final long serialVersionUID = 5379622556607224153L;

	public ProductDAOException() {
		super();
	}

	public ProductDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductDAOException(String message) {
		super(message);
	}

	public ProductDAOException(Throwable cause) {
		super(cause);
	}
}
