package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import repl.Repl;

public class ReplTester {
	Repl repl;
	@Before
	public void setUp() throws Exception {
		repl = new Repl();
	}

	@After
	public void tearDown() throws Exception {
		repl = null;
	}

	@Test
	public void testRepl() {
		repl.start();
	}
}

// TODO:::

/// Fix the constructors to be prettier for the parser, lexer and evaluator
// (defun a (+ a 3) (a 1)

// In the global scope, a should still be a FUNCTION, but the in the scope it should be an int.