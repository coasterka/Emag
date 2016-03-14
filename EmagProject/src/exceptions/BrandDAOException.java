package exceptions;

public class BrandDAOException extends Exception {
	
	private static final long serialVersionUID = -1257779438032992701L;

	public BrandDAOException() {
		super();
	}

	public BrandDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public BrandDAOException(String message) {
		super(message);
	}

	public BrandDAOException(Throwable cause) {
		super(cause);
	}

	

}
