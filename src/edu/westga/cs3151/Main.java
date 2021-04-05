package edu.westga.cs3151;

import edu.westga.cs3151.controller.DemoController;

/**
 * Class Main - entry point into the application
 * 
 * @author CS3151
 * @version Spring 2021
 */
public class Main {
	/**
	 * Instantiates and runs a demo controller
	 * 
	 * @param args not used
	 */
	public static void main(String[] args)  {
		DemoController controller = new DemoController();
		controller.run();		
	}
}
