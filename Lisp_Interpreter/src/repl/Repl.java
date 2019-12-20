package repl;

import java.util.Scanner;

import evaluator.Evaluator;

import parser.Parser;

import lexer.Lexer;

public class Repl {
	
	Lexer lexer;
	Parser parser;
	Evaluator evaluator;
	
	public Repl() {
		lexer = new Lexer();
		parser = new Parser();
		evaluator = new Evaluator();
	}
	
	
	public void start(){
		String inp = "";
		Scanner scanner = new Scanner(System.in);
		
		System.out.println(")) lisp repl ((");
		System.out.println("type 'q' to quit");
		System.out.print("lsp> ");
		
		
		
		while(scanner.hasNext()){
			lexer.removeEOF();
			
			inp = scanner.nextLine();
			
			if (inp.equals("q")){
				System.out.println("exiting repl");
				System.exit(0);
			}
			eval(inp);
			System.out.print("lsp> ");
		}
		scanner.close();
	}
	
	private void eval(String string){
		evaluator.eval((parser.parse(lexer.lex(string))));
	}

	
	

}
