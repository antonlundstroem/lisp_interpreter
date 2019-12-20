package ast;

import token.Token;

public class StringLiteralNode extends Node{
	Token token;
	String value;
	
	public StringLiteralNode(Token token){
		this.token = token;
		this.value = token.getValue();
	}
	
	public String getString(){
		return this.value;
	}

	@Override
	public void printContents() {
		// TODO Auto-generated method stub
		
	}

}
