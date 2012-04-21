<<<<<<< HEAD
package edu.asu.cse360.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class Course extends SQLEntity {

	private String courseName;
	Instructor instructor;
	Semester semester;
	ArrayList<Student> studentRoster = new ArrayList<Student>();
	ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
	
	public Course() throws ClassNotFoundException {
		super();
	}
	public Course(String name) throws ClassNotFoundException {
		super();
		courseName = name;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	
	public ArrayList<Student> getStudents() {
		return this.studentRoster;
	}
	
	public int insert() throws SQLException {
		StringBuffer query = new StringBuffer();
		if(this.getCourseName() == null || this.getCourseName().length() < 1) {
			throw new SQLException("Cannot persist a course with no name.");
		}
		// TODO insert quizzes
		for (Student s : this.studentRoster) {
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO `student_roster`(`course_name`, `instructor`, `student_name`) VALUES (");
			sb.append(quotes(this.getCourseName()));
			sb.append(", ");
			sb.append(quotes(this.getInstructor().getUserName()));
			sb.append(", ");
			sb.append(quotes(s.getUserName()));
			sb.append(")");
			super.insert(sb.toString());
		}
		query.append("INSERT INTO `course`(`course_name`, `instructor`, `semester`) VALUES (");
		query.append(quotes(this.getCourseName()));
		query.append(", ");
		query.append(quotes(this.getInstructor().getUserName()));
		query.append(", ");
		query.append(quotes(this.getSemester().getName()));
		query.append(")");
		
		return super.insert(query.toString());
	}

	public int update() throws SQLException {
		StringBuffer query = new StringBuffer();
		if(this.getCourseName() == null || this.getCourseName().length() < 1) {
			throw new SQLException("Cannot persist a course with no name.");
		}

		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM `student_roster` WHERE `course_name`=");
		sb.append(quotes(this.getCourseName()));
		sb.append(" AND `instructor`=");
		sb.append(quotes(this.getInstructor().getUserName()));
		super.delete(sb.toString());
				
		for (Student s : this.studentRoster) {
			sb = new StringBuffer();
			sb.append("INSERT INTO `student_roster`(`course_name`, `instructor`, `student_name`) VALUES (");
			sb.append(quotes(this.getCourseName()));
			sb.append(", ");
			sb.append(quotes(this.getInstructor().getUserName()));
			sb.append(", ");
			sb.append(quotes(s.getUserName()));
			sb.append(")");
			super.insert(sb.toString());
		}
		
		for (Quiz q : this.quizzes) {
			q.update();
		}
		query.append("UPDATE `course` SET `instructor`=");
		query.append(quotes(this.getInstructor().getUserName()));
		query.append(", `semester`=");
		query.append(quotes(this.getSemester().getName()));
		query.append(" WHERE `course_name`=");
		query.append(quotes(this.getCourseName()));
		query.append(" AND `instructor`=");
		query.append(quotes(this.getInstructor().getUserName()));
		
		System.out.println(query.toString());
		
		return super.update(query.toString());
	}

	public int delete() throws Exception {
		StringBuffer query = new StringBuffer();
		if(this.getCourseName() == null || this.getCourseName().length() < 1) {
			throw new SQLException("Cannot delete a course with no name.");
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM `student_roster` WHERE `course_name`=");
		sb.append(quotes(this.getCourseName()));
		sb.append(" AND `instructor`=");
		sb.append(quotes(this.getInstructor().getUserName()));
		super.delete(sb.toString());
		
		for (Quiz q : this.quizzes) {
			q.delete();
		}
		
		query.append("DELETE FROM `course` WHERE `instructor`=");
		query.append(quotes(this.getInstructor().getUserName()));
		query.append(" AND `semester`=");
		query.append(quotes(this.getSemester().getName()));
		query.append(" AND `course_name`=");
		query.append(quotes(this.getCourseName()));
		
		return super.delete(query.toString());
	}

	public boolean select() throws Exception {
		boolean success = false;
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT `course_name`, `instructor`, `semester` FROM `course` WHERE `course_name`= ");
		query.append(quotes(this.getCourseName()));
		query.append(" AND `instructor`= ");
		query.append(quotes(this.getInstructor().getUserName()));
	
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			Semester s = new Semester();
			s.setName(results.getString("semester"));
			s.select();
			this.setSemester(s);
			
			// get student roster
			this.studentRoster = getStudentRoster();
			success = true;;
		}
		
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
	    
		return success;
	}
	
	public ArrayList<Student> getStudentRoster() throws Exception {
		ArrayList<Student> students = new ArrayList<Student>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT U.`username`, U.`last`, U.`first` FROM `user` AS U JOIN `student_roster` AS S ON S.`student_name` = U.`username` WHERE U.`isInstructor`= 0 ");
		query.append("AND S.`instructor` = ");
		query.append(quotes(this.getInstructor().getUserName()));
		query.append(" AND S.`course_name` = ");
		query.append(quotes(this.getCourseName()));
		query.append(" order by U.`username` ASC");
		
		System.out.println(query.toString());
		
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			Student s = new Student();
			s.setUserName(results.getString("username"));
			s.setLastName(results.getString("last"));
			s.setFirstName(results.getString("first"));
			students.add(s);
		}
				
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
	    
		return students;
	}
	
	public static ArrayList<Course> getCoursesByInstructor(String instructor) throws Exception {
		ArrayList<Course> courses = new ArrayList<Course>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `course_name`, `instructor`, `semester` FROM `course` WHERE ");
		query.append("`instructor`=");
		query.append(quotes(instructor));
		
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			Instructor i = new Instructor();
			i.setUserName(instructor);
			Course c = new Course();
			c.setCourseName(results.getString("course_name"));
			if (i != null) {
				c.setInstructor(i);
			}
			String semester = results.getString("semester");
			if (semester != null) {
				Semester s = new Semester();
				s.setName(semester);
				s.select();
				c.setSemester(s);
			}
			c.studentRoster = c.getStudentRoster();
			
			c.quizzes = Quiz.getQuizzesByCourse(c.getCourseName());
			
			courses.add(c);
		}
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return courses;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			Course c = new Course();
			c.setCourseName("CSE-362");
			Instructor i = new Instructor();
			i.setUserName("janeka");
			i.select();
			c.setInstructor(i);
			Semester sem = new Semester();
			sem.setName("Summer - 2012");
			c.setSemester(sem);
			c.select();
			c.delete();
			
			

			
			
			
			
/*			
			ArrayList<Course> courses = Course.getCoursesByInstructor("janeka");
			
			for (Course c : courses) {
				System.out.println(c.getCourseName());
				System.out.println(c.getSemester().getName());
				System.out.println(c.getInstructor().getUserName());
				ArrayList<Student> st = c.getStudents();
				for (Student s : st) {
					System.out.println(s.getUserName());
					System.out.println(s.getFirstName());
					System.out.println(s.getLastName());
				}
			}
			
			
			Course c = new Course();
			c.setCourseName("CSE-362");
			Instructor i = new Instructor();
			i.setUserName("janeka");
			i.select();
			c.setInstructor(i);
			Semester sem = new Semester();
			sem.setName("Summer - 2012");
			sem.setStartTime(new java.sql.Timestamp(System.currentTimeMillis()));
			sem.setEndTime(new java.sql.Timestamp(System.currentTimeMillis() + 360000L));
			c.setSemester(sem);
			
//			c.select();
			
			System.out.println(c.getCourseName());
			System.out.println(c.getSemester().getName());
			System.out.println(c.getInstructor().getUserName());
			
			ArrayList<Student> st = c.getStudents();
			Student s0 = new Student();
			Student s1 = new Student();
			
			s0.setUserName("wayne");
			s1.setUserName("betty");
			s0.select();
			s1.select();
			
			st.add(s0);
			st.add(s1);
			

			for (Student s : st) {
				System.out.println(s.getUserName());
				System.out.println(s.getFirstName());
				System.out.println(s.getLastName());
			}
			c.insert();
*/			
//			Student s = new Student();
//			Student s1 = new Student();
			
//			s.setUserName("wayne");
//			s1.setUserName("betty");
//			s.select();
//			s1.select();
			
//			st.add(s);
			
//			c.update();
			
			
/*			Semester sem = new Semester();
			sem.setName("Summer - 2012");
			sem.setStartTime(new java.sql.Timestamp(System.currentTimeMillis()));
			sem.setEndTime(new java.sql.Timestamp(System.currentTimeMillis() + 360000L));
			c.setSemester(sem);

			
			System.out.println(c.getCourseName());
			System.out.println(c.getSemester().getName());
			System.out.println(c.getInstructor().getUserName());
			
			Student s = new Student();
//			Student s1 = new Student();
			
			s.setUserName("wayne");
//			s1.setUserName("betty");
			s.select();
//			s1.select();
			
			ArrayList<Student> st = c.getStudents();
			st.add(s);
//			st.add(s1);
			
			c.update();
*/			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
=======

package edu.asu.cse360.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class Course extends SQLEntity {

	private String courseName;
	Instructor instructor;
	Semester semester;
	ArrayList<Student> studentRoster = new ArrayList<Student>();
	ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
	
	public Course() throws ClassNotFoundException {
		super();
	}
	public Course(String name) throws ClassNotFoundException {
		super();
		courseName = name;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	
	public ArrayList<Student> getStudents() {
		return this.studentRoster;
	}
	
	public int insert() throws SQLException {
		StringBuffer query = new StringBuffer();
		if(this.getCourseName() == null || this.getCourseName().length() < 1) {
			throw new SQLException("Cannot persist a course with no name.");
		}
		// TODO insert quizzes
		for (Student s : this.studentRoster) {
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO `student_roster`(`course_name`, `instructor`, `student_name`) VALUES (");
			sb.append(quotes(this.getCourseName()));
			sb.append(", ");
			sb.append(quotes(this.getInstructor().getUserName()));
			sb.append(", ");
			sb.append(quotes(s.getUserName()));
			sb.append(")");
			super.insert(sb.toString());
		}
		query.append("INSERT INTO `course`(`course_name`, `instructor`, `semester`) VALUES (");
		query.append(quotes(this.getCourseName()));
		query.append(", ");
		query.append(quotes(this.getInstructor().getUserName()));
		query.append(", ");
		query.append(quotes(this.getSemester().getName()));
		query.append(")");
		
		return super.insert(query.toString());
	}

	public int update() throws SQLException {
		StringBuffer query = new StringBuffer();
		if(this.getCourseName() == null || this.getCourseName().length() < 1) {
			throw new SQLException("Cannot persist a course with no name.");
		}

		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM `student_roster` WHERE `course_name`=");
		sb.append(quotes(this.getCourseName()));
		sb.append(" AND `instructor`=");
		sb.append(quotes(this.getInstructor().getUserName()));
		super.delete(sb.toString());
				
		for (Student s : this.studentRoster) {
			sb = new StringBuffer();
			sb.append("INSERT INTO `student_roster`(`course_name`, `instructor`, `student_name`) VALUES (");
			sb.append(quotes(this.getCourseName()));
			sb.append(", ");
			sb.append(quotes(this.getInstructor().getUserName()));
			sb.append(", ");
			sb.append(quotes(s.getUserName()));
			sb.append(")");
			super.insert(sb.toString());
		}
		
		for (Quiz q : this.quizzes) {
			q.update();
		}
		query.append("UPDATE `course` SET `instructor`=");
		query.append(quotes(this.getInstructor().getUserName()));
		query.append(", `semester`=");
		query.append(quotes(this.getSemester().getName()));
		query.append(" WHERE `course_name`=");
		query.append(quotes(this.getCourseName()));
		query.append(" AND `instructor`=");
		query.append(quotes(this.getInstructor().getUserName()));
		
		System.out.println(query.toString());
		
		return super.update(query.toString());
	}

	public int delete() throws Exception {
		StringBuffer query = new StringBuffer();
		if(this.getCourseName() == null || this.getCourseName().length() < 1) {
			throw new SQLException("Cannot delete a course with no name.");
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM `student_roster` WHERE `course_name`=");
		sb.append(quotes(this.getCourseName()));
		sb.append(" AND `instructor`=");
		sb.append(quotes(this.getInstructor().getUserName()));
		super.delete(sb.toString());
		
		for (Quiz q : this.quizzes) {
			q.delete();
		}
		
		query.append("DELETE FROM `course` WHERE `instructor`=");
		query.append(quotes(this.getInstructor().getUserName()));
		query.append(" AND `semester`=");
		query.append(quotes(this.getSemester().getName()));
		query.append(" AND `course_name`=");
		query.append(quotes(this.getCourseName()));
		
		return super.delete(query.toString());
	}

	public boolean select() throws Exception {
		boolean success = false;
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT `course_name`, `instructor`, `semester` FROM `course` WHERE `course_name`= ");
		query.append(quotes(this.getCourseName()));
		query.append(" AND `instructor`= ");
		query.append(quotes(this.getInstructor().getUserName()));
	
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			Semester s = new Semester();
			s.setName(results.getString("semester"));
			s.select();
			this.setSemester(s);
			
			// get student roster
			this.studentRoster = getStudentRoster();
			success = true;;
		}
		
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
	    
		return success;
	}
	
	public ArrayList<Student> getStudentRoster() throws Exception {
		ArrayList<Student> students = new ArrayList<Student>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT U.`username`, U.`last`, U.`first` FROM `user` AS U JOIN `student_roster` AS S ON S.`student_name` = U.`username` WHERE U.`isInstructor`= 0 ");
		query.append("AND S.`instructor` = ");
		query.append(quotes(this.getInstructor().getUserName()));
		query.append(" AND S.`course_name` = ");
		query.append(quotes(this.getCourseName()));
		query.append(" order by U.`username` ASC");
		
		System.out.println(query.toString());
		
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			Student s = new Student();
			s.setUserName(results.getString("username"));
			s.setLastName(results.getString("last"));
			s.setFirstName(results.getString("first"));
			students.add(s);
		}
				
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
	    
		return students;
	}
	
	public static ArrayList<Course> getCoursesByInstructor(String instructor) throws Exception {
		ArrayList<Course> courses = new ArrayList<Course>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `course_name`, `instructor`, `semester` FROM `course` WHERE ");
		query.append("`instructor`=");
		query.append(quotes(instructor));
		
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			Instructor i = new Instructor();
			i.setUserName(instructor);
			Course c = new Course();
			c.setCourseName(results.getString("course_name"));
			if (i != null) {
				c.setInstructor(i);
			}
			String semester = results.getString("semester");
			if (semester != null) {
				Semester s = new Semester();
				s.setName(semester);
				s.select();
				c.setSemester(s);
			}
			c.studentRoster = c.getStudentRoster();
			
			c.quizzes = Quiz.getQuizzesByCourse(c.getCourseName());
			
			courses.add(c);
		}
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return courses;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			Course c = new Course();
			c.setCourseName("CSE-362");
			Instructor i = new Instructor();
			i.setUserName("janeka");
			i.select();
			c.setInstructor(i);
			Semester sem = new Semester();
			sem.setName("Summer - 2012");
			c.setSemester(sem);
			c.select();
			c.delete();
			
			

			
			
			
			
/*			
			ArrayList<Course> courses = Course.getCoursesByInstructor("janeka");
			
			for (Course c : courses) {
				System.out.println(c.getCourseName());
				System.out.println(c.getSemester().getName());
				System.out.println(c.getInstructor().getUserName());
				ArrayList<Student> st = c.getStudents();
				for (Student s : st) {
					System.out.println(s.getUserName());
					System.out.println(s.getFirstName());
					System.out.println(s.getLastName());
				}
			}
			
			
			Course c = new Course();
			c.setCourseName("CSE-362");
			Instructor i = new Instructor();
			i.setUserName("janeka");
			i.select();
			c.setInstructor(i);
			Semester sem = new Semester();
			sem.setName("Summer - 2012");
			sem.setStartTime(new java.sql.Timestamp(System.currentTimeMillis()));
			sem.setEndTime(new java.sql.Timestamp(System.currentTimeMillis() + 360000L));
			c.setSemester(sem);
			
//			c.select();
			
			System.out.println(c.getCourseName());
			System.out.println(c.getSemester().getName());
			System.out.println(c.getInstructor().getUserName());
			
			ArrayList<Student> st = c.getStudents();
			Student s0 = new Student();
			Student s1 = new Student();
			
			s0.setUserName("wayne");
			s1.setUserName("betty");
			s0.select();
			s1.select();
			
			st.add(s0);
			st.add(s1);
			

			for (Student s : st) {
				System.out.println(s.getUserName());
				System.out.println(s.getFirstName());
				System.out.println(s.getLastName());
			}
			c.insert();
*/			
//			Student s = new Student();
//			Student s1 = new Student();
			
//			s.setUserName("wayne");
//			s1.setUserName("betty");
//			s.select();
//			s1.select();
			
//			st.add(s);
			
//			c.update();
			
			
/*			Semester sem = new Semester();
			sem.setName("Summer - 2012");
			sem.setStartTime(new java.sql.Timestamp(System.currentTimeMillis()));
			sem.setEndTime(new java.sql.Timestamp(System.currentTimeMillis() + 360000L));
			c.setSemester(sem);

			
			System.out.println(c.getCourseName());
			System.out.println(c.getSemester().getName());
			System.out.println(c.getInstructor().getUserName());
			
			Student s = new Student();
//			Student s1 = new Student();
			
			s.setUserName("wayne");
//			s1.setUserName("betty");
			s.select();
//			s1.select();
			
			ArrayList<Student> st = c.getStudents();
			st.add(s);
//			st.add(s1);
			
			c.update();
*/			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
>>>>>>> 5cb929cc8ac103fb609cb934aa437661f1535d49
