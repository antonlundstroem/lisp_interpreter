package ast;


public class AbstractSyntaxTree {
	
	Node root;
	
	public AbstractSyntaxTree(Node root){
		this.root = root;
	}
	
	public Node getRoot(){
		return this.root;
	}
	
}
