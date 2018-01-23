import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PlayingCard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FreecellOperationsTest {
  FreecellOperations<PlayingCard> myFreecell = new FreecellModel();

  // **********************************************************************************************
  // Tests of Deck Validity
  // **********************************************************************************************
  /**
   * A correct deck of cards for Freecell contains 52 cards with ace, 2, ..., jack, queen, king in
   * each of clubs, diamonds, hearts and spades) as a List of card objects.
   */
  @Test
  public void TestValidDeck() {

  }
  // **********************************************************************************************
  // Tests Starting Game
  // **********************************************************************************************

  /**
   * A deck is invalid for this game if it does not have 52 cards
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestShortDeck() {

  }

  /**
   * A deck is invalid for this game if it has duplicate cards
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestDuplicateCards() {

  }

  /**
   * A deck is invalid for this game if it has invalid cards
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestDeckInvalidCards() {

  }

  /**
   * Checks that a game with a shuffled deck is different than non-shuffled deck
   */
  @Test
  public void TestShuffled() {

  }
  // **********************************************************************************************
  // Tests Moving Cards
  // **********************************************************************************************

  /**
   * Tests
   */
  @Test





  // **********************************************************************************************
  // Tests of Game State
  // **********************************************************************************************

  /**
   * Test Game State prints correct values of the game.
   */
  @Test
  public void TestGameState() {

  }

  /**
   * Test the Game State when the game has not yet begun (should return empty string)
   */
  @Test
  public void TestGameStateNotBegun() {

  }









}
