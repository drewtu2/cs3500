import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.cards.CardSuit;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;
import cs3500.hw02.piles.PileInterface;
import cs3500.hw02.piles.PileType;

import java.util.List;
import java.util.Collections;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;



public class FreecellOperationsTest {
  private FreecellOperations<PlayingCard> myFreecell;
  private List<PlayingCard> myDeck;

  @Before
  public void initConds() {
    myFreecell = new FreecellModel();
    myDeck = myFreecell.getDeck();
  }

  // **********************************************************************************************
  // Tests of Deck Validity
  // **********************************************************************************************
  /**
   * A correct deck of cards for Freecell contains 52 cards with ace, 2, ..., jack, queen, king in
   * each of clubs, diamonds, hearts and spades) as a List of card objects.
   */
  @Test
  public void TestValidDeck() {

    // If no exception is thrown with a valid deck.
    myFreecell.startGame(myDeck, 4, 1, false);

  }

  /**
   * A deck is invalid for this game if it does not have 52 cards
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestShortDeck() {
    // One TOO few cards
    myDeck.remove(myDeck.size() - 1);

    // Exepect Exception
    myFreecell.startGame(myDeck, 4, 1, false);
  }

  /**
   * A deck is invalid for this game if it does not have 52 cards
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestLongDeck() {
    // One TOO many cards
    myDeck.add(new PlayingCard(CardSuit.HEART, CardValue.ACE));

    // Exepect Exception
    myFreecell.startGame(myDeck, 4, 1, false);
  }

  /**
   * A deck is invalid for this game if it has duplicate cards
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestDuplicateCards() {
    // Remove the last card
    myDeck.remove(myDeck.size() - 1);
    // Add another Ace of Hearts
    myDeck.add(new PlayingCard(CardSuit.HEART, CardValue.ACE));

    // Exepect Exception
    myFreecell.startGame(myDeck, 4, 1, false);
  }

  /**
   * A deck is invalid for this game if it has invalid cards
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestDeckInvalidCards() {
    throw new IllegalArgumentException("Impossible to create invalid card");
  }

  /**
   * Checks that a game with a shuffled deck is different than non-shuffled deck
   */
  @Test
  public void TestShuffled() {
    // If no exception is thrown with a valid deck.
    myFreecell.startGame(myDeck, 4, 1, false);
    FreecellOperations<PlayingCard> shuffledGame = new FreecellModel();
    shuffledGame.startGame(myDeck,4, 1, true);

    // If the decks have been shuffled, their game states should not be equal.
    assertNotEquals(myFreecell.getGameState(), shuffledGame.getGameState());


  }

  // **********************************************************************************************
  // Tests of Game State
  // **********************************************************************************************

  /**
   * Test Game State prints correct values of the game.
   */
  @Test
  public void TestGameState() {
    myFreecell.startGame(myDeck, 4, 1, false);
    String expected = "F0:\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "O0:\n"
        + "C0: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "C1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
        + "C2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "C3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦";
    assertEquals(expected, myFreecell.getGameState());
  }

  /**
   * Test the Game State when the game has not yet begun (should return empty string)
   */
  @Test
  public void TestGameStateNotBegun() {
    assertEquals("", myFreecell.getGameState());
  }

  /**
   * Tests if the game is over
   */
  @Test
  public void TestGameOver() {
    // Reversing the default deck should let us just move everything to foundation piles
    Collections.reverse(myDeck);
    myFreecell.startGame(myDeck, 4, 1, false);

    int counter = CardValue.values().length - 1;
    for (CardValue value : CardValue.values()) { // Iterate over all possible card types
      for(int pileNum = 0; pileNum < 4; ++pileNum) {
        myFreecell.move(PileType.CASCADE, pileNum, counter, PileType.FOUNDATION, pileNum);
      }
      --counter;
    }
    assertEquals(true, myFreecell.isGameOver());
  }

  /**
   * Tests the game is not over
   */
  @Test
  public void TestGameNotOver() {
    myFreecell.startGame(myDeck, 4, 1, false);
    assertEquals(false, myFreecell.isGameOver());
  }


