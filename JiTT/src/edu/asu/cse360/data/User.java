<<<<<<< HEAD
package edu.asu.cse360.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends SQLEntity {
	
	private String userName;
	private String lastName;
	private String firstName;
	private boolean isInstructor;
	
	public User() throws ClassNotFoundException {
		super();
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isInstructor() {
		return isInstructor;
	}

	public void setInstructor(boolean isInstructor) {
		this.isInstructor = isInstructor;
	}

	public int insert() throws Exception {
		StringBuffer query = new StringBuffer();
		if(this.getUserName() == null || this.getUserName().length() < 1) {
			throw new SQLException("Cannot persist a user with no user name.");
		}
				
		query.append("INSERT INTO `user`(`username`, `last`, `first`, `isInstructor`) VALUES (");
		query.append(quotes(this.getUserName()));
		query.append(",");
		query.append(quotes(this.getLastName()));
		query.append(",");
		query.append(quotes(this.getFirstName()));
		query.append(",");
		query.append(this.isInstructor() ? 1 : 0);
		query.append(")");
		
		return super.insert(query.toString());
	}

	public int update() throws Exception {
		StringBuffer query = new StringBuffer();
		if(this.getUserName() == null || this.getUserName().length() < 1) {
			throw new SQLException("Cannot persist a user with no user name.");
		}
		
		query.append("UPDATE `user` SET `last`=");
		query.append(quotes(this.getLastName()));
		query.append(",`first`=");
		query.append(quotes(this.getFirstName()));
		query.append(",`isInstructor`=");
		query.append(this.isInstructor() ? 1 : 0);
		query.append(" WHERE `username`=");
		query.append(quotes(this.getUserName()));
		
		return super.update(query.toString());
	}

	public int delete() throws SQLException {
		StringBuffer query = new StringBuffer();
		if(this.getUserName() == null || this.getUserName().length() < 1) {
			throw new SQLException("Cannot delete a user with no user name.");
		}
		
		query.append("DELETE FROM `user` WHERE `username`=");
		query.append(quotes(this.getUserName()));
		
		return super.delete(query.toString());
		
	}

	public boolean select() throws SQLException {
		boolean success = false;
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT `username`, `last`, `first`, `isInstructor` FROM `user` WHERE `username`= ");
		query.append(quotes(this.getUserName()));
	
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    

		while (results.next()) {
			this.setLastName(results.getString("last"));
			this.setFirstName(results.getString("first"));
			this.setInstructor(results.getBoolean("isInstructor"));
			success = true;;
		}
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return success;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			User u = new User();
			u.setUserName("belly");
			u.setLastName("Betty");
			u.setFirstName("Sue");
			u.setInstructor(false);
	
//			u.select();
			u.insert();
			System.out.println(u.getUserName());
			System.out.println(u.getFirstName());
			System.out.println(u.getLastName());
			System.out.println(u.isInstructor());
			
//			u.setLastName("Smith");
			
//			u.update();
			
//			System.out.println(u.delete());
			
			u.commit();
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
=======
package edu.asu.cse360.data;

public abstract class User extends SQLEntity {
	
	protected String username, lastName, firstName;
	protected boolean isInstructor;
	
	public User()
	{}

	
/*** Getter and Setter ***/	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isInstructor() {
		return isInstructor;
	}
	


}
>>>>>>> 740b003273b3064d2e2731216538b2078bc7a60d
