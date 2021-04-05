package edu.westga.cs3151.util;

/**
 * Interface Operation - a functional interface 
 * 
 * @author CS3151
 * @version Spring 2021
 */
public interface Operation {
	
	/**
	 * Executes this operation for the given operands
	 * 
	 * @param operand1 first operand
	 * @param operand2 second operand
	 * @return result of the operation
	 */
	double execute(double operand1, double operand2);
}
