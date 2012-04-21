package edu.asu.cse360.control;

import edu.asu.cse360.model.Model;
import edu.asu.cse360.view.View;

public class Controller {

	protected Model MODEL;
	protected View VIEW;
	
	public Controller()
	{}
	
	public Controller (Model model, View view)
	{
		MODEL = model;
		VIEW = view;
	}
	
	public View getView()
	{
		return VIEW;
	}
	
}
