package ast;

import token.Token;

public class NumberLiteralNode extends Node{
	
	Token token;
	String value;
	
	public NumberLiteralNode(Token token){
		this.token = token;
		this.value = token.getValue();
	}
	
	public String getValue(){
		return this.value;
	}
	
	@Override
	public void printContents() {
		System.out.print("Token: " + token.getType() + " : " + token.getValue());
	}

}
