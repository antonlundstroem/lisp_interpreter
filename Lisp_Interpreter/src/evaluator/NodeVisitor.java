package evaluator;


import ast.AssignmentNode;
import ast.BinOpNode;
import ast.BlockNode;
import ast.ListNode;
import ast.NoOpNode;
import ast.Node;
import ast.NumberLiteralNode;
import ast.PrintNode;
import ast.ProcCallNode;
import ast.ProcedureNode;
import ast.StringLiteralNode;
import ast.SymbolNode;
import ast.UnaryOpNode;

public class NodeVisitor extends Visitor {
	
	private int result;
	private String str = "";
	private SymbolTable st;
	private String scope = "global";
	
	public NodeVisitor(SymbolTable st){
		this.st = st;
	}
	
	public void evaluate(BinOpNode node) {
		Node leftChild = null;
		Node rightChild = null;
		
		if (node.getOperator().equals("+")){
			leftChild = node.getLeftChild();
			rightChild = node.getRightChild();
			visit(leftChild);
			int leftResult = result;
			visit(rightChild);
			result = leftResult + result;
			
		} else if (node.getOperator().equals("-")){
			leftChild = node.getLeftChild();
			rightChild = node.getRightChild();
			visit(leftChild);
			int leftResult = result;
			visit(rightChild);
			result = leftResult - result;
			
		} else if (node.getOperator().equals("*")){
			leftChild = node.getLeftChild();
			rightChild = node.getRightChild();
			visit(leftChild);
			int leftResult = result;
			visit(rightChild);
			result = leftResult * result;
			
		} else if (node.getOperator().equals("/")){
			leftChild = node.getLeftChild();
			rightChild = node.getRightChild();
			visit(leftChild);
			int leftResult = result;
			visit(rightChild);
			result = leftResult / result;
		}
	}
	
	public void evaluate(UnaryOpNode node){
		if (node.getOperator().equals("+")){
			visit(node.getChild());
			result = +result;
			
		} else if (node.getOperator().equals("-")){
			visit(node.getChild());
			result = -result;
		}
	}
	
	public void evaluate(NumberLiteralNode node) {
		result = Integer.parseInt(node.getValue());
	}
	
	public void evaluate(SymbolNode node){
		String symbolName = node.getSymbol();
		int value = st.lookup(scope, symbolName);
		result = value;
	}
	
	
	public void evaluate(ProcedureNode node){
		String symbolName = node.getSymbol();
		st.define(scope, symbolName, node.getRightChild());
	}
	
	public void evaluate(ProcCallNode node){
		String symbolName = node.getSymbol();
		Node n = st.lookup(scope, symbolName);
		
		scope = node.getSymbol();
		
		visit(node.getChild());
		
		st.define(scope, symbolName, result);
		
		visit(n);
		
		scope = "global";
	}
	
	
	public void evaluate(AssignmentNode node){
		visit(node.getRightChild());
		st.define(scope, node.getLeftChild().getSymbol(), result);
	}
	
	public void evaluate(BlockNode node){
		for (Node n : node.getBlock()){
			visit(n);
		}
	}
	
	public void evaluate(PrintNode node){
		visit(node.getLeftChild());
		visit(node.getRightChild());
		System.out.println(result);
		System.out.println(str);
		
	}
	
	public void evaluate(StringLiteralNode node){
		String nodeString = node.getString();
		str = nodeString;
	}
	
	public void evaluate(ListNode node){
		for (SymbolNode sn : node.getSymbols()){
			str += sn.getSymbol() + " ";
		}
	}
	
	public void evaluate(NoOpNode node){}
	
	public int getResult(){return this.result;};
	
}
