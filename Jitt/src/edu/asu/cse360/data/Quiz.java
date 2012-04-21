package edu.asu.cse360.data;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz extends SQLEntity {

	private String quizName, courseName;
	private static final String instructions = "Read the following questions and click on a response. " +
			"You have 20 minutes to complete the quiz.";
	private int numberOfQuestions;
	private java.sql.Timestamp open, close;
	private ArrayList<QuizContent> content = new ArrayList<QuizContent>();
	
	public Quiz() {
		
	}
	
	public void addContent(QuizContent qc) {
		content.add(qc);
		numberOfQuestions++;
	}
	
	public void deleteContent(QuizContent qc) {
		content.remove(qc);
		numberOfQuestions--;
	}
	
	public void deleteContent(int idx) {
		content.remove(idx);
		numberOfQuestions--;
	}
	
/*** Getter and Setters ***/

	public void setQuizName(String quizName) {
		this.quizName = quizName; //This might be extended to include the course name and such.
	}

	public String getQuizName() {
		return quizName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseName() {
		if (courseName == null) {
			return "";
		} else {
			return courseName;
		}
	}

	public String getInstructions() {
		return Quiz.instructions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setOpen(java.sql.Timestamp open) {
		this.open = open;
	}

	public Timestamp getOpen() {
		return open;
	}

	public void setClose(java.sql.Timestamp close) {
		this.close = close;
	}

	public Timestamp getClose() {
		return close;
	}

	/** setContent and getContent may need to be revised to
	 ** getting and setting a specific index of content.	
	 ** @param content
	 */
	public void setContent(ArrayList<QuizContent> content) {
		this.content = content;
	}

	public ArrayList<QuizContent> getContent() {
		return content;
	}
	
	public int insert() throws SQLException {
		StringBuffer query = new StringBuffer();
		if(quizName == null || quizName.length() < 1) {
			throw new SQLException("Cannot persist a quiz with no name.");
		}
		
		Collections.sort(content, new QuizContentComparator());
		
		for (QuizContent qc : content) {
			qc.insert();
		}
		query.append("INSERT INTO `quiz`(`quiz_name`, `course`, `start`, `end`) VALUES (");
		query.append(quotes(getQuizName()));
		query.append(", ");
		query.append(quotes(getCourseName()));
		query.append(", '");
		query.append(getOpen());
		query.append("', '");
		query.append(getClose());
		query.append("')");

		
		return super.insert(query.toString());
	}

	public int update() throws SQLException {
		StringBuffer query = new StringBuffer();
		if(quizName == null || quizName.length() < 1) {
			throw new SQLException("Cannot persist a quiz with no name.");
		}
		
		Collections.sort(content, new QuizContentComparator());
		
		for (QuizContent qc : content) {
			qc.update();
		}
		query.append("UPDATE `quiz` SET `course`=");
		query.append(quotes(getCourseName()));
		query.append(", `start`='");
		query.append(getOpen());
		query.append("', `end`='");
		query.append(getClose());
		query.append("' WHERE `quiz_name`=");
		query.append(quotes(this.getQuizName()));
		
		return super.update(query.toString());
	}

	public int delete() throws SQLException {
		StringBuffer query = new StringBuffer();
		if(quizName == null || quizName.length() < 1) {
			throw new SQLException("Cannot persist a quiz with no name.");
		}
		
		Collections.sort(content, new QuizContentComparator());
		
		for (QuizContent qc : content) {
			qc.delete();
		}
		query.append("DELETE FROM `quiz` WHERE `quiz_name`=");
		query.append(quotes(this.getQuizName()));
		
		return super.delete(query.toString());
	}

	
	public boolean select() throws Exception {
		boolean success = false;
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT `quiz_name`, `course`, `start`, `end` FROM `quiz` WHERE `quiz_name`= ");
		query.append(quotes(this.getQuizName()));
		if (this.getCourseName() != null && this.getCourseName().length() > 0) {
			query.append(" AND `course`= ");
			query.append(quotes(this.getCourseName()));
		}
	
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			this.setCourseName(results.getString("course"));
			this.setOpen(results.getTimestamp("start"));
			this.setClose(results.getTimestamp("end"));
			success = true;;
		}
		
	    if(statement != null) {
	    	statement.close();
	    }
	    
		content = QuizContent.getQuizContents(this.getQuizName());
		
	    SQLEntity.returnConnection(con);
						
		return success;
	}
	
	public static ArrayList<Quiz> getQuizzesByCourse(String course) throws Exception {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `quiz_name`, `course`, `start`, `end` FROM `quiz` WHERE `course`= ");
		query.append(quotes(course));
		
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			Quiz q = new Quiz();
			q.setQuizName(results.getString("quiz_name"));
			q.setCourseName(results.getString("course"));
			q.setOpen(results.getTimestamp("start"));
			q.setClose(results.getTimestamp("end"));
			q.setContent(QuizContent.getQuizContents(q.getQuizName()));
			quizzes.add(q);
		}
				
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return quizzes;
	}
	
	public static void main(String [] args) {
		try {
			
			ArrayList<Quiz> qc = Quiz.getQuizzesByCourse("Fall - 2012");
			
			for (Quiz q : qc) {
				System.out.println(q.getQuizName());
				System.out.println(q.getCourseName());
				System.out.println(q.getOpen());
				System.out.println(q.getClose());
				
				List<QuizContent> cont = q.getContent();
				
				for (QuizContent a : cont) {
					System.out.println(a.getQuizName());
					System.out.println(a.getQuestionNumber());
					System.out.println(a.getQuestion());
					System.out.println(a.getAnswerA());
					System.out.println(a.getAnswerB());
					System.out.println(a.getAnswerC());
					System.out.println(a.getAnswerD());
					System.out.println(a.getCorrectAnswer());
				}
				
				
			}
//			Quiz q = new Quiz();
			
//			q.setQuizName("Quiz 2");
//			q.setCourseName("Fall - 2012");
//			q.setOpen(new java.sql.Timestamp(System.currentTimeMillis()));
//			q.setClose(new java.sql.Timestamp(System.currentTimeMillis() + 500000L));
//			q.insert();
//			q.select();
//			q.delete();
/*			
			System.out.println(q.getQuizName());
			System.out.println(q.getCourseName());
			System.out.println(q.getOpen());
			System.out.println(q.getClose());

			List<QuizContent> cont = q.getContent();
			
			for (QuizContent a : cont) {
				System.out.println(a.getQuizName());
				System.out.println(a.getQuestionNumber());
				System.out.println(a.getQuestion());
				System.out.println(a.getAnswerA());
				System.out.println(a.getAnswerB());
				System.out.println(a.getAnswerC());
				System.out.println(a.getAnswerD());
				System.out.println(a.getCorrectAnswer());
			}
			q.setOpen(new java.sql.Timestamp(System.currentTimeMillis()));
			q.setClose(new java.sql.Timestamp(System.currentTimeMillis() + 500000L));

			QuizContent qc = new QuizContent();
			
			qc.setQuizName("Quiz 2");
			qc.setQuestionNumber(1);
			
			qc.setQuestion("Why are cows always happy?");
			qc.setAnswerA("Crazy?");
			qc.setAnswerB("Stupid?");
			qc.setAnswerC("Republican?");
			qc.setAnswerD("Democrat?");
			qc.setCorrectAnswer(Answers.getEnum("A"));
			
			QuizContent qa = new QuizContent();
			qa.setQuizName("Quiz 2");
			qa.setQuestionNumber(2);
			
			qa.setQuestion("Why are cows always happy?");
			qa.setAnswerA("Crazy?");
			qa.setAnswerB("Stupid?");
			qa.setAnswerC("Republican?");
			qa.setAnswerD("Democrat?");
			qa.setCorrectAnswer(Answers.getEnum("A"));
			
			q.addContent(qc);
			q.addContent(qa);
			
			q.insert();
*/			
			
/*			
			q.select();
			
			q.setClose(new java.sql.Timestamp(System.currentTimeMillis() + 500000L));
			System.out.println(q.getQuizName());
			System.out.println(q.getCourseName());
			System.out.println(q.getOpen());
			System.out.println(q.getClose());
			q.update();
//			q.insert();
//			q.delete();
			q.setQuestion("Why are cows always happy?");
			q.setAnswerA("Crazy?");
			q.setAnswerB("Stupid?");
			q.setAnswerC("Republican?");
			q.setAnswerD("Democrat?");
			q.setCorrectAnswer(Answers.getEnum("A"));
	
//			q.select();
			q.insert();
			System.out.println(q.getQuizName());
			System.out.println(q.getQuestionNumber());
			System.out.println(q.getQuestion());
			System.out.println(q.getAnswerA());
			System.out.println(q.getAnswerB());
			System.out.println(q.getAnswerC());
			System.out.println(q.getAnswerD());
			System.out.println(q.getCorrectAnswer());
			
//			q.setCorrectAnswer(Answers.getEnum("B"));
			
						
//			u.setLastName("Smith");
//			q.update();
			
//			System.out.println(u.delete());
			
			q.commit();
			
			ArrayList<QuizContent> c = QuizContent.getQuizContents("Quiz 1");
			
			for (QuizContent q : c) {
				System.out.println(q.getQuizName());
				System.out.println(q.getQuestionNumber());
				System.out.println(q.getQuestion());
				System.out.println(q.getAnswerA());
				System.out.println(q.getAnswerB());
				System.out.println(q.getAnswerC());
				System.out.println(q.getAnswerD());
				System.out.println(q.getCorrectAnswer());
				System.out.println("\n");
			}
*/			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
