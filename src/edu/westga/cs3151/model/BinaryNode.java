package edu.westga.cs3151.model;

/**
 * BinaryNode - one node in a binary tree
 *
 * @param <T> type of node value
 * @author CS3151
 * @version Spring 2021
 */
public class BinaryNode<T> {
	private T value;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;
	private BinaryNode<T> parent;

	/**
	 * Instantiates a new binary node
	 * 
	 * @pre none
	 * @post getValue() == null AND !hasLeftChild() AND !hasRightChild() AND
	 *       !hasParent()
	 */
	public BinaryNode() {
		this(null);
	}

	/**
	 * Instantiates a new binary node
	 *
	 * @pre none
	 * @post getValue() == value AND !hasLeftChild() AND !hasRightChild() AND
	 *       !hasParent()
	 * @param value the value of the new node
	 */
	public BinaryNode(T value) {
		this.value = value;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}

	/**
	 * Returns the left child node
	 * 
	 * @pre none
	 * @post none
	 * @return returns the left child of this node
	 */
	public BinaryNode<T> getLeftChild() {
		return this.leftChild;
	}

	/**
	 * Returns the right child node
	 * 
	 * @pre none
	 * @post none
	 * @return returns the right child of this node
	 */
	public BinaryNode<T> getRightChild() {
		return this.rightChild;
	}

	/**
	 * Returns the parent
	 * 
	 * @pre none
	 * @post none
	 * @return returns the parent of this node
	 */
	public BinaryNode<T> getParent() {
		return this.parent;
	}

	/**
	 * Returns the value
	 * 
	 * @pre none
	 * @post none
	 * @return returns the value of this node
	 */
	public T getValue() {
		return this.value;
	}

	/**
	 * Sets the left child
	 * 
	 * @pre none
	 * @post getLeftChild() == child
	 * @param child the new left child of this node
	 */
	public void setLeftChild(BinaryNode<T> child) {
		this.leftChild = child;
		child.parent = this;
	}

	/**
	 * Sets the right child
	 *
	 * @pre none
	 * @post getRightChild() == child
	 * @param child the new right child of this node
	 */
	public void setRightChild(BinaryNode<T> child) {
		this.rightChild = child;
		child.parent = this;
	}

	/**
	 * Sets the node value
	 *
	 * @pre none
	 * @post getValue() == value
	 * @param value the new value of this node
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * Checks if this node has a left child
	 *
	 * @pre none
	 * @post none
	 * @return true if this node has a left child
	 */
	public boolean hasLeftChild() {
		return this.leftChild != null;
	}

	/**
	 * Checks if this node has a right child
	 *
	 * @pre none
	 * @post none
	 * @return true if this node has a right child
	 */
	public boolean hasRightChild() {
		return this.rightChild != null;
	}

	/**
	 * Checks if this node has a parent
	 *
	 * @pre none
	 * @post none
	 * @return true if this node has a parent
	 */
	public boolean hasParent() {
		return this.parent != null;
	}
	
	@Override
	public String toString() {
		return this.value.toString();
	}
}
