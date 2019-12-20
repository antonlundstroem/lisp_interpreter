package ast;

import evaluator.IVisitable;
import evaluator.Visitor;

public abstract class Node implements IVisitable{
	
	public abstract void printContents();

}
