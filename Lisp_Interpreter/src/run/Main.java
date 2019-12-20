package run;

import lexer.FileParser;
import lexer.Lexer;
import evaluator.Evaluator;
import parser.Parser;
import repl.Repl;

public class Main {

	public static void main(String[] args) {
		if (args.length == 0 || args.length > 3){
			System.out.println("--repl to start repl, -f /path/to/file");
		} else if (args.length == 1) {
			switch (args[0]) {
			case "-h":
				System.out.println("--repl to start repl, -f /path/to/file");
				break;
			case "--repl":
				startRepl();
			default:
				System.out.println("Unrecognized flag. Use --repl to start repl, -f /path/to/file");;
			}
		} else if (args.length == 2){
			lspFromFile(args[1]);
		} else {
			System.out.println("--repl to start repl, -f /path/to/file");
		}
		

	}
	
	private static void startRepl(){
		Repl repl = new Repl();
		repl.start();
	}
	
	private static void lspFromFile(String file){
		FileParser fileParser = new FileParser(file);
		Lexer lexer = new Lexer(fileParser.charsInFileIterator());
		Parser parser = new Parser();
		Evaluator evaluator = new Evaluator();
		evaluator.eval((parser.parse(lexer.lex())));		
	}

}
