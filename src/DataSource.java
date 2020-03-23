import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DataSource{
	
	private static DataSource instance = null;
	
	private String db = "jdbc:mysql://apontejaj.com:3306/customer?useSSL=false";
	private String un = "cctstudent";
	private String pw = "Pass1234!";
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs = null;
		
	
	private DataSource() {
		
		try{
			
			// Get a connection to the database
			conn = DriverManager.getConnection( db, un, pw ) ;

			// Get a statement from the connection
			stmt = conn.createStatement() ;

		}
		catch( SQLException se ){
			System.out.println( "SQL Exception:" ) ;

			// Loop through the SQL Exceptions
			while( se != null ){
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e ){
			System.out.println( e ) ;
		}
	}
	
	public static DataSource getInstance() {
		
		if(instance==null) {
			instance = new DataSource();
		}
		return instance;
		
	}
	
	
	public ResultSet select(String query) {
		
		
		try {
			rs = stmt.executeQuery( query ) ;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void closing() {

		try {
			if(rs != null) {
				rs.close();
			}
			stmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean save(String query) {
		
		try {
			stmt.execute( query ) ;
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
