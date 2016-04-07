import java.io.*;
import java.util.Scanner;

public class Deck {

	private Card[] cards;

	private int size;

	public Deck() {
		int Char1,Char2, rank, suit;
		cards = new Card[52];
		String s, s1,s2;
		s1 = new String("--23456789TJQKA");
		s2 = new String("shdc");
		try {
			File infile = new File("Cards");
			Scanner input = new Scanner(infile);
			for (size = 0; size < 52;size++) {
				s = input.nextLine();
				Char1 = s.charAt(0);
				Char2 = s.charAt(1);
				rank = s1.indexOf(Char1);
				suit = s2.indexOf(Char2);
				cards[size] = new Card(rank, suit);
			}
		}catch (Exception e) {
			System.out.println("File not found...\n\n");
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public Card deal() {
		size--;
		return cards[size];
	}


	protected void swap(int i, int j) {
		Card temp = cards[i];
		cards[i] = cards[j];
		cards[j] = temp;
	}

}