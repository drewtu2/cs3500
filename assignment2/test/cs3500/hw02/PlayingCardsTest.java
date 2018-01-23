package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

  }

  /**
   * Tests two playing cards are equal
   */
  @Test
  public void TestEquals() {

  }

  /**
   * Tests two playing cards are not equal
   */
  @Test
  public void TestNotEqual() {

  }

  /**
   * Tests the hash of two playing cards are equal
   */
  @Test
  public void TestHashEqual() {

  }

  /**
   * Tests the hash of two playing cards are not equal
   */
  @Test
  public void TestHashNotEqual() {

  }

  /**
   * Tests for invalid value entered to playing card constructor
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestInvalidValue() {

  }

  /**
   * Tests for invalid suit entered to playing card constructor
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestInvalidSuit() {

  }

}
