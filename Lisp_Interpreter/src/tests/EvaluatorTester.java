package tests;

import static org.junit.Assert.*;
import lexer.FileParser;
import lexer.Lexer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import evaluator.Evaluator;

import parser.Parser;

public class EvaluatorTester {
	FileParser fp;
	Lexer lexer, fpLexer;
	Parser parser, fpParser;
	Evaluator evaluator, fpEvaluator;
	final static String FILE_PATH = "/home/antlun/Desktop/skola/Algoritmer_Datastrukturer/project/lispfile";

	@Before
	public void setUp() throws Exception {
		
		//fp = new FileParser(FILE_PATH);
		//fpLexer = new Lexer(fp.charsInFileIterator());
		//fpParser = new Parser();
		//fpEvaluator = new Evaluator(fpParser.parse(fpLexer.lex()));

		//String defvar = ("(defvar  3)");
		lexer = new Lexer();
		parser = new Parser();
		//evaluator = new Evaluator(parser.parse(lexer.lex(defvar)));
	}

	@After
	public void tearDown() throws Exception {
		lexer = null;
		parser = null;
		evaluator = null;
	}
	
	private void eval(String string){
		evaluator = new Evaluator(parser.parse(lexer.lex(string)));
		evaluator.eval();
	}
	@Test
	public void testDefvar(){
		String defvar = "(defvar A -5) (print (+ 6 A)) (print (+ 3 3))";
		eval(defvar);
	}
	
	//@Test
	public void testDefFun() {
		String defun = "(defun func (+ func 2))";
		eval(defun);
	}
	
	@Test
	public void testDefFuncFunc(){
		String defun = "";
		//eval(defun);
	}
	
	//@Test
	public void testFileParserEval(){
		fpEvaluator.eval();
	}

}
