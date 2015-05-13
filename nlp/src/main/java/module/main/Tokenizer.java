package module.main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import module.db.Database;
import module.interfaces.TokenizerListener;

public class Tokenizer {

	private Database DB;	
	private List<String> TOKENS;
	
	public static final String KEY_WORD = "kata";
	public static final String KEY_CTYPE = "tipe_dasar";
	public static final String KEY_STYPE = "tipe_spesifik";
	
	public Tokenizer(Database db) {
		this.DB = db;
	}
	
	public Tokenizer(Database db, String sentence) {
		
		try{
			TOKENS = Arrays.asList(sentence.split(" "));
			if(TOKENS.size() == 0){
				throw new Exception("Pertanyaan kosong!");
			}
			
			DB = db;
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	public void tokenize(TokenizerListener listener){
		List<Map<String,String>> res = new ArrayList<Map<String, String>>();
		String query = "SELECT * FROM lexicon WHERE kata = ?";
		
		try{
			listener.onTokenizerUpdate("Building database query...");
			PreparedStatement stmt = DB.preparedStatement(query);
			
			for(String t: TOKENS){
				stmt.setString(1, t);
				
				listener.onTokenizerUpdate("Database search for token: " + t + "...");
				ResultSet r = stmt.executeQuery();
				
				Map<String,String> resMap = new HashMap<String, String>();
				
				resMap.put(KEY_WORD, t);
				
				if(r.isBeforeFirst()){
					listener.onTokenizerUpdate("Type found for token " + t);
					r.absolute(1);
					resMap.put(KEY_CTYPE, r.getString(KEY_CTYPE));
					resMap.put(KEY_STYPE, r.getString(KEY_STYPE));
					
				} else {
					listener.onTokenizerUpdate("Type not found for token " + t);
				}
				
				res.add(resMap);
			}
			
			DB.closeConnection(stmt);
			
			listener.onTokenizeSuccess(res);
			
		} catch(SQLException e){
			listener.onTokenizeFail(e.getMessage());
		}
	}
	
	public List<Map<String, String>> tokenize(){
		List<Map<String,String>> res = new ArrayList<Map<String, String>>();
		
		String query = "SELECT * FROM lexicon WHERE kata = ?";
		
		try{
			PreparedStatement stmt = DB.preparedStatement(query);
			
			for(String t: TOKENS){
				stmt.setString(1, t);
				ResultSet r = stmt.executeQuery();
				
				Map<String,String> resMap = new HashMap<String, String>();
				
				resMap.put(KEY_WORD, t);
				
				if(r.isBeforeFirst()){
					
					r.absolute(1);
					resMap.put(KEY_CTYPE, r.getString(KEY_CTYPE));
					resMap.put(KEY_STYPE, r.getString(KEY_STYPE));
					
				}
				
				res.add(resMap);
			}
			
			DB.closeConnection(stmt);
			
		} catch(SQLException e){
			
		}
		
		return res;
	}
}
