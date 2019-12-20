package ast;

import java.util.List;

public class ListNode extends Node{
	List<SymbolNode> list;
	public ListNode(List<SymbolNode> list){
		this.list = list;
	}
	
	public List<SymbolNode> getSymbols(){
		return this.list;
	}
	@Override
	public void printContents() {
		// TODO Auto-generated method stub
		
	}

}
