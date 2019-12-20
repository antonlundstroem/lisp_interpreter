package ast;

import token.Token;

public class PrintNode extends Node {
	
	Token token;
	Node leftChild, rightChild;
	
	public PrintNode(Token token, Node leftChild, Node rightChild){
		this.token = token;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public Node getLeftChild(){
		return this.leftChild;
	}
	
	public Node getRightChild(){
		return this.rightChild;
	}
	
	
	@Override
	public void printContents() {
		System.out.println("----- PrintNode -----");
		System.out.println("Left: " + this.leftChild);
		System.out.println("Right: " + this.rightChild);
		
	}
	
	

}
