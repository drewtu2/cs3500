import cs3500.hw02.cards.CardSuit;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class PlayingCardTest {

  private PlayingCard aceOfSpades;
  private PlayingCard aceOfHearts;

  @Before
  public void initCond() {
    aceOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.ACE);
    aceOfHearts = new PlayingCard(CardSuit.HEART, CardValue.ACE);
  }

  @Test
  public void TestString() {
    assertEquals("A♠", aceOfSpades.toString());
  }

  @Test
  public void TestEquals() {
    assertEquals(aceOfSpades, aceOfSpades);
  }

  @Test
  public void TestNotEquals() {
    assertNotEquals(aceOfHearts, aceOfSpades);
  }

  @Test
  public void TestHashEquals() {
    assertEquals(aceOfSpades.hashCode(), aceOfSpades.hashCode());
  }

  @Test
  public void TestHashNotEquals() {
    assertNotEquals(aceOfHearts.toString(), aceOfSpades.toString());
  }

  @Test
  public void TestSameSuit() {
    assertEquals(true, aceOfSpades.sameSuitAs(aceOfSpades));
  }

  @Test
  public void TestNotSameSuit() {
    assertEquals(false, aceOfSpades.sameSuitAs(aceOfHearts));
  }

  @Test
  public void TestValueIs() {
    assertEquals(true, aceOfSpades.valueIs(CardValue.ACE));
  }

  @Test
  public void TestNotValueIs() {
    assertEquals(false, aceOfSpades.valueIs(CardValue.TWO));
  }

  @Test
  public void TestOneGreater() {
    PlayingCard twoOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.TWO);
    assertEquals(true, twoOfSpades.isOneGreater(aceOfSpades));

    PlayingCard queenOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.QUEEN);
    PlayingCard kingOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.KING);
    assertEquals(true, kingOfSpades.isOneGreater(queenOfSpades));


  }

  @Test
  public void TestNotOneGreater() {
    PlayingCard threeOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.THREE);
    PlayingCard twoOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.TWO);

    assertEquals(false, threeOfSpades.isOneGreater(aceOfSpades));
    assertEquals(false, twoOfSpades.isOneGreater(threeOfSpades));

    PlayingCard kingOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.KING);
    assertEquals(false, kingOfSpades.isOneGreater(aceOfSpades));
    assertEquals(false, aceOfSpades.isOneGreater(kingOfSpades));
  }

  @Test
  public void TestDifferentColor() {
    PlayingCard aceOfDiamonds = new PlayingCard(CardSuit.DIAMOND, CardValue.ACE);

    assertEquals(true, aceOfSpades.differentColor(aceOfHearts));
    assertEquals(true, aceOfSpades.differentColor(aceOfDiamonds));
  }

  @Test
  public void TestNotDifferentColor() {
    PlayingCard aceOfClubs = new PlayingCard(CardSuit.CLUB, CardValue.ACE);

    assertEquals(false, aceOfSpades.differentColor(aceOfClubs));
    assertEquals(false, aceOfSpades.differentColor(aceOfSpades));
  }

  @Test
  public void TestGetSuit() {
    assertEquals(CardSuit.SPADE, aceOfSpades.getSuit());
    assertEquals(CardSuit.HEART, aceOfHearts.getSuit());
  }

  @Test
  public void TestNotGetSuit() {
    assertNotEquals(CardSuit.HEART, aceOfSpades.getSuit());
    assertNotEquals(CardSuit.SPADE, aceOfHearts.getSuit());
  }

  @Test
  public void TestGetValue() {
    assertEquals(CardValue.ACE, aceOfSpades.getValue());
  }

  @Test
  public void TestNotGetValue() {
    assertNotEquals(CardValue.TWO, aceOfHearts.getValue());
  }
}
