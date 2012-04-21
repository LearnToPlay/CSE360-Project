package edu.asu.cse360.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a student's response to a single question on a specific quiz
 *  for a specific course.
 * @author W
 *
 */

public class QuizResponse extends SQLEntity {
	
	private String quizName;
	private String courseName;
	private String studentName;
	private Answers response;
	private int questionNum;
	
	public QuizResponse() {
	
	}
	
	/**
	 * This method inserts a student's response to a quiz question to the database
	 * @return int indicating how many rows were inserted to the quiz_response table
	 * @throws SQLException
	 */
	public int insert() throws SQLException {
		StringBuffer query =new StringBuffer();

		query.append("INSERT INTO `quiz_response`(`quiz`, `course`, `username`, `question_num`, `response`) VALUES (");
		query.append(quotes(getQuizName()));
		query.append(",");
		query.append(quotes(this.getCourseName()));
		query.append(",");
		query.append(quotes(this.getStudentName()));
		query.append(",");
		query.append(getQuestionNum());
		query.append(",");
		query.append(quotes(this.getResponse().toString()));
		query.append(")");
		
		return super.insert(query.toString());
	}

	/**
	 * This method updates any changes to a student's response to a quiz question.
	 * @return int number of student response rows updated in the database.
	 * @throws SQLException
	 */
	public int update() throws SQLException {
		StringBuffer query =new StringBuffer();
		query.append("UPDATE `quiz_response` SET `quiz`=");
		query.append(quotes(getQuizName()));
		query.append(",`course`=");
		query.append(quotes(getCourseName()));
		query.append(",`username`=");
		query.append(quotes(getStudentName()));
		query.append(",`question_num`=");
		query.append(getQuestionNum());
		query.append(",`response`=");
		query.append(quotes(getResponse().toString()));
		query.append(" WHERE `quiz`= ");
		query.append(quotes(getQuizName()));
		query.append(" AND `course`=");
		query.append(quotes(getCourseName()));
		query.append(" AND `username`=");
		query.append(quotes(getStudentName()));
		query.append(" AND `question_num`=");
		query.append(getQuestionNum());
		
		return super.update(query.toString());
	}

	/** 
	 * This method deletes a student's response to a quiz question.
	 * @return int number of student response rows deleted from the database.
	 * @throws SQLException
	 */
	public int delete() throws SQLException {
		StringBuffer query = new StringBuffer();
		
		query.append("DELETE FROM `quiz_response` WHERE `quiz`= ");
		query.append(quotes(getQuizName()));
		query.append(" AND `course`=");
		query.append(quotes(getCourseName()));
		query.append(" AND `username`=");
		query.append(quotes(getStudentName()));
		query.append(" AND `question_num`=");
		query.append(getQuestionNum());
		
		return super.delete(query.toString());
	}

	/**
	 * This method retrieves a student's response to a quiz question from the database.
	 * @return boolean indicates true if a matching response was found in the database.
	 * @throws Exception
	 */
	public boolean select() throws Exception {
		boolean success = false;
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT `quiz`, `course`, `username`, `question_num`, `response` FROM `quiz_response` WHERE `quiz`= ");
		query.append(quotes(getQuizName()));
		query.append(" AND `course`=");
		query.append(quotes(getCourseName()));
		query.append(" AND `username`=");
		query.append(quotes(getStudentName()));
		query.append(" AND `question_num`=");
		query.append(getQuestionNum());
	
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			this.setResponse(Answers.getEnum(results.getString("response")));
			success = true;;
		}
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
						
		return success;
	}
	
	/**
	 * This method retrieves all a student's responses for a quiz.
	 * @param quiz
	 * @param course
	 * @param user
	 * @return ArrayList<QuizResonse> of student responses to a quiz.
	 * @throws Exception
	 */
	public static ArrayList<QuizResponse> getQuizResponses(String quiz, String course, String user) throws Exception {
		ArrayList<QuizResponse> qResponses = new ArrayList<QuizResponse>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `quiz`, `course`, `username`, `question_num`, `response` FROM `quiz_response` WHERE `quiz`= ");
		query.append(quotes(quiz));
		query.append(" AND `course`=");
		query.append(quotes(course));
		query.append(" AND `username`=");
		query.append(quotes(user));
	
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			QuizResponse qr = new QuizResponse();
			qr.setQuizName(results.getString("quiz"));
			qr.setCourseName(results.getString("course"));
			qr.setStudentName(results.getString("username"));
			qr.setQuestionNum(results.getInt("question_num"));
			qr.setResponse(Answers.getEnum(results.getString("response")));
			qResponses.add(qr);
		}
		
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return qResponses;
		
	}
	
	/**
	 * This method retrieves all the responses for a quiz for all students.
	 * @param quiz
	 * @param course
	 * @param questionNum
	 * @return ArrayList<QuizResonse> of responses to a quiz.
	 * @throws Exception
	 */
	public static ArrayList<QuizResponse> getQuizQuestionResponses(String quiz, String course, int questionNum) throws Exception {
		ArrayList<QuizResponse> qResponses = new ArrayList<QuizResponse>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `quiz`, `course`, `username`, `question_num`, `response` FROM `quiz_response` WHERE `quiz`= ");
		query.append(quotes(quiz));
		query.append(" AND `course`=");
		query.append(quotes(course));
		query.append(" AND `question_num`=");
		query.append(questionNum);
	
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			QuizResponse qr = new QuizResponse();
			qr.setQuizName(results.getString("quiz"));
			qr.setCourseName(results.getString("course"));
			qr.setStudentName(results.getString("username"));
			qr.setQuestionNum(results.getInt("question_num"));
			qr.setResponse(Answers.getEnum(results.getString("response")));
			qResponses.add(qr);
		}
		
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return qResponses;
		
	}
	
	public String getQuizName() {
		return quizName;
	}



	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}



	public String getCourseName() {
		return courseName;
	}



	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}



	public String getStudentName() {
		return studentName;
	}



	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public Answers getResponse() {
		return response;
	}



	public void setResponse(Answers response) {
		this.response = response;
	}



	public int getQuestionNum() {
		return questionNum;
	}



	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}
	
	public String toString() {
		return this.getQuizName() + "\n" + this.getCourseName() + "\n"
				+ this.getStudentName() + "\n" + this.getQuestionNum() + "\n"
				+ this.getResponse();
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
/*			
			// INSERT test case
			QuizResponse qr = new QuizResponse();
			qr.setQuizName("Quiz 1");
			qr.setCourseName("CSE-360");
			qr.setStudentName("betty");
			qr.setQuestionNum(3);
			qr.setResponse(Answers.B);
			qr.insert();
			System.out.println(qr.toString());
			// end of INSERT
			
			//SELECT test case
			qr.setResponse(null);
			qr.select();
			System.out.println(qr.toString());
			// end of SELECT
			
			//UPDATE test case
			qr.setResponse(Answers.C);
			qr.update();
			// end of UPDATE
			
			//DELETE test case
			qr.delete();
			// end of DELETE test case
			
			// getQuizResponses test case
			List <QuizResponse> r = QuizResponse.getQuizResponses("Quiz 1", "CSE-360", "betty");
			for (QuizResponse q : r) {
				System.out.println(q);
			}
			// end of getQuizResponses
*/
			// getQuizQuestionResponses test case
			List <QuizResponse> r = QuizResponse.getQuizQuestionResponses("Quiz 1", "CSE-360", 1);
			for (QuizResponse q : r) {
				System.out.println(q);
			}
			// end of getQuizResponses
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
