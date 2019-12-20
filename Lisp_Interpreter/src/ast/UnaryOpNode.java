package ast;

import token.Token;

public class UnaryOpNode extends Node{
	
	String operator;
	Token token;
	Node child;
	
	public UnaryOpNode(Token token, Node node){
		this.operator = token.getValue();
		this.token = token;
		this.child = node;
	}
	
	public String getOperator(){
		return this.operator;
	}
	
	public Node getChild(){
		return this.child;
	}

	@Override
	public void printContents() {
		System.out.println("-----UnaryOpNode-----");
		System.out.println("Operator: " + this.operator + " Child: " + this.child);
	}

}
