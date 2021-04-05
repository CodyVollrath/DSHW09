package edu.westga.cs3151.util;

/**
 * Enum BinaryOperator - arithmetic, binary operators
 * 
 * @author CS3151
 * @version Spring 2021
 */
public enum BinaryOperator {
	ADDITION("+", (operand1, operand2) -> operand1 + operand2), 
	SUBTRACTION("-", (operand1, operand2) -> operand1 - operand2), 
	MULTIPLICATION("*", (operand1, operand2) -> operand1 * operand2), 
	DIVISION("/", (operand1, operand2) -> operand1 / operand2);
	
	private final String symbol;
	private Operation operation;
	
	BinaryOperator(String symbol, Operation operation) {
		this.symbol = symbol;
		this.operation = operation;
	}
	
	/**
	 * Returns the symbol associated with this operator
	 * 
	 * @pre none
	 * @post none
	 * @return the operation symbol
	 */
	public String getSymbol() {
		return this.symbol;
	}
	
	/**
	 * Executes this operation for the given operands
	 * 
	 * @pre none
	 * @post none
	 * @param operand1 first operand
	 * @param operand2 second operand
	 * @return result of the operation
	 */
	public double execute(double operand1, double operand2) {
		return this.operation.execute(operand1, operand2);
	}
	
	/**
	 * Gets the operator associated with the specified symbol
	 * 
	 * @pre symbol.equals("+") OR symbol.equals("-") OR symbol.equals("*") OR symbol.equals("/")
	 * @post none
	 * @param symbol the symbol of the wanted operator
	 * @return the operator with the specified symbol
	 */
	public static BinaryOperator getOperator(String symbol) {
		for (BinaryOperator operator : BinaryOperator.values()) {
			if (operator.getSymbol().equals(symbol)) {
				return operator;
			}
		}
		return null;
	}
}
