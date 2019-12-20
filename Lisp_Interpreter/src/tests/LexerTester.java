package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lexer.FileParser;
import lexer.Lexer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LexerTester {
	FileParser fp;
	Lexer iterLexer, stringLexer;
	final static String FILE_PATH = "/home/antlun/Desktop/skola/Algoritmer_Datastrukturer/project/lispfile";
	
	
	@Before
	public void setUp() throws Exception {
		fp = new FileParser(FILE_PATH);
		iterLexer = new Lexer(fp.charsInFileIterator()); //with Iterator
		stringLexer = new Lexer();
	}

	@After
	public void tearDown() throws Exception {
		fp = null;
		iterLexer = null;
		stringLexer = null;
	}

	//@Test
	public void testIterLexer() {
		iterLexer.lex();
		iterLexer.printTokenList();
	}
	
	@Test
	public void testStringLexer(){
		String stringLexerString = ("(defun A (+ A 2))");
		stringLexer.lex(stringLexerString);
		stringLexer.printTokenList();
	}

}