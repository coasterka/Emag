package exceptions;

public class CategoryDAOException extends Exception {

	
	private static final long serialVersionUID = 1539330352228436L;

	public CategoryDAOException() {
		super();
	}

	public CategoryDAOException(String message) {
		super(message);
	}

	public CategoryDAOException(Throwable cause) {
		super(cause);
	}

	public CategoryDAOException(String message, Throwable cause) {
		super(message, cause);
	}

}
