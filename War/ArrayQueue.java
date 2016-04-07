// An array-based queue
public class ArrayQueue<E> implements Queue<E> {

	// Array of items in this queue.
	private E[] data;

	// Index of the frontmost element in this Queue.
	private int front;

	// Number of items currently in this Queue.
	private int size;

	// The Queue is initially empty.
	public ArrayQueue() {
		data = (E[])(new Object[1]);// This causes a compiler warning
		size = 0;
		front = 0;
	}

	//Peek
	public E peek() {
			if (isEmpty()) {
				throw new EmptyStructureException();
			}
			E result = data[(front + size - 1) % data.length];
			return result;
	}

	// Add
	public void add(E target) {
		if (isFull()) {
			stretch();
		}
		data[(front + size) % data.length] = target;
		size++;
	}
	public int getSize()
	{
		return size;
	}


	//isEmpty
	public boolean isEmpty() {
		return size == 0;
	}

	//isFull
	protected boolean isFull() {
		return size == data.length;
	}

	// remove
	public E remove() {
		if (isEmpty()) {
			throw new EmptyStructureException();
		}
		E result = data[front];
		front = (front + 1) % data.length;
		size--;
		return result;
	}

	// Stretch
	protected void stretch() {
		E[] newData = (E[])(new Object[data.length * 2]);//warning
		for (int i = 0; i < data.length; i++){
			newData[i] = data[(front + i) % data.length];
		}
		data = newData;
		front = 0;
	}
}