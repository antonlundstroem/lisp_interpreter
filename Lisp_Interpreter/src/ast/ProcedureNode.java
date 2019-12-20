package ast;

import token.Token;

public class ProcedureNode extends Node{
	
	Token token;
	ProcedureSymbolNode leftChild;
	Node rightChild;
	
	public ProcedureNode(Token token, ProcedureSymbolNode leftChild, Node rightChild){
		this.token = token;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public String getSymbol(){
		return this.leftChild.getSymbol();
	}
	
	public SymbolNode getLeftChild(){
		return this.leftChild;
	}
	
	public Node getRightChild(){
		return this.rightChild;
	}

	@Override
	public void printContents() {
		// TODO Auto-generated method stub
		
	}


}
