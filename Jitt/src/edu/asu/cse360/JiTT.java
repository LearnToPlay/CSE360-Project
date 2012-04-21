
package edu.asu.cse360;

import javax.swing.JFrame;
import javax.swing.UIManager;
import edu.asu.cse360.view.NavigatorPanel;

public class JiTT
{
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.err.println("Couldn't use system look and feel.");
        }
        
        //Create and set up the window.
        JFrame frame = new JFrame("JiTT Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        NavigatorPanel demo = new NavigatorPanel();
        demo.addComponentToPane(frame.getContentPane());
        
        //Display the window.
        frame.pack();
        frame.setSize(700,400);
        frame.setVisible(true);
    }
	
	public static void main(String[] args)
	{
        //Schedule a job for the event dispatch thread:
	    //creating and showing this application's GUI.
	    javax.swing.SwingUtilities.invokeLater(
		    		new Runnable()
				    {
			            public void run()
			            {
			                createAndShowGUI();
			            }
			        }
		    	);
	}
}