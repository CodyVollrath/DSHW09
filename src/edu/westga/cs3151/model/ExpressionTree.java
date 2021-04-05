package edu.westga.cs3151.model;

import java.util.ArrayList;
import java.util.Stack;

import edu.westga.cs3151.util.Messages;
import edu.westga.cs3151.util.Parser;
import edu.westga.cs3151.util.Term;

/**
 * Class ExpressionTree
 * 
 * @author CS3151
 * @version Spring 2021
 */
public class ExpressionTree {
	private Term term;
	private ExpressionTree leftExpTree;
	private ExpressionTree rightExpTree;

	/**
	 * Instantiates an expression tree of value 0
	 * 
	 * @pre none
	 * @post evaluate() == 0
	 */
	public ExpressionTree() {
		this.term = new Term(0);
		this.leftExpTree = null;
		this.rightExpTree = null;
	}

	/**
	 * Instantiates an expression tree representing the specified infix expression
	 * 
	 * @pre infix is an arithmetic expression in infix notation with nonnegative
	 *      numbers as operands and with the binary operators specified by
	 *      BinaryOperator only; each binary operation is enclosed in parentheses
	 * @post this expression represent the specified infix expression
	 * @param infix string representation of the infix expression
	 * @throws InvalidArgument Expression if the given expression does not meet the
	 *                         preconditions
	 */
	public ExpressionTree(String infix) {
		ArrayList<String> infixParts = Parser.parse(infix);
		Stack<ExpressionTree> expressionStack = new Stack<ExpressionTree>();
		for (String part : infixParts) {
			if (part.equals(")")) {
				if (expressionStack.size() < 3) {
					throw new IllegalArgumentException(Messages.INVALID_INFIX_EXPRESSION);
				}
				ExpressionTree operand2 = expressionStack.pop();
				ExpressionTree operator = expressionStack.pop();
				ExpressionTree operand1 = expressionStack.pop();
				operator.leftExpTree = operand1;
				operator.rightExpTree = operand2;
				expressionStack.push(operator);
			} else if (!part.equals("(")) {
				ExpressionTree expressionTree = new ExpressionTree();
				expressionTree.term = new Term(part);
				expressionStack.push(expressionTree);
			}
		}
		if (expressionStack.size() != 1) {
			throw new IllegalArgumentException(Messages.INVALID_INFIX_EXPRESSION);
		} else {
			this.copy(expressionStack.pop());
		}
	}

	/**
	 * Returns the infix expression with parentheses
	 * 
	 * @return String representation of the infix notation of this expression
	 */
	public String getExpression() {
		return getInfixExpression(this);
	}

	private static String getInfixExpression(ExpressionTree expTree) {
		if (expTree.term.isOperand()) {
			return expTree.term.toString();
		}
		return "(" + getInfixExpression(expTree.leftExpTree) + " " + expTree.term.getOperator() + " "
				+ getInfixExpression(expTree.rightExpTree) + ")";
	}

	/**
	 * Returns the Polish (prefix) notation of the expression
	 * 
	 * @return String representation of the prefix notation of this expression
	 */
	public String getPolishNotation() {
		return getPrefixNotation(this);
	}

	private static String getPrefixNotation(ExpressionTree expTree) {
		if (expTree.term.isOperand()) {
			return expTree.term.toString();
		}
		return expTree.term.getOperator() + " " + getPrefixNotation(expTree.leftExpTree) + " "
				+ getPrefixNotation(expTree.rightExpTree);
	}

	/**
	 * Returns the infix notation of the expression without parenthesis
	 * 
	 * @return String representation of the infix notation of this expression
	 */
	public String getInfixNotation() {
		return getInfixNotation(this);
	}

	private static String getInfixNotation(ExpressionTree expTree) {
		if (expTree.term.isOperand()) {
			return expTree.term.toString();
		}
		return getInfixNotation(expTree.leftExpTree) + " " + expTree.term.getOperator() + " "
				+ getInfixNotation(expTree.rightExpTree);
	}

	/**
	 * Returns the reversed Polish (postfix) notation of the expression
	 * 
	 * @return String representation of the postfix notation of this expression
	 */
	public String getReversedPolishNotation() {
		return getPostfixNotation(this);
	}

	private static String getPostfixNotation(ExpressionTree expTree) {
		if (expTree.term.isOperand()) {
			return expTree.term.toString();
		}
		return getPostfixNotation(expTree.leftExpTree) + " " + getPostfixNotation(expTree.rightExpTree) + " "
				+ expTree.term.getOperator();
	}

	/**
	 * Evaluates this expression
	 * 
	 * @pre none
	 * @post none
	 * @return value of this expression
	 */
	public double evaluate() {
		if (this.term.isOperand()) {
			return this.term.evaluate();
		}
		return this.term.evaluate(this.leftExpTree.evaluate(), this.rightExpTree.evaluate());
	}

	private void copy(ExpressionTree tree) {
		this.term = tree.term;
		this.leftExpTree = tree.leftExpTree;
		this.rightExpTree = tree.rightExpTree;
	}
}
