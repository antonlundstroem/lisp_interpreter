package ast;

import token.Token;

public class SymbolNode extends Node{
	
	Token token;
	String symbol;
	
	public SymbolNode(Token token){
		this.token = token;
		this.symbol = token.getValue();
	}
	
	public String getSymbol(){
		return this.symbol;
	}
	
	@Override
	public void printContents() {
		// TODO Auto-generated method stub
		
	}

}