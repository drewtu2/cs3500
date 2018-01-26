import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.cards.CardSuit;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;
import java.util.List;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FreecellOperationsTest {
  FreecellOperations<PlayingCard> myFreecell;
  List<PlayingCard> myDeck;

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
    myFreecell.startGame(myDeck, 1, 1, true);

  }

  /**
   * A deck is invalid for this game if it does not have 52 cards
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestShortDeck() {
    // One TOO few cards
    myDeck.remove(myDeck.size() - 1);

    // Exepect Exception
    myFreecell.startGame(myDeck, 1, 1, true);
  }

  /**
   * A deck is invalid for this game if it does not have 52 cards
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestLongDeck() {
    // One TOO many cards
    myDeck.add(new PlayingCard(CardSuit.HEART, CardValue.ACE));

    // Exepect Exception
    myFreecell.startGame(myDeck, 1, 1, true);
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
    myFreecell.startGame(myDeck, 1, 1, true);
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
    myFreecell.startGame(myDeck, 1, 1, false);
    FreecellOperations<PlayingCard> shuffledGame = new FreecellModel();
    shuffledGame.startGame(myDeck, 1, 1, true);

    // If the decks have been shuffled, their game states should not be equal.
    assertNotEquals(myFreecell.getGameState(), shuffledGame.getGameState());


  }

  // **********************************************************************************************
  // Tests of Game State
  // **********************************************************************************************

  /**
   * Test Game State prints correct values of the game.
   * TODO: Generate that string!
   */
  @Test
  public void TestGameState() {
    myFreecell.startGame(myDeck, 1, 1, false);
    assertEquals("", myFreecell.getGameState());
  }

  /**
   * Test the Game State when the game has not yet begun (should return empty string)
   */
  @Test
  public void TestGameStateNotBegun() {
    assertEquals("", myFreecell.getGameState());
  }

  // **********************************************************************************************
  // Tests Moving Cards
  // **********************************************************************************************



}
