package edu.asu.cse360.data;

import java.util.Comparator;

public class QuizContentComparator implements Comparator<QuizContent> {	
	    @Override
	    public int compare(QuizContent o1, QuizContent o2) {
	        return o1.getQuestionNumber() - o2.getQuestionNumber();
	    }
}
