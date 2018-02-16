import static org.junit.Assert.assertEquals;

import cs3500.hw02.PileType;
import cs3500.hw02.cards.CardSuit;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;
import cs3500.hw02.piles.CascadePile;
import cs3500.hw02.piles.FoundationPile;
import cs3500.hw02.piles.OpenPile;
import cs3500.hw02.piles.PileFactory;
import cs3500.hw02.piles.PileInterface;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TestPileFactory {

  private List<PlayingCard> initalCards;
  private List<List<PlayingCard>> listOfInitalCards;

  /**
   * Sets up the test bench.
   */
  @Before
  public void initTests() {
    initalCards = new ArrayList<>();
    initalCards.add(new PlayingCard(CardSuit.SPADE, CardValue.ACE));

    listOfInitalCards = new ArrayList<>();
    listOfInitalCards.add(initalCards);
    listOfInitalCards.add(initalCards);
    listOfInitalCards.add(initalCards);

  }

  /**
   * Test to make sure the default constructor works.
   */
  @Test
  public void TestDefaultConstructor() {

    // Test Foundation
    PileInterface fpile = PileFactory.defaultConstructor(PileType.FOUNDATION);
    assertEquals(fpile.toString(0), new FoundationPile().toString(0));

    // Test Open
    PileInterface opile = PileFactory.defaultConstructor(PileType.OPEN);
    assertEquals(opile.toString(0), new OpenPile().toString(0));

    // Test Cascade
    PileInterface cpile = PileFactory.defaultConstructor(PileType.CASCADE, initalCards);
    assertEquals(cpile.toString(0), new CascadePile(initalCards).toString(0));
  }

  /**
   * Tests to make sure the list constructor works.
   */
  @Test
  public void TestListConstructor() {
    List<PileInterface> flist = PileFactory.makeListOfPiles(PileType.FOUNDATION, 3);
    List<PileInterface> olist = PileFactory.makeListOfPiles(PileType.OPEN, 3);
    List<PileInterface> clist = PileFactory.makeListOfPiles(PileType.CASCADE, listOfInitalCards);

    assertEquals(3, flist.size());
    for (PileInterface iface : flist) {
      assertEquals(iface.toString(0), new FoundationPile().toString(0));
    }

    assertEquals(3, olist.size());
    for (PileInterface iface : olist) {
      assertEquals(iface.toString(0), new OpenPile().toString(0));
    }

    assertEquals(3, clist.size());
    for (PileInterface iface : clist) {
      assertEquals(iface.toString(0), new CascadePile(initalCards).toString(0));
    }
  }

  /**
   * Tests to make sure the correct UnsuportedOperation Exceptions are thrown when making piles.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void TestUnsupportedOperationConstructor() {
    PileInterface open = PileFactory.defaultConstructor(PileType.OPEN, initalCards);

    PileInterface foundation = PileFactory.defaultConstructor(PileType.FOUNDATION, initalCards);

    PileInterface cascade = PileFactory.defaultConstructor(PileType.CASCADE);

  }

  /**
   * Tests to make sure the correct UnsuportedOperation Exceptions are thrown when making list of
   * piles.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void TestUnsupportedOperation() {
    List<PileInterface> olist = PileFactory.makeListOfPiles(PileType.OPEN, listOfInitalCards);

    List<PileInterface> flist = PileFactory.makeListOfPiles(PileType.FOUNDATION, listOfInitalCards);

    List<PileInterface> clist = PileFactory.makeListOfPiles(PileType.CASCADE, 3);

  }
}
