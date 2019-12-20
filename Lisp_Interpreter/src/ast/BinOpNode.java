package ast;


import token.Token;


public class BinOpNode extends Node {
	
	Node leftChild, rightChild;
	String operator;
	Token token;
	
	public BinOpNode(Token token, Node leftChild, Node rightChild){
		this.leftChild = leftChild;
		this.token = token;
		this.operator = token.getValue();
		this.rightChild = rightChild;
	}
	
	public String getOperator(){
		return this.operator;
	}
	
	public Node getLeftChild(){
		return this.leftChild;
	}
	
	public Node getRightChild(){
		return this.rightChild;
	}
	
	@Override
	public void printContents(){
		System.out.println("------- BINOP NODE -------");
		System.out.println("Node: " + this.operator);
		System.out.printf("Left: %s\n", this.leftChild);
		System.out.print(" ");
		this.leftChild.printContents();
		System.out.println("");
		System.out.printf("Right: %s\n", this.rightChild);
		System.out.print(" ");
		this.rightChild.printContents();
		System.out.println("\n");
	}
}