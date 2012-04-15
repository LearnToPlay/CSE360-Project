package edu.asu.cse360.data;

public enum Answers {
	A {
	    public String toString() {
	        return "A";
	    }
	},
	 
	B {
	    public String toString() {
	        return "B";
	    }
	},
	
	C {
	    public String toString() {
	        return "C";
	    }
	},
	
	D {
	    public String toString() {
	        return "D";
	    }
	};
	
	public static Answers getEnum(String letter) throws Exception {
		if (letter == null || letter.length() < 1) {
			throw new Exception(letter +"is not either 'A' or 'B' or 'C' or 'D'");
		}
		
		char c = Character.toUpperCase(letter.charAt(0));
		switch (c) {
		case 'A':
			return A;
		case 'B':
			return B;
		case 'C':
			return C;
		case 'D':
			return D;
		default:
			throw new Exception(c +"is not either 'A' or 'B' or 'C' or 'D'");
		}
	}
}