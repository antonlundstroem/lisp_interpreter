package exceptions;

public class CompilationErrorException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CompilationErrorException() {
		super("CompilationErrorException");
	}
	
	public CompilationErrorException(String string){
		super(string);
	}

}
