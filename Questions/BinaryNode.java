public class BinaryNode<E> {

	private E item;
	
	private BinaryNode<E> left;
	
	private BinaryNode<E> right;
	
	public BinaryNode(E item) {
		this.item = item;
	}
	public BinaryNode(E item, BinaryNode<E> left, BinaryNode<E> right) {
		this.item = item;
		this.left = left;
		this.right = right;
	}
	
	public E getItem() {
		return item;
	}	
	
	public BinaryNode<E> getLeft() {
		return left;
	}
	
	public BinaryNode<E> getRight() {
		return right;
	}
	
	public boolean isLeaf() {
		return (left == null) && (right == null);
	}
	
	public void setItem(E item) { 
		this.item = item;
	}
	
	public void setLeft(BinaryNode<E> left) {
		this.left = left;
	}
	
	public void setRight(BinaryNode<E> right) {
		this.right = right;
	}
}
