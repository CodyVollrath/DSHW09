package edu.westga.cs3151.util;

import java.text.NumberFormat;

/**
 * Class Term - an operand or operator
 *
 * @author CS3151
 * @version Spring 2021
 */
public class Term {

	private BinaryOperator operator;
	private double operand;
	private boolean isOperand;

	/**
	 * Instantiates an new term that is an operand
	 * 
	 * @pre none
	 * @post isOperand() && getOperand() == operand
	 * @param operand the value of the operand
	 */
	public Term(double operand) {
		this.isOperand = true;
		this.operand = operand;
	}

	/**
	 * Instantiates a new term
	 * 
	 * @pre Operator.getOperator(term) != null OR Double.parseDouble(term)
	 * @post (Operator.getOperator(term) != null IMPLIES !isOperand()) AND
	 *       (Double.parseDouble(term) IMPLIES isOperand())
	 * @param term the term
	 * @throws IllegalArgumentException if the operator is neither a known operator
	 *                                  nor a number
	 */
	public Term(String term) {
		this.operator = BinaryOperator.getOperator(term);
		if (this.operator != null) {
			this.isOperand = false;
		} else {
			try {
				this.operand = Double.parseDouble(term);
				this.isOperand = true;
			} catch (NullPointerException | NumberFormatException ex) {
				throw new IllegalArgumentException("Unknown operator");
			}
		} 
		
	}

	/**
	 * Checks if this term is an operand
	 * 
	 * @pre none
	 * @post none
	 * @return true if this term is an operand
	 */
	public boolean isOperand() {
		return this.isOperand;
	}

	/**
	 * Checks if this term is an operator
	 * 
	 * @pre none
	 * @post none
	 * @return true if this term is an operator
	 */
	public boolean isOperator() {
		return !this.isOperand;
	}
	
	/**
	 * Gets the operator symbol if this term is an operator
	 * 
	 * @pre isOperator()
	 * @post none
	 * @return the operator symbol representing this term
	 * @throws IllegalArgumentException if this term is not an operator
	 */
	public String getOperator() {
		if (this.isOperand) {
			throw new IllegalArgumentException("this term is not an operator");
		}
		return this.operator.getSymbol();
	}

	/**
	 * Gets the value of the operand
	 * 
	 * @pre isOperand()
	 * @post none
	 * @return returns the operand
	 * @throws IllegalArgumentException if this term is not an operand
	 */
	public double evaluate() {
		if (!this.isOperand) {
			throw new IllegalArgumentException("operator cannot be evaluated without operands");
		}
		return this.operand;
	}

	/**
	 * Evaluates the operation using this operator and the specified operands
	 * 
	 * @pre isOperator()
	 * @post none
	 * @param operand1 the first operand
	 * @param operand2 the second operand
	 * @return the value of this operation
	 * @throws IllegalArgumentException if this term is not an operator
	 */
	public double evaluate(double operand1, double operand2) {
		if (this.isOperand) {
			throw new IllegalArgumentException("this term is an operand, operands should not be specified");
		}
		return this.operator.execute(operand1, operand2);
	}

	@Override
	public String toString() {
		if (this.isOperand) {
			return NumberFormat.getInstance().format(this.operand);
		} else {
			return this.operator.getSymbol();
		}
	}
}
