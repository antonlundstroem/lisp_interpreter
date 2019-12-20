package lexer;

import java.util.Iterator;
import java.util.function.Consumer;


public class PeekableIterator<T> implements Iterator<T>{
	
	private T nextItem;
	private Iterator<T> iterator;
	
	public PeekableIterator(Iterator<T> iterator){
		this.iterator = iterator;
	}
	
	public T peekNext(){
		if (!hasNext()){
			//Throw exception
		}
		return nextItem;
	}

	@Override
	public void forEachRemaining(Consumer<? super T> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasNext() {
		if (nextItem != null)
			return true;
		
		if (iterator.hasNext())
			nextItem = iterator.next();

		return nextItem != null;
	}

	@Override
	public T next() {
		if(!hasNext()){
			//throw exception
		}
		
		T toReturn = nextItem;
		nextItem = null;
		return toReturn;		
	}

	@Override
	public void remove() {
		//NYI
		
	}

}
