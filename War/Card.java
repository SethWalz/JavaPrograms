public class Card {

	// Suit of spades.
	public static final int SPADES = 0;

	// Suit of hearts.
	public static int HEARTS = 1;

	// Suit of diamonds.
	public static final int DIAMONDS = 2;

	//Two
	public static final int TWO = 2;

	// Suit of clubs
	public static final int  CLUBS = 3;

	// Rank of ace, equivalents to 1.
	public static final int ACE = 14;

	// Rank of jack.
	public static final int JACK = 11;

	// Rank of queen.
	public static final int QUEEN = 12;

	// Rank of king.
	public static final int KING = 13;

	private int rank;

	private int suit;

	public Card(int rank, int suit){
		this.rank=rank;
		this.suit=suit;

	}
	public int getRank() {
		return rank;

	}
	public int getSuit() {
	return suit;

	}
	public boolean equals(Object that) {
		if (this == that){
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		Card thatCard= (Card)that;
		return rank == thatCard.rank;

	}
	public String toString() {
		return "" + "--23456789TJQKA".charAt(rank) + "shdc".charAt(suit);
	}
}