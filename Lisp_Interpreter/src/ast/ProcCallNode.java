package ast;

import token.Token;

public class ProcCallNode extends Node {
	
	Token token;
	Node node;
	
	public ProcCallNode(Token token, Node node){
		this.token = token;
		this.node = node;
	}
	
	public String getSymbol(){
		return this.token.getValue();
	}

	public Node getChild(){
		return node;
	}
	
	@Override
	public void printContents() {
		// TODO Auto-generated method stub
		
	}

}
