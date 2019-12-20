package evaluator;

import java.util.HashMap;
import java.util.Map;

import exceptions.SymbolTableException;

import ast.Node;

public class SymbolTable {
	
	Map<String, HashMap<String, String>> st;
	Map<String, HashMap<String, Integer>> scopedInts;
	Map<String, HashMap<String, String>> scopedStrings;
	Map<String, HashMap<String, Node>> scopedNodes;


	public SymbolTable(){
		st = new HashMap<String, HashMap<String, String>>();
		scopedInts = new HashMap<String, HashMap<String, Integer>>();
		scopedStrings = new HashMap<String, HashMap<String, String>>();
		scopedNodes = new HashMap<String, HashMap<String, Node>>();
	}
	
	private void mapToSt(String scope, String symbol, String type){
		if (!st.containsKey(scope)){
			st.put(scope, new HashMap<String, String>());
		}
		st.get(scope).put(symbol, type);
	}
	
	public void define(String scope, String symbol, String str){
		if (!scopedStrings.containsKey(scope))
			scopedStrings.put(scope, new HashMap<String, String>());
		
		
		mapToSt(scope, symbol, "STRING");
		scopedStrings.get(scope).put(symbol, str);
	}
	
	public void define(String scope, String symbol, Node node){
		if (!scopedNodes.containsKey(scope))
			scopedNodes.put(scope, new HashMap<String, Node>());
		

		mapToSt(scope, symbol, "FUNCTION");
		scopedNodes.get(scope).put(symbol, node);
	}

	
	public void define(String scope, String symbol, int value){
		if (!scopedInts.containsKey(scope))
			scopedInts.put(scope, new HashMap<String, Integer>());
		
		
		mapToSt(scope, symbol, "INTEGER");
		scopedInts.get(scope).put(symbol, value);
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T lookup(String scope, String symbol){
		T stEntry = null;
		if (!st.get(scope).containsKey(symbol)){
			throw new SymbolTableException("Could not find symbol: " + symbol + " in scope: " + scope);
		}
		
		if (isInSymbolTable(scope, symbol, "INTEGER")){
			stEntry = (T) scopedInts.get(scope).get(symbol);
			
		} else if (isInSymbolTable(scope, symbol, "STRING")){
			stEntry = (T) scopedStrings.get(scope).get(symbol);
			
		} else if (isInSymbolTable(scope, symbol, "FUNCTION")){
			stEntry = (T) scopedNodes.get(scope).get(symbol);
		} else {
			throw new SymbolTableException("Error in lookup for symbol:" + symbol + " in scope: " + scope);
		}

		return stEntry;
	}
	
	private boolean isInSymbolTable(String scope, String symbol, String type){
		return st.get(scope).get(symbol).equals(type);
	}
	
	public void printContents(){
		System.out.println("Symbol Table: " + st);
		System.out.println("Scoped ints: " + scopedInts);
		System.out.println("Scoped strings: " + scopedStrings);
		System.out.println("Scoped nodes: " + scopedNodes);
	}
}
