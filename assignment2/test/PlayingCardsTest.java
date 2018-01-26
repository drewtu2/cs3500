package cs3500.hw02;

import cs3500.hw02.cards.CardSuit;
import cs3500.hw02.cards.PlayingCard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This junit class tests the PlayingCard class
 */
public class PlayingCardsTest {
  PlayingCard myCard = new PlayingCard(CardSuit.SPADE, 1);

  /**
   * Ensures the toString() representation of the class is working.
   */
  @Test
  public void TestToString() {
    assertEquals("1 of Spades", myCard.toString());
  }

  /**
   * Tests two playing cards are equal
   */
  @Test
  public void TestEquals() {
    PlayingCard card2 = new PlayingCard(CardSuit.SPADE, 1);

    assertEquals(card2, myCard);

  }

  /**
   * Tests the value of two playing cards are not equal
   */
  @Test
  public void TestValueNotEqual() {
    PlayingCard card2 = new PlayingCard(CardSuit.SPADE, 2);
    assertNotEquals(card2, myCard);
  }

  /**
   * Tests the suit of two playing cards are not equal
   */
  @Test
  public void TestSuitNotEqual() {
    PlayingCard card2 = new PlayingCard(CardSuit.SPADE, 2);

    assertNotEquals(card2, myCard);
  }

  /**
   * Tests the hash of two playing cards are equal
   */
  @Test
  public void TestHashEqual() {
    PlayingCard card2 = new PlayingCard(CardSuit.SPADE, 1);

    assertEquals(card2.hashCode(), myCard.hashCode());
  }

  /**
   * Tests the hash of two playing cards are not equal
   */
  @Test
  public void TestHashNotEqual() {
    PlayingCard card2 = new PlayingCard(CardSuit.SPADE, 2);

    assertNotEquals(card2.hashCode(), myCard.hashCode());
  }

  /**
   * Tests for invalid value entered to playing card constructor
   * Invalid value is a negative value
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestNegativeValue() {
    PlayingCard card = new PlayingCard(CardSuit.SPADE, -1);
  }

  /**
   * Tests for invalid value entered to playing card constructor
   * Invalid value is a greater than the appropriate value.
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestOutsideRangeValue() {
    PlayingCard card = new PlayingCard(CardSuit.SPADE, 14);
  }

}
