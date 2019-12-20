package ast;

import token.Token;

public class AssignmentNode extends Node {
	
	Token token;
	SymbolNode leftChild;
	Node rightChild;
	
	public AssignmentNode(Token token, SymbolNode leftChild, Node rightChild){
		this.token = token;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public SymbolNode getLeftChild(){
		return this.leftChild;
	}
	
	public Node getRightChild(){
		return this.rightChild;
	}

	@Override
	public void printContents() {
		System.out.println("---- AssignmentNode ----");
		System.out.println("Left: " + this.leftChild);
		System.out.println("Right: " + this.rightChild);
	}
}
