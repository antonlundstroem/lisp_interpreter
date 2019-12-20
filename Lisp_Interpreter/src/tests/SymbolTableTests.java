package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ast.NoOpNode;
import ast.Node;
import ast.ProcedureSymbolNode;
import ast.SymbolNode;

import evaluator.SymbolTable;

public class SymbolTableTests {
	SymbolTable st;
	@Before
	public void setUp() throws Exception {
		st = new SymbolTable();
		NoOpNode noop = new NoOpNode();
		st.define("global", "funct", noop);
		st.define("R", "R", 2);
		st.define("R", "K", "Hello World");
	
	}

	@After
	public void tearDown() throws Exception {
		st = null;
	}

	@Test
	public void test() {
		st.printContents();
	}

}
