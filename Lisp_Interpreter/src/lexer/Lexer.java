package lexer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import dictionary.Dictionary;
import exceptions.SyntaxErrorException;

import token.Token;

public class Lexer {

	List<String> tokens = new ArrayList<>();
	List<Token> tokenList = new ArrayList<>();
	
	Iterator<Character> charIter;
	Character ch;
	
	PeekableIterator<Character> peekIter;
	Dictionary dict;
	
	public Lexer(Iterator<Character> charIter){
		this.charIter = charIter; 
		peekIter = new PeekableIterator<>(this.charIter);
		dict = new Dictionary();
	}
	
	public Lexer(){
		dict = new Dictionary();
	}
	
	public List<Token> lex(String input){
		this.charIter = convertInputStringToIterator(input);
		peekIter = new PeekableIterator<>(this.charIter);
		return lex();
	}

	public List<Token> lex(){
		
		while(peekIter.hasNext()){
			ch = peekIter.next();
			
			if (ch == '"' || ch == '\'' ){
				tokenize("StringLiteral", scanString(ch));
				
			} else if (Pattern.matches("\\>|\\<|\\(|\\)|\\{|\\}|\\,|\\;|\\=|\\:", ch.toString())){
				tokenize(scanComparator(ch), "");
				
			}  else if (Pattern.matches("\\+|\\-|\\/|\\*", ch.toString())){
				tokenize("Operator", ch.toString());
				
			} else if (Character.isDigit(ch)){
				tokenize("NumberLiteral", scanInteger(ch));
				
			} else if (Character.isLetter(ch)){
				tokenize(dict.matchKeyword(scanSymbol(ch)));
				
			} else {
				// Ignore whitespace and line endings
			}
		}
		tokenize("EOF", "EOF");
		return tokenList;
	}
	
	private String scanInteger(Character firstInt){
		String str = "" + firstInt;
		Character nextChar = null;
		
		while(peekIter.hasNext() && Character.isDigit(peekIter.peekNext())){
			nextChar = peekIter.next();
			str += nextChar;
		}
		return str;		
	}
	
	private String scanString(Character stringOpener){
		String str = "";
		Character nextChar = null;
		
		while (peekIter.hasNext() && nextChar != stringOpener){
			nextChar = peekIter.next();
			
			if (nextChar == stringOpener)
				return str;
			
			/// Handle exception for when the string is never closed
			str += nextChar;
		}
		return str;
	}
	
	private String scanSymbol(Character firstChar){
		String str = "" + firstChar;
		Character nextChar = null;
		
		while(peekIter.hasNext() && Character.isLetter(peekIter.peekNext())){
			nextChar = peekIter.next();
			str += nextChar;
		}
		return str;
	}
	


	private String scanComparator(Character firstChar){
		String comp = "";
		
		if (firstChar.equals('>') || firstChar.equals('<') && peekIter.peekNext().equals('=')){
			Character eq = peekIter.next();
			comp = firstChar.toString() + eq.toString();
			
		} else if(firstChar.equals('/') && peekIter.peekNext().equals('=')){
			Character eq = peekIter.next();
			comp = firstChar.toString() + eq.toString();
			
		} else {
			comp = firstChar.toString();
		}
		
		return comp;
	}
		
	private Iterator<Character> convertInputStringToIterator(String input){
		List<Character> tmp = new ArrayList<>();
		
		for (int i = 0; i < input.length(); i ++)
			tmp.add(input.charAt(i));
		
		return tmp.iterator();
	}
	
	public void printTokenList(){
		for (Token t : tokenList){
			System.out.printf("(\"%s\", \"%s\")\n", t.getType(), t.getValue());
		}
	}
	
	private void tokenize(String type, String value){
		tokenList.add(new Token(type, value));
	}
	
	private void tokenize(Token token){
		tokenList.add(token);
	}
	
	/**
	 * Used by the REPL to remove the EOF token when a new input is to be evaluated
	 * 
	 */
	
	public void removeEOF(){
		if (!tokenList.isEmpty())
			tokenList.remove(tokenList.size() - 1);
		
	}
	
}
