package dictionary;

import java.util.HashMap;
import java.util.Map;

import token.Token;

public class Dictionary {
	final Map<String, Token> KEYWORDMAP;
	private String prev = "";
	
	public Dictionary(){
		KEYWORDMAP = new HashMap<String, Token>();
		generateMap();
	}
	
	private void generateMap(){
		KEYWORDMAP.put("defvar", new Token("Function", "defvar"));
		KEYWORDMAP.put("defun", new Token("Function", "defun"));
		KEYWORDMAP.put("incf", new Token("Function", "incf"));
		KEYWORDMAP.put("decf", new Token("Function", "decf"));
		KEYWORDMAP.put("print", new Token("Function", "print"));
	}
	
	public Token matchKeyword(String string){
		if (!KEYWORDMAP.containsKey(string)){
			if (prev.equals("defun")){
				prev = "";
				return new Token("ProcedureSymbol", string);
			} else if (prev.equals("defvar")){
				prev = "";
				return new Token("VarSymbol", string);
			}
			return new Token("Symbol", string); /// returns a "default" token that will be used for variables
		}
		
		prev = string;
		return KEYWORDMAP.get(string);
	}
	

}
