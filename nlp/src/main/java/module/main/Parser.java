package module.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import module.db.Database;

public class Parser {

	List<List<String>> parseTree;

	public Parser(Database db, String sentence) {
		List<Map<String,String>> token = new Tokenizer(db,sentence).tokenize();
		parseTree = new ArrayList<List<String>>();
		generateParseTree(token);
	}
	
	private final void generateParseTree(List<Map<String,String>> token){
		
		Iterator<Map<String,String>> it = token.iterator();
		
		while(it.hasNext()){
			 
		}
		
	}
	
	public List<List<String>> getParseTree(){
		return parseTree;
	}
}
