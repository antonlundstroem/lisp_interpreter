package evaluator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public abstract class Visitor {
	/**
	 * Finds the correct method for the visitor depending on the subclass of the abstract class Node.
	 * 
	 * @param node
	 */
	
	
	public void visit(IVisitable v){
		Method m = findMethod(v);
		try {
			m.invoke(this, v);
		} catch (IllegalAccessException e) {
			System.err.println("Error in visit in Visitor class");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println("Error in visit in Visitor class");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.err.println("Error in visit in Visitor class");
			e.printStackTrace();
		}
	}
	
	private Method findMethod(IVisitable v){
		String methodName = "evaluate";
		Class visitable = v.getClass();
		Method m = null;
		try {
			m = getClass().getMethod(methodName, visitable);
			return m;
		} catch (NoSuchMethodException e) {
			System.err.println("Error in visitGenerator in Visitor class");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.err.println("Error in visitGenerator in Visitor class");
			e.printStackTrace();
		}
		return m;
	}

}
