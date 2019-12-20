package exceptions;

public class SyntaxErrorException extends CompilationErrorException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public SyntaxErrorException(){
		super("SyntaxErrorException");
	}
	
	public SyntaxErrorException(String string){
		super(string);
	}

	
	
}
