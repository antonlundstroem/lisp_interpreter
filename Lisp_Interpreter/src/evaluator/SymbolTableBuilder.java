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
import ast.ProcedureSymbolNode;
import ast.SymbolNode;
import ast.UnaryOpNode;
import ast.VarSymbolNode;

public class SymbolTableBuilder extends Visitor {
	SymbolTable st;
	String scope = "global";
	
	public SymbolTableBuilder(SymbolTable st){
		this.st = st;
	}
	
	public void evaluate(BinOpNode node){
		visit(node.getLeftChild());
		visit(node.getRightChild());
	}
	
	public void evaluate(NumberLiteralNode node){
		
	}
	
	public void evaluate(UnaryOpNode node){
		
	}
	
	public void evaluate(SymbolNode node){
		
	}
	
	public void evaluate(VarSymbolNode node){
		
	}
	
	public void evaluate(ProcedureNode node){
		String symbolName = node.getSymbol();
		st.define(scope, symbolName, node.getRightChild());
		// Should be "global"????
	}

	public void evaluate(ProcedureSymbolNode node){
		/// TODO
	}
	
	public void evaluate(AssignmentNode node){

	}
	
	public void evaluate(BlockNode node){
		for(Node n : node.getBlock()){
			visit(n);
		}
	}
	
	public void evaluate(ListNode node){
		//TODO impl
	}
	
	public void evaluate(NoOpNode node){
		
	}
	
	public void evaluate(PrintNode node){}
	
	public void evaluate(ProcCallNode node){
		System.out.println("here");
	}
	
	
}
