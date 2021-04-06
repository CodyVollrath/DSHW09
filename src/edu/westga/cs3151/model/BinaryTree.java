package edu.westga.cs3151.model;

import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayDeque;

/**
 * BinaryTree
 *
 * @param <E> type of the node values
 * @author CS3151
 * @version Spring 2021
 */

public class BinaryTree<E> implements Iterable<E> {
	private BinaryNode<E> root;

	/**
	 * Instantiates an empty binary tree
	 *
	 * @pre none
	 * @post getRoot() == null
	 */
	public BinaryTree() {
		this(null);
	}

	/**
	 * Instantiates an new binary tree
	 *
	 * @pre root is the root node of a binary tree
	 * @post getRoot() == root
	 * @param root the new root of this tree
	 */
	public BinaryTree(BinaryNode<E> root) {
		this.root = root;
	}

	/**
	 * Returns the root node
	 * 
	 * @pre none
	 * @post none
	 * @return the root node of this tree
	 */
	public BinaryNode<E> getRoot() {
		return this.root;
	}

	/**
	 * Resets the root of this tree
	 * 
	 * @pre root is the root node of a binary tree
	 * @post getRoot() == root
	 * @param root the new root of this tree
	 */
	public void setRoot(BinaryNode<E> root) {
		this.root = root;
	}
	
	/**
	 * Prints the node values of this tree in in-order
	 * 
	 * @pre none
	 * @post none
	 */
	public void printInorder() {
		if (this.root != null) {
			new BinaryTree<E>(this.root.getLeftChild()).printInorder();
			System.out.print(this.root.getValue());
			new BinaryTree<E>(this.root.getRightChild()).printInorder();
		}	
	}
	
	/**
	 * Prints the node values of this tree in pre-order
	 * 
	 * @pre none
	 * @post none
	 */
	public void printPreorder() {
		if (this.root != null) {
			System.out.print(this.root.getValue());
			new BinaryTree<E>(this.root.getLeftChild()).printPreorder();
			new BinaryTree<E>(this.root.getRightChild()).printPreorder();
		}	
	}
	
	/**
	 * Prints the node values of this tree in post-order
	 * 
	 * @pre none
	 * @post none
	 */
	public void printPostorder() {
		if (this.root != null) {
			new BinaryTree<E>(this.root.getLeftChild()).printPostorder();
			new BinaryTree<E>(this.root.getRightChild()).printPostorder();
			System.out.print(this.root.getValue());
		}
	}

	/**
	 * Returns an iterator that traverses this binary tree in level-order
	 * 
	 * @pre none
	 * @post none
	 * @return a level-order iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new LevelOrderIterator();
	}

	/**
	 * Returns an iterator that traverses this binary tree in in-order
	 * 
	 * @pre none
	 * @post none
	 * @return an in-order iterator
	 */
	public Iterator<E> inOrderIterator() {
		return new InOrderIterator();
	}

	/**
	 * Returns an iterator that traverses this binary tree in post-order
	 * 
	 * @pre none
	 * @post none
	 * @return a post-order iterator
	 */
	public Iterator<E> postOrderIterator() {
		return new PostOrderIterator();
	}

	/**
	 * Returns an iterator that traverses this binary tree in pre-order
	 * 
	 * @pre none
	 * @post none
	 * @return a pre-order iterator
	 */
	public Iterator<E> preOrderIterator() {
		return new PreOrderIterator();
	}

	/**
	 * Class LevelOrderIterator
	 * 
	 * An iterator that traverses this binary tree in level-order
	 */
	protected class LevelOrderIterator implements Iterator<E> {
		private Queue<BinaryNode<E>> nodeQueue;

		public LevelOrderIterator() {
			this.nodeQueue = new ArrayDeque<BinaryNode<E>>();
			this.nodeQueue.add(BinaryTree.this.root);
		}

		@Override
		public boolean hasNext() {
			return !this.nodeQueue.isEmpty();
		}

		@Override
		public E next() {
			BinaryNode<E> node = this.nodeQueue.remove();
			if (node.hasLeftChild()) {
				this.nodeQueue.add(node.getLeftChild());
			}
			if (node.hasRightChild()) {
				this.nodeQueue.add(node.getRightChild());
			}
			return node.getValue();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Class InOrderIterator
	 * 
	 * An iterator that traverses this binary tree in in-order
	 */
	protected class InOrderIterator implements Iterator<E> {
		private BinaryNode<E> next;

		public InOrderIterator() {
			this.next = BinaryTree.this.getRoot();
			if (this.next != null) {
				while (this.next.hasLeftChild()) {
					this.next = this.next.getLeftChild();
				}
			}
		}

		@Override
		public boolean hasNext() {
			return this.next != null;
		}

		@Override
		public E next() {
			E returnValue = this.next.getValue();
			if (this.next.hasRightChild()) {
				this.next = this.next.getRightChild();
				while (this.next.hasLeftChild()) {
					this.next = this.next.getLeftChild();
				}
			} else {
				while (this.next.hasParent() && this.next.getParent().getRightChild() == this.next) {
					this.next = this.next.getParent();
				}
				this.next = this.next.getParent();
			}
			return returnValue;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	protected class PreOrderIterator implements Iterator<E> {
		private Stack<BinaryNode<E>> nodeStack;
		public PreOrderIterator() {
			this.nodeStack = new Stack<BinaryNode<E>>();
			this.nodeStack.push(BinaryTree.this.root);
		}
		
		@Override
		public boolean hasNext() {
			return !this.nodeStack.empty();
		}

		@Override
		public E next() {
			BinaryNode<E> newNode = this.nodeStack.pop();
			if (newNode.getRightChild() != null) {
				this.nodeStack.push(newNode.getRightChild());
			}
			
			if (newNode.getLeftChild() != null) {
				this.nodeStack.push(newNode.getLeftChild());
			}
			return newNode.getValue();
		}

		@Override
		public void remove() {
			this.nodeStack.pop();
		}
	}
	
	protected class PostOrderIterator implements Iterator<E> {
		BinaryNode<E> currNode;
		private boolean finished;
		public PostOrderIterator() {
			this.currNode = BinaryTree.this.root;
			this.finished = false;
			while (this.currNode.hasLeftChild()) {
				this.currNode = this.currNode.getLeftChild();
			}
		}
		
		@Override
		public boolean hasNext() {
			return !this.finished;
		}

		@Override
		public E next() {
			E value = this.currNode.getValue();
			if (!this.currNode.hasParent()) {
				this.finished = true;
				return value;
			}
			
			if (this.currNode.getParent().getRightChild().equals(this.currNode)) {
				this.currNode = this.currNode.getParent();
			} else if (this.currNode.getParent().getLeftChild().equals(this.currNode)) {
				this.currNode = this.currNode.getParent().getRightChild();
				while (this.currNode.hasLeftChild()) {
					this.currNode = this.currNode.getLeftChild();
				}
			}
			return value;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
