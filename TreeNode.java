/**
 * A BST's node that contains its data, a left child and right child
 * @author Christos Patsouras
 */
public class TreeNode {
	/**
	* Node's data
	*/
	private int data;
	/**
	* Node's left child
	*/	
	private TreeNode leftChild;
	/**
	* Node's right child
	*/	
	private TreeNode rightChild;	
	/**
	 * Constructor of TreeNode class
	 * @param d The data field of the node
	 */
	public TreeNode(int data){
		this.data = data;
		this.leftChild = null;
		this.rightChild = null;
	}
	/**
	 * Get the data of the node
	 * @return Current node's data
	 */
	public int getData() {
		return data;
	}
	/**
	 * Get the left child of the node
	 * @return the left the child of the node
	 */
	public TreeNode getLeftChild() {
		return leftChild;
	}
	/**
	 * Set the left child of the node
	 * @param leftChild The new left child of the node
	 */
	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}
	/**
	 * Get the right child of the node
	 * @return the right the child of the node
	 */
	public TreeNode getRightChild() {
		return rightChild;
	}
	/**
	 * Set the right child of the node
	 * @param rightChild The new right child of the node
	 */
	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}
}