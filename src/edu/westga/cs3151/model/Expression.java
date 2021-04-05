package edu.westga.cs3151.model;

import java.util.Iterator;
import java.util.Stack;

import edu.westga.cs3151.util.Messages;
import edu.westga.cs3151.util.Parser;
import edu.westga.cs3151.util.Term;

import java.util.ArrayList;

/**
 * Class Expression - an arithmetic expression
 *
 * @author CS3151
 * @version Spring 2021
 */
public class Expression {
	private BinaryTree<Term> expression;

	/**
	 * Instantiates an expression representing the specified infix expression
	 * 
	 * @pre infix is an arithmetic expression in infix notation with nonnegative
	 *      numbers as operands and with binary operators specified by
	 *      BinaryOperator only; each binary operation is enclosed in parenthesis
	 * @post this expression represent the specified infix expression
	 * @param infix string representation of the infix expression
	 * @throws InvalidArgument Expression if the given expression does not meet the
	 *                         preconditions
	 */
	public Expression(String infix) {
		ArrayList<String> infixParts = Parser.parse(infix);
		Stack<BinaryNode<Term>> termStack = new Stack<BinaryNode<Term>>();
		for (String part : infixParts) {
			if (part.equals(")")) {
				if (termStack.size() < 3) {
					throw new IllegalArgumentException(Messages.INVALID_INFIX_EXPRESSION);
				}
				BinaryNode<Term> operand2 = termStack.pop();
				BinaryNode<Term> operator = termStack.pop();
				BinaryNode<Term> operand1 = termStack.pop();
				operator.setLeftChild(operand1);
				operator.setRightChild(operand2);
				termStack.push(operator);
			} else if (!part.equals("(")) {
				BinaryNode<Term> node = new BinaryNode<Term>();
				node.setValue(new Term(part));
				termStack.push(node);
			}
		}
		if (termStack.size() != 1) {
			throw new IllegalArgumentException(Messages.INVALID_INFIX_EXPRESSION);
		} else {
			this.expression = new BinaryTree<Term>();
			this.expression.setRoot(termStack.pop());
		}
	}

	/**
	 * Returns the infix expression with parentheses
	 * 
	 * @return String representation of the infix notation of this expression
	 */
	public String getExpression() {
		return this.getInfixExpression(this.expression.getRoot());
	}

	private String getInfixExpression(BinaryNode<Term> node) {
		String infixExpression;
		Term term = node.getValue();
		if (term.isOperand()) {
			infixExpression = term.toString();
		} else {
			infixExpression = "(" + this.getInfixExpression(node.getLeftChild()) + " " + term.toString() + " "
					+ this.getInfixExpression(node.getRightChild()) + ")";
		}
		return infixExpression;
	}

	/**
	 * Returns the infix notation of the expression without parenthesis
	 * 
	 * @return String representation of the infix notation of this expression
	 */
	public String getInfixNotation() {
		Iterator<Term> it = this.expression.inOrderIterator();
		String strExpression = "";
		while (it.hasNext()) {
			strExpression += it.next().toString() + " ";
		}
		return strExpression;
	}
	
	/**
	 * Returns the Polish (prefix) notation of this expression
	 * 
	 * @return String representation of the prefix notation of this expression
	 */
	public String getPolishNotation() {
		String expression = "";
		var iterator = this.expression.preOrderIterator();
		while (iterator.hasNext()) {
			expression += iterator.next() + " ";
		}
		return expression;
	}

	/**
	 * Returns the reversed Polish (postfix) notation of the expression
	 * 
	 * @return String representation of the postfix notation of this expression
	 */
	public String getReversedPolishNotation() {
		String expression = "";
		var iterator = this.expression.postOrderIterator();
		while (iterator.hasNext()) {
			expression += iterator.next() + " ";
		}
		return expression;
	}

	/**
	 * Evaluates this expression
	 * 
	 * @pre none
	 * @post none
	 * @return value of this expression
	 */
	public double evaluate() {
		return this.evaluate(this.expression.getRoot());
	}

	/**
	 * Evaluates the expression represented by the tree rooted at the specified node
	 * 
	 * @param node node
	 * @return value of the expression rooted at node
	 */
	private double evaluate(BinaryNode<Term> node) {
		Term term = node.getValue();
		if (term.isOperand()) {
			return term.evaluate();
		} else {
			double operand1 = this.evaluate(node.getLeftChild());
			double operand2 = this.evaluate(node.getRightChild());
			return term.evaluate(operand1, operand2);
		}
	}
}