package exceptions;

public class EmagInvalidPassException extends Exception {

	
	private static final long serialVersionUID = 5942538784416352891L;
	
	public EmagInvalidPassException() {
		super();
	}
	
	public EmagInvalidPassException(String message) {
		super(message);
	}
	
	public EmagInvalidPassException(Throwable t) {
		super(t);
	}
	
	public EmagInvalidPassException(String message, Throwable t) {
		super(message, t);
	}
	
	

}
