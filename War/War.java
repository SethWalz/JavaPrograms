import java.util.Scanner;

/** The card game War for two players. */
public class War {

	private int round = 1;

  /** For reading from the Console. */
  public static final Scanner INPUT = new Scanner(System.in);

  /** Player 1's pile of Cards. */
  private Queue<Card> hand1;

  /** Player 2's pile of Cards. */
  private Queue<Card> hand2;

  /** Deal all the Cards out to the players. */
  public War() {
    hand1 = new ArrayQueue<Card>();
    hand2 = new ArrayQueue<Card>();
    Deck deck = new Deck();
    while (!(deck.isEmpty())) {
      hand1.add(deck.deal());
      hand2.add(deck.deal());
    }
  }

  /** Give all of the Cards played to the winning player. */
  public void give(Queue<Card> queue1,
                   Queue<Card> queue2,
                   Queue<Card> winner) {
    if (winner == hand1) {
      System.out.println("Player 1 gets the cards");
    } else {
      System.out.println("Player 2 gets the cards");
    }
    while (!(queue1.isEmpty())) {
	  winner.add(queue1.remove());
	}
    while (!(queue2.isEmpty())) {
	  winner.add(queue2.remove());
	}
  }

  /** Play until one player runs out of Cards. */
  public void play() {

    while (!(hand1.isEmpty() || hand2.isEmpty())) {

			System.out.print("\n--------------------------");
			System.out.print("\nRound: " + round);
			System.out.print("\nPlayer 1 cards: " + hand1.getSize());
			System.out.print("\nPlayer 2 cards: " + hand2.getSize());


      System.out.print("\nHit return to play round: \n");
      INPUT.nextLine();
      playRound();
      if (hand1.isEmpty()) {
        System.out.println("Player 2 wins!");
      }
      if (hand2.isEmpty()) {
        System.out.println("Player 1 wins!");
      }

    }
  }

  /** Play one round. */
  public void playRound() {
	  round++;

    Queue<Card> queue1 = new ArrayQueue<Card>();
    Queue<Card> queue2 = new ArrayQueue<Card>();
    queue1.add(hand1.remove());
    queue2.add(hand2.remove());
    do {
      Card card1 = queue1.peek();
      Card card2 = queue2.peek();
      System.out.println(card1 + " " + card2);
      Queue<Card> winner = null;
      if (card1.getRank() > card2.getRank()) {
		winner = hand1;
	  }
      if (card1.getRank() < card2.getRank()) {
		winner = hand2;
      }
      if (winner != null) {
        give(queue1, queue2, winner);
        return;
      }

    } while (!settledByWar(queue1, queue2));
  }

  /**
   * Play a war over stack1 and stack2.  If this ends the game because
   * one player runs out of cards, give the cards to the winning
   * player and return true.  Otherwise, return false.
   */
  public boolean settledByWar(Queue<Card> queue1, Queue<Card> queue2) {
    System.out.println("War!");
    for (int i = 0; i < 4; i++) {
      if (hand1.isEmpty()) {
        give(queue1, queue2, hand2);
        return true;
      }
      queue1.add(hand1.remove());
      if (hand2.isEmpty()) {
        give(queue1, queue2, hand1);
        return true;
      }
      queue2.add(hand2.remove());
    }
    return false;
  }

  /** Create and play the game. */
  public static void main(String[] args) {
    System.out.println("Welcome to War.\n");
    War game = new War();
    game.play();
  }

}
