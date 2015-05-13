package module.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import module.db.Database;

public class MySQLDatabase extends Database{
	
	public MySQLDatabase(String db_name, String table_name, String db_user, String db_pass) {
		super("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1/" + db_name, table_name, db_user, db_pass);
	}
	
	public MySQLDatabase(String db_name, String db_user, String db_pass) {
		this(db_name, "lexicon", db_user, db_pass);
	}
	
	public MySQLDatabase(String db_user, String db_pass) {
		this("new_lexicon","lexicon",db_user, db_pass);
	}

	@Override
	public ResultSet runQuery(String query) {
		
		try {
			Statement stmt = DB_CONNECTION.createStatement();			
			return stmt.executeQuery(query);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean closeConnection(Statement stmt) {
		try {
			stmt.close();
			super.DB_CONNECTION.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public PreparedStatement preparedStatement(String query) {
		
		try {
			return super.DB_CONNECTION.prepareStatement(query);
		} catch (SQLException e) {
			System.out.println("can not create prepared Statement object!");
		}
		return null;
	}
}
