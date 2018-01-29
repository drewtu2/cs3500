import cs3500.hw02.cards.CardSuit;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class PlayingCardTest {
  private PlayingCard AceOfSpades;
  private PlayingCard AceOfHearts;

  @Before
  public void initCond() {
    AceOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.ACE);
    AceOfHearts = new PlayingCard(CardSuit.HEART, CardValue.ACE);
  }

  @Test
  public void TestString() {
    assertEquals("A♠", AceOfSpades.toString());
  }

  @Test
  public void TestEquals() {
    assertEquals(AceOfSpades, AceOfSpades);
  }

  @Test
  public void TestNotEquals() {
    assertNotEquals(AceOfHearts, AceOfSpades);
  }

  @Test
  public void TestHashEquals() {
    assertEquals(AceOfSpades.hashCode(), AceOfSpades.hashCode());
  }

  @Test
  public void TestHashNotEquals() {
    assertNotEquals(AceOfHearts.toString(), AceOfSpades.toString());
  }

  @Test
  public void TestSameSuit() {
    assertEquals(true, AceOfSpades.sameSuitAs(AceOfSpades));
  }

  @Test
  public void TestNotSameSuit() {
    assertEquals(false, AceOfSpades.sameSuitAs(AceOfHearts));
  }

  @Test
  public void TestValueIs() {
    assertEquals(true, AceOfSpades.valueIs(CardValue.ACE));
  }

  @Test
  public void TestNotValueIs() {
    assertEquals(false, AceOfSpades.valueIs(CardValue.TWO));
  }

  @Test
  public void TestOneGreater() {
    PlayingCard TwoOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.TWO);
    assertEquals(true, TwoOfSpades.isOneGreater(AceOfSpades));

    PlayingCard QueenOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.QUEEN);
    PlayingCard KingOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.KING);
    assertEquals(true, KingOfSpades.isOneGreater(QueenOfSpades));


  }

  @Test
  public void TestNotOneGreater() {
    PlayingCard ThreeOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.THREE);
    PlayingCard TwoOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.TWO);

    assertEquals(false, ThreeOfSpades.isOneGreater(AceOfSpades));
    assertEquals(false, TwoOfSpades.isOneGreater(ThreeOfSpades));

    PlayingCard KingOfSpades = new PlayingCard(CardSuit.SPADE, CardValue.KING);
    assertEquals(false, KingOfSpades.isOneGreater(AceOfSpades));
    assertEquals(false, AceOfSpades.isOneGreater(KingOfSpades));
  }

  @Test
  public void TestDifferentColor() {
    PlayingCard AceOfDiamonds = new PlayingCard(CardSuit.DIAMOND, CardValue.ACE);

    assertEquals(true, AceOfSpades.differentColor(AceOfHearts));
    assertEquals(true, AceOfSpades.differentColor(AceOfDiamonds));
  }

  @Test
  public void TestNotDifferentColor() {
    PlayingCard AceOfClubs = new PlayingCard(CardSuit.CLUB, CardValue.ACE);

    assertEquals(false, AceOfSpades.differentColor(AceOfClubs));
    assertEquals(false, AceOfSpades.differentColor(AceOfSpades));
  }

  @Test
  public void TestGetSuit() {
    assertEquals(CardSuit.SPADE, AceOfSpades.getSuit());
    assertEquals(CardSuit.HEART, AceOfHearts.getSuit());
  }

  @Test
  public void TestNotGetSuit() {
    assertNotEquals(CardSuit.HEART, AceOfSpades.getSuit());
    assertNotEquals(CardSuit.SPADE, AceOfHearts.getSuit());
  }

  @Test
  public void TestGetValue() {
    assertEquals(CardValue.ACE, AceOfSpades.getValue());
  }

  @Test
  public void TestNotGetValue() {
    assertNotEquals(CardValue.TWO, AceOfHearts.getValue());
  }
}
