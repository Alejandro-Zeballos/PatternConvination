import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DataSourceSingleton{
	
	//Singleton with lazy implementation
	
	private static DataSourceSingleton instance = null;
	
	private String db = "jdbc:mysql://apontejaj.com:3306/world?useSSL=false";
	private String un = "cctstudent";
	private String pw = "Pass1234!";
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs = null;
		
	
	private DataSourceSingleton() {
		
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
	
	//this method will return the instance of the singleton
	public static DataSourceSingleton getInstance() {
		
		if(instance==null) {
			instance = new DataSourceSingleton();
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
		//this method will close the connection, statement and resultSet if the DAO.
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
	
	//this method is in charge of saving something to the database
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
