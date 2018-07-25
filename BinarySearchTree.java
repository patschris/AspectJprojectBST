/**
 * An implementation of a Binary Search Tree (BST). Supports methods insert, remove, lookup and printTree.
 * Based on http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
 * @author Christos Patsouras
 */
public class BinarySearchTree {

	/**
	 * Binary Search Tree's root
	 */
	private TreeNode root;
	
	/**
	 * Constructor of BinarySearchTree class
	 */
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * Returns the root of the BST
	 * @return BST's root
	 */
	public TreeNode getRoot() {
		return root;
	}
	
	/**
	 * Add a node the tree with value data
	 * @param data The data field of the new Tree Node
	 */
	public boolean insert (int data) {
		System.out.println(Thread.currentThread().getName() +": Trying to insert new node with data " + data + ".");
		/*********************/
		/* Create a new node */
		/*********************/
		TreeNode node = new TreeNode(data);
		/*****************/
		/* Tree is empty */
		/*****************/ 
		if (root == null) {
			root = node; // the new node is the tree's root
			System.out.println(Thread.currentThread().getName() + ": Insertion of node with data " + data + " succeeded.");
			return true;
		}
		/**********************************/
		/*         Tree not empty         */
		/* Trying to find where to insert */
		/**********************************/
		TreeNode current = root;
		TreeNode parent = null;
		while (true) {
			parent = current;
			if (data < current.getData()) {
				current = current.getLeftChild();
				if (current == null){
					parent.setLeftChild(node);
					System.out.println(Thread.currentThread().getName() + ": Insertion of node with data " + data + " succeeded.");
					return true;
				}
			}
			else if (data > current.getData()) {
				current = current.getRightChild();
				if (current == null) {
					parent.setRightChild(node);
					System.out.println(Thread.currentThread().getName() + ": Insertion of node with data " + data + " succeeded.");
					return true;
				}
			}
			else {
				System.out.println(Thread.currentThread().getName() + ": Already existed node. Insertion failed.");
				return false;
			}
		}
	}
	
	/**
	 * Delete a node of the tree with value data
	 * @param data The data field of the new Tree Node
	 * @return True if the removal was successful, otherwise false
	 */
	public boolean remove (int data) {
		System.out.println(Thread.currentThread().getName() + ": Trying to remove node with data " + data + ".");
		if (root == null) {
			System.out.println(Thread.currentThread().getName() + ": The BST is empty. Removal failed.");
			return false;
		}
		TreeNode current = root;
		TreeNode parent = root;
		boolean isLeftChild = false;
		while (current.getData() != data) {
			parent = current;
			if (current.getData() > data) {
				isLeftChild = true;
				current = current.getLeftChild();
			}
			else {
				isLeftChild = false;
				current = current.getRightChild();
			}
			/* There is no node with the given data */
			if (current == null) {
				System.out.println(Thread.currentThread().getName() + ": There is no node with data " + data + ". Removal failed.");
				return false;
			}
		}
		/* if I am here that means we have found the node */
		
		/* if the node to be deleted has no children */
		if(current.getLeftChild()==null && current.getRightChild()==null){
			if(current==root) root = null;
			if(isLeftChild == true) parent.setLeftChild(null);
			else parent.setRightChild(null);
		}
		/* if node to be deleted has only one child */
		else if(current.getRightChild()==null){
			if(current==root) root = current.getLeftChild();
			else if (isLeftChild) parent.setLeftChild(current.getLeftChild());
			else parent.setRightChild(current.getLeftChild());
		}
		else if(current.getLeftChild()==null){
			if(current == root) root = current.getRightChild();
			else if(isLeftChild) parent.setLeftChild(current.getRightChild());
			else parent.setRightChild(current.getRightChild());
		}
		else if(current.getLeftChild()!=null && current.getRightChild() != null){
			/* now we have found the minimum element in the right sub tree */
			TreeNode successor = getSuccessor(current);
			if(current == root) root = successor;
			else if(isLeftChild) parent.setLeftChild(successor);			
			else parent.setRightChild(successor);	
			successor.setLeftChild(current.getLeftChild());
		}
		System.out.println(Thread.currentThread().getName() + ": Removal of node with data " + data + " succeeded.");
		return true;
	}

	/**
	 * Find a node of the tree with value data
	 * @param data The data field of the new Tree Node
	 * @return True if the function finds the node with the value data, otherwise false
	 */
	public boolean lookup(int data) {
		System.out.println(Thread.currentThread().getName() + ": Trying to find the node with data " + data + ".");
		TreeNode current = root;
		while (current != null) {
			if (current.getData() == data) {
				System.out.println(Thread.currentThread().getName() + ": Just found the node with data " + data + ".");
				return true;
			}
			else if (current.getData() > data) current = current.getLeftChild();
			else if (current.getData() < data) current = current.getRightChild();
		}
		System.out.println(Thread.currentThread().getName() + ": Cannot find the node with data " + data + ".");
		return false;
	}

	/**
	 * Prints the entire tree in increas­ing order
	 * @param root The root of the BST
	 */
	public void printTree(TreeNode root){
		if(root!=null){
			printTree(root.getLeftChild());
			System.out.print(" " + root.getData());
			printTree(root.getRightChild());
		}
	}

	/**
	 * Suc­ces­sor is the node which will replace the deleted node. 
	 * Suc­ces­sor is the smaller node in the right sub tree of the node to be deleted.
	 * @param deleleNode The node that function remove is going to delete
	 * @return The successor of the deleteNode
	 */
	private TreeNode getSuccessor(TreeNode deleleNode){
		TreeNode successsor =null;
		TreeNode successsorParent =null;
		TreeNode current = deleleNode.getRightChild();
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.getLeftChild();
		}
		/* check if the successor has the right child, it cannot have left child for sure if it does have the right child, add it to the left of successorParent. */
		if(successsor!=deleleNode.getRightChild()){
			successsorParent.setLeftChild(successsor.getRightChild());
			successsor.setRightChild(deleleNode.getRightChild());
		}
		return successsor;
	}
}