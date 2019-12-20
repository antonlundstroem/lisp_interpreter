package ast;

import java.util.List;

public class BlockNode extends Node {
	List<Node> statements;
	
	public BlockNode(List<Node> block){
		this.statements = block;
	}
	
	public List<Node> getBlock(){		
		return this.statements;
	}
	
	@Override
	public void printContents() {
		System.out.println("----- BLOCK NODE -----");
		System.out.println(this.statements);
		
	}

}
