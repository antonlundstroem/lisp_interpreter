package lexer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileParser {

	String fileToRead;
	FileReader fr;
	
	public FileParser(String fileToRead){
		this.fileToRead = fileToRead;
	}
	
	public List<String> readFile(){
		List<String> readFile = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(this.fileToRead))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       readFile.add(line);
		    }
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return readFile;
	}
	
	private List<Character> fileToCharArray(){
		List<Character> charsInFileArray = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(this.fileToRead))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	for(int i = 0; i < line.length(); i++)
		    		charsInFileArray.add(line.charAt(i));
		    	
		    }
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return charsInFileArray;
		
	}
	
	public Iterator<Character> charsInFileIterator(){
		Iterator<Character> charsInFileIterator = fileToCharArray().iterator();
		return charsInFileIterator;
	}
}
