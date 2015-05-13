package module.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {
	
	private String TB_NAME;
	protected Connection DB_CONNECTION;
	
	public Database(String dbDriver, String db, String user, String pass) {
		this(dbDriver, db, user, pass, null);
	}
	
	public Database(String dbDriver, String db, String tb_name, String user, String pass) {
		TB_NAME = (tb_name == null) ? "lexicon" : tb_name;
		try {
			Class.forName(dbDriver).newInstance();
			DB_CONNECTION = DriverManager.getConnection(db, user, pass);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public final String getTableName(){
		return this.TB_NAME;
	}
	
	public abstract ResultSet runQuery(String query);
	public abstract PreparedStatement preparedStatement(String query);
	public abstract boolean closeConnection(Statement stmt);
}
