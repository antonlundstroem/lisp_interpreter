package evaluator;

import ast.AbstractSyntaxTree;
import ast.Node;

public class Evaluator{
	
	AbstractSyntaxTree ast;
	NodeVisitor nv;
	Node root;
	
	public Evaluator(AbstractSyntaxTree ast){
		this.ast = ast;
		root = ast.getRoot();
	}
	
	public Evaluator(){}
	
	public void eval(){
		SymbolTable st = new SymbolTable();
		
		//SymbolTableBuilder stb = new SymbolTableBuilder(st);
		//stb.visit(root);

		NodeVisitor nv = new NodeVisitor(st);
		nv.visit(root);
		
		//st.printContents();
	}
	
	public void eval(AbstractSyntaxTree ast){
		this.ast = ast;
		this.root = ast.getRoot();
		eval();
	}
}
