package tests;

import static org.junit.Assert.*;
import lexer.FileParser;
import lexer.Lexer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ast.AbstractSyntaxTree;

import parser.Parser;

public class ParserTester {
	Lexer lexer, fpLexer;
	Parser parser, fpParser;
	FileParser fp;
	final static String FILE_PATH = "/home/antlun/Desktop/skola/Algoritmer_Datastrukturer/project/lispfile";
	
	
	@Before
	public void setUp() throws Exception {
		// Fix error handling, try with a parenthesis wrong
		
		fp = new FileParser(FILE_PATH);
		fpLexer = new Lexer(fp.charsInFileIterator());
		fpParser = new Parser();
		
		lexer = new Lexer();
		parser = new Parser();
	}

	@After
	public void tearDown() throws Exception {
		lexer = null;
		parser = null;
	}
	
	//@Test
	public void testFpParser(){
		AbstractSyntaxTree ast = fpParser.parse(fpLexer.lex());
	}
	
	@Test
	public void testParsedList() {
		String binOpString = ("(defun A (+ A 2)) (A 2)");
		AbstractSyntaxTree ast = parser.parse(lexer.lex(binOpString));
	}

}
