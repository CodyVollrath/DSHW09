package edu.westga.cs3151.util;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * class Parser
 * 
 * @author CS3151
 * @version Spring 2021
 */
public class Parser {

	/**
	 * Parses the specified expression; extracts the operators, operands, and
	 * opening and closing parenthesis
	 * 
	 * @pre expression consists of the binary operators specified by BinaryOperator,
	 *      of nonnegative numbers, and of opening and closing parentheses
	 * @post none
	 * @param expression the arithmetic expression
	 * @return list of operands, operators, opening and closing parenthesis in the
	 *         order in which they occur in the specified infix expression; returns
	 *         null if an invalid character is found
	 * @throws IllegalArgumentException if expression contains an invalid character
	 */
	public static ArrayList<String> parse(String expression) {
		String spacedExpression = expression.replaceAll("\\(", " \\( ");
		spacedExpression = spacedExpression.replaceAll("\\)", " \\) ");
		for (BinaryOperator operator : BinaryOperator.values()) {
			String symbol = operator.getSymbol();
			spacedExpression = spacedExpression.replaceAll("\\" + symbol, " \\" + symbol + " ");
		}

		ArrayList<String> infixParts = new ArrayList<String>();
		try (Scanner scanner = new Scanner(spacedExpression)) {
			while (scanner.hasNext()) {
				if (scanner.hasNext("\\(|\\)|\\*|\\+|-|/")) {
					String token = scanner.next("\\(|\\)|\\*|\\+|-|/");
					infixParts.add(token);
				} else if (scanner.hasNextDouble()) {
					double value = scanner.nextDouble();
					infixParts.add(Double.toString(value));
				} else {
					throw new IllegalArgumentException(Messages.INVALID_EXPRESSION);
				}
			}
		}
		return infixParts;
	}
}
