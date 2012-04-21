package edu.asu.cse360.model;

public class Model {

	protected boolean persisted;
	
	public Model()
	{
		persisted = false;
	}
	
	
	public void Persist()
	{
		//TODO: Is this method for Warren to add SQL stuff to?...
	}
	
/*** Getter Setter ***/
	
	public void setPersisted(boolean persisted)
	{
		this.persisted = persisted;
	}
	
	public boolean isPersisted()
	{
		return persisted;
	}
	
}
