package token;

public class Token {
	String type;
	String value;

	public Token(String type, String value){
		this.type = type;
		this.value = value;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getValue(){
		return this.value;
	}
}