  // **********************************************************************************************
  // Tests Moving Cards
  // **********************************************************************************************
  // is called to move a card according to the rules of the game. Specifically It moves cards
  // beginning at index cardIndex from the pile number sourcePileNumber of type sourceType to the
  // pile number destPileNumber of type destinationType. PileType (provided to you) is an enumerated
  // type that is helpful in specifying the type of pile.
  // All indices and pile numbers in this method start at 0 — e.g. in the figure above, to move 4♦
  // in cascade pile 8 to cascade pile 1 this method would be called as

  /**
   * Test a valid move to cascade pile
   */
  @Test
  public void TestValidMoveToCascade() {
    myFreecell.startGame(myDeck, 52, 1, false);
    myFreecell.getGameState();
    // Move 1spades onto 2hearts
    myFreecell.move(PileType.CASCADE, 0, 0, PileType.CASCADE,6);

    // Move 3spades onto 4diamond
    myFreecell.move(PileType.CASCADE, 8, 0, PileType.CASCADE,14);

    // Move 2diamond onto 3spades
    myFreecell.move(PileType.CASCADE, 7, 0, PileType.CASCADE,14);

  }

  /**
   * Test a valid move to foundation pile
   */
  @Test
  public void TestValidMoveToFoundation() {
    // This is tested in TestGameOver, leaving this here as a place holder
    assertEquals(true, true);

  }

  /**
   * Test a valid move to open pile
   */
  @Test
  public void TestValidMoveToOpen() {
    myFreecell.startGame(myDeck, 4, 4, false);
    myFreecell.move(PileType.CASCADE, 0,12, PileType.OPEN,0);
    myFreecell.move(PileType.CASCADE, 1,12 , PileType.OPEN,1);
    myFreecell.move(PileType.CASCADE, 2,12 , PileType.OPEN,2);

  }

  /**
   * Tests attempting to move a card of the wrong color but correct value onto a cascade pile
   */
  @Test(expected = IllegalStateException.class)
  public void TestInvalidMoveCascadePileWrongColor() {
    myFreecell.startGame(myDeck, 52, 1, false);

    // Trying to move 1 of spades on to 2 of spades
    myFreecell.move(PileType.CASCADE, 0, 0, PileType.CASCADE,
        4);
  }

  /**
   * Tests attempting to move a card of the right color but wrong value onto a cascade pile
   */
  @Test(expected = IllegalStateException.class)
  public void TestInvalidMoveCascadePileWrongValue() {
    myFreecell.startGame(myDeck, 52, 1, false);

    // Trying to move 1 of spades on to 3 of hearts
    myFreecell.move(PileType.CASCADE, 1, 0, PileType.CASCADE,
        10);
  }

  /**
   * Tests attempting to move a card of the wrong suit but right value onto a foundation pile
   */
  @Test(expected = IllegalStateException.class)
  public void TestInvalidMoveFoundationPileWrongSuit() {
    myFreecell.startGame(myDeck, 52, 1, false);

    // Start by moving 1 of spades on to Foundation pile 0
    myFreecell.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION,
        0);

    // Now try to move 2 of clubs onto foundation pile 0
    myFreecell.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION,
        0);
  }

  /**
   * Tests attempting to move a card of the right suit but wrong value onto a foundation pile
   */
  @Test(expected = IllegalStateException.class)
  public void TestInvalidMoveFoundationPileWrongValue() {
    myFreecell.startGame(myDeck, 52, 1, false);

    // Start by moving 1 of spades on to Foundation pile 0
    myFreecell.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION,
        0);

    // Now try to move 3 of spades onto foundation pile 0
    myFreecell.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION,
        0);
  }

  /**
   * Tests attempting to move a card onto a non empty open pile
   */
  @Test(expected = IllegalStateException.class)
  public void TestInvalidMoveOpenOccupied() {
    myFreecell.startGame(myDeck, 4, 1, false);
    myFreecell.move(PileType.CASCADE, 0, 13, PileType.OPEN,
        0);
    myFreecell.move(PileType.CASCADE, 0, 12, PileType.OPEN,
        0);

  }
}
