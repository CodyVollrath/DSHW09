package edu.westga.cs3151.controller;

import edu.westga.cs3151.model.BinaryNode;
import edu.westga.cs3151.model.BinaryTree;
import edu.westga.cs3151.model.Expression;
import edu.westga.cs3151.model.ExpressionTree;

/**
 * Class DemoController - demonstrates the use of class Expression
 * 
 * @author CS3151
 * @version Spring 2021
 */
public class DemoController {

	/**
	 * Demonstrates the tree classes in the model package
	 */
	public void run() {
		demoBinaryTree();
		demoExpression();
		demoExpressionTree();
	}
	
	/**
	 * Demonstrates class BinaryTree
	 */
	public static void demoBinaryTree() {
		System.out.println("-------------------------------\nDemo of Class Binary Tree");
		BinaryNode<String> root = new BinaryNode<String>("A");
		BinaryNode<String> leftChild = new BinaryNode<String>("B");
		BinaryNode<String> rightChild = new BinaryNode<String>("C");
		root.setLeftChild(leftChild);
		root.setRightChild(rightChild);
		BinaryTree<String> tree = new BinaryTree<String>(root);
		
		System.out.print("Inorder traversal: ");
		tree.printInorder();
		System.out.println();
	
		System.out.print("Preorder traversal: ");
		tree.printPreorder();
		System.out.println();
		
		System.out.print("Postorder traversal: ");
		tree.printPostorder();
		System.out.println();
	}
	
	/**
	 * Demonstrates class Expression
	 */
	public static void demoExpression() {
		System.out.println("-------------------------------\nDemo of Class Expression");
		Expression expression = new Expression("((26.5 + 43.5) / ((8 * 64) / 16))");
		System.out.println("Expression: " + expression.getExpression());
		System.out.println("Expression using infix notation (no parenthesis): " + expression.getInfixNotation());
		System.out.println("Expression using polish notation: " + expression.getPolishNotation());
		System.out.println("Expression using reversed polish notation: " + expression.getReversedPolishNotation());
		System.out.println("Value of expression: " + expression.evaluate());
	}
	
	/**
	 * Demonstrates class ExpressionTree
	 */
	public static void demoExpressionTree() {
		System.out.println("-------------------------------\nDemo of Class ExpressionTree");
		ExpressionTree expTree = new ExpressionTree("((26.5 + 43.5) / ((8 * 64) / 16))");
		System.out.println("Expression: " + expTree.getExpression());
		System.out.println("Expression using infix notation (no parenthesis): " + expTree.getInfixNotation());
		System.out.println("Expression using polish notation: " + expTree.getPolishNotation());
		System.out.println("Expression using reversed polish notation: " + expTree.getReversedPolishNotation());
		System.out.println("Value of expression: " + expTree.evaluate());
	}
}
