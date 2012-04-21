
package edu.asu.cse360.data;

import java.sql.*;

public class SQLEntity {
	
	Connection connection;
	
	static {
		try {
		Class.forName("com.mysql.jdbc.Driver"); // Setup the connection with the DB
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public SQLEntity() {
	
	}
	
	public SQLEntity(Connection con) throws SQLException {
		if (con != null && !con.isClosed()) {
			connection = con;
		}
	}
	
	public static Connection getConnection() throws SQLException {
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jitt", "", "");
		
		return con;
	}
	
	public static void returnConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	public int insert(String query) throws SQLException{
		return executeQuery(query);
	}

	public int update(String query) throws SQLException{
		return executeQuery(query);
	}

	public int delete(String query) throws SQLException {
		return executeQuery(query);
	}
	
	public int executeQuery(String query) throws SQLException {
		int retValue = 0;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    retValue =  statement.executeUpdate(query);
	    
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    returnConnection(con);
	    
	    return retValue;
	}
	
	public ResultSet select(String query) throws SQLException {
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query);
	    
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);

		return results;
	}
	
	public static ResultSet selectAll(String query) throws SQLException {
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query);
	    
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    returnConnection(con);

		return results;
	}

	public void commit() {
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public void rollBack() {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void finalize() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static String quotes(String str) {
		if (str != null) {
			str = "'"+str+"'";
		}
		return str;
	}	
	
	public static String backQuotes(String str) {
		if (str != null) {
			str = "`"+str+"`";
		}
		return str;
	}
	
}
