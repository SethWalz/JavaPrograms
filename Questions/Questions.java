import java.util.Scanner;

public class Questions {
	public static final Scanner INPUT = new Scanner(System.in);
	
	private BinaryNode<String> root;
	
	public Questions() {
		root = new BinaryNode<String>("a giraffe");
	}
	
	public static void main(String[] args) {
		Questions game = new Questions();
		System.out.println("Welcome to Questions.");
		do {
			System.out.println();
			game.play();
			System.out.print("Play again (yes or no)? ");
			} while (INPUT.nextLine().equals("yes"));		
	}

	public void play() {
		BinaryNode<String> node = root;
		while (!(node.isLeaf())) {
			System.out.print(node.getItem() + " ");
			if (INPUT.nextLine().equals("yes")) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
		System.out.print("Is it ..." + node.getItem() + "? ");
		if (INPUT.nextLine().equals("yes")) {
			System.out.println("I win!");
		} else {
			System.out.println("I give up.");
			learn(node);
		}
	}

	protected void learn(BinaryNode<String> node) {
		System.out.print("What is it? ");
		String correct = INPUT.nextLine();
		System.out.println("I need question to distinguish that from " + node.getItem() + ".");
		System.out.println("The answer for " + correct + " should be yes.");
		System.out.print("Enter the question: ");
		String question = INPUT.nextLine();
		node.setLeft(new BinaryNode<String>(correct));
		node.setRight(new BinaryNode<String>(node.getItem()));
		node.setItem(question);
	}
}
