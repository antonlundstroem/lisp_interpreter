package parser;

import java.util.ArrayList;
import java.util.List;

import exceptions.SyntaxErrorException;

import ast.AbstractSyntaxTree;
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
import ast.StringLiteralNode;
import ast.SymbolNode;
import ast.UnaryOpNode;
import ast.VarSymbolNode;

import lexer.PeekableIterator;

import token.Token;

public class Parser {
	
	PeekableIterator<Token> peeker;
	Token currentToken;
	AbstractSyntaxTree ast;
	
	public Parser(){
	}
	
	public AbstractSyntaxTree parse(List<Token> tokens){
		peeker = new PeekableIterator<>(tokens.iterator());
		currentToken = peeker.next();
		
		ast = new AbstractSyntaxTree(program());
		return ast;
	}
	
		// program ::= block EOF
	private BlockNode program(){
		BlockNode node = block();
		match("EOF");
		return node;
	}
	
	
		// block ::= ( statements )
	private BlockNode block(){
		match("(");
		List<Node> statements = statements();
		match(")");
		
		if (cTypeEquals("(")){
			statements.add(block());
		}

		return new BlockNode(statements);
	}
	
		// statements ::= statement |  statement statements 
	private List<Node> statements(){
		List<Node> statements = new ArrayList<>();
		Node statement = statement();
		statements.add(statement);
		
		while (cTypeEquals("(")){
			statements.add(statement());
		}
		
		return statements;
	}
		
		// statement ::= block | expr
	private Node statement(){
		Node node;
		if (cTypeEquals("(")){
			node = block();
		} else {
			node = expr();
		}
		return node;
	}
	
	
		// expr ::= assignment | binOp | ...
	private Node expr(){
		Node node = null;
		
		switch (cTokenType()) {
		case "Function":
			if (cValueEquals("defvar")){
				node = assignment();
			} else if (cValueEquals("defun")){
				node = proc();
			} else if (cValueEquals("print")){
				node = print();
			}
			break;
		case "Operator":
			node = binOp();
			break;
		case "Symbol":
			if (peeker.peekNext().getType().equals("Symbol")){
				node = procCall();
			} else if (peeker.peekNext().getType().equals("NumberLiteral")){
				node = procCall();
			} else {
				node = symbol();
			}
			break;
		case "StringLiteral":
			node = stringlit();
			break;
		case "NumberLiteral": // Can't handle negative constants yet!!!!!
			node = factor(); 
			break;
		default:
			node = noOp();
			break;
		}
		return node;
	}
	
	/// eww
	private Node list(){
		// TODO : implement :)
		List<SymbolNode> list = new ArrayList<>();
		while(cTypeEquals("Symbol")){
			list.add(symbol());
		}
		return new ListNode(list);
	}
	
	private PrintNode print(){
		Token cToken = cToken();
		match("Function");
		PrintNode node = new PrintNode(cToken, block(), noOp());
		return node;
	}
	
		// binOpStatmenet ::= Operator factor factor
	private BinOpNode binOp(){		
		Token op = cToken();
		match("Operator");
		
		Node firstFactor = factor();
		Node secondFactor = factor();
		
		BinOpNode binOp = new BinOpNode(op, firstFactor, secondFactor);

		return binOp;		
	}
	
		// factor ::= NumberLiteral | ( binOp ) | op factor | symbol 
	private Node factor(){
		Node factor = null;
		
		if (cTypeEquals("Operator")){
			Token cToken = cToken();
			if (cValueEquals("+") || cValueEquals("-")){
				match("Operator");
				factor = new UnaryOpNode(cToken, factor());
			}
				
		} else if(cTypeEquals("NumberLiteral")){
			factor = new NumberLiteralNode(cToken());
			match("NumberLiteral");
			
		} else if (cTypeEquals("(")){
			match("(");
			factor = binOp();
			match(")");
		} else {
			factor = symbol();
		}
		
		return factor;
	}
	
	private StringLiteralNode stringlit(){
		Token cToken = cToken();
		match("StringLiteral");
		
		return new StringLiteralNode(cToken);
	}
	
		// assignment ::= function symbol factor
	private Node assignment(){
		Token token = cToken();
		match("Function");
		SymbolNode leftChild = symbol();
		Node rightChild = factor();
		Node assNode = new AssignmentNode(token, leftChild, rightChild);
		return assNode;
	}
	
	private ProcCallNode procCall(){
		Token cToken = cToken();
		match("Symbol");
		return new ProcCallNode(cToken, expr());
	}
	
	// proc ::= function symbol (block)
	private ProcedureNode proc(){
		ProcedureNode node = null;
		Token cToken = cToken();
		match("Function");
		ProcedureSymbolNode psn = procSymbol();
		Node block = block();
		node = new ProcedureNode(cToken, psn, block);
		return node;
	}
	
		// symbol ::= varsymbol | procsymbol
	private SymbolNode symbol(){
		SymbolNode symbol = null;
		if (cTypeEquals("VarSymbol")){
			symbol = varSymbol();
		} else if (cTypeEquals("ProcedureSymbol")){
			symbol = procSymbol();
		} else {
			Token cToken = cToken();
			match("Symbol");
			symbol = new SymbolNode(cToken); 
		}
		return symbol;
	}
	
		// varSymbol :: = varSymbol
	private VarSymbolNode varSymbol(){
		VarSymbolNode node = new VarSymbolNode(cToken());
		match("VarSymbol");
		return node;
	}
		// procedureSymbol ::= procedureSymbol
	private ProcedureSymbolNode procSymbol(){
		ProcedureSymbolNode node = new ProcedureSymbolNode(cToken());
		match("ProcedureSymbol");
		return node;
	}
	
		// noOp ::= <>
	private Node noOp(){
		return new NoOpNode();
	}
	
	private Token cToken(){
		return this.currentToken;
	}

	private String cTokenType(){
		return currentToken.getType();
	}
	
	private String cTokenValue(){
		return currentToken.getValue();
	}
	
	private boolean cTypeEquals(String eq){
		return cTokenType().equals(eq);
	}
	
	private boolean cValueEquals(String eq){
		return cTokenValue().equals(eq);
	}
	
	private void match(String tokenType){
		if (cTypeEquals(tokenType)){
			this.currentToken = peeker.next();
		} else {
			throw new SyntaxErrorException("Expected: " + tokenType + " but got: " + cTokenType());
		}
	}
}






