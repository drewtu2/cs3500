import cs3500.hw02.cards.PlayingCard;
import cs3500.hw02.piles.CascadePile;
import cs3500.hw02.piles.FoundationPile;
import cs3500.hw02.piles.OpenPile;
import cs3500.hw02.piles.PileFactory;
import cs3500.hw02.piles.PileInterface;
import cs3500.hw02.piles.PileType;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPileFactory {

  /**
   * Test to make sure the default constructor works
   */
  @Test
  public void TestDefaultConstructor() {
    PileInterface fpile = PileFactory.defaultConstructor(PileType.FOUNDATION);
    PileInterface cpile = PileFactory.defaultConstructor(PileType.CASCADE);
    PileInterface opile = PileFactory.defaultConstructor(PileType.OPEN);

    assertEquals(fpile.toString(0), new FoundationPile().toString(0));
    assertEquals(cpile.toString(0), new CascadePile().toString(0));
    assertEquals(opile.toString(0), new OpenPile().toString(0));

  }

  /**
   * Tests to make sure the list constructor works
   */
  @Test
  public void TestListConstructor() {
    List<PileInterface> flist = PileFactory.makeListOfPiles(PileType.FOUNDATION, 3);
    List<PileInterface> olist = PileFactory.makeListOfPiles(PileType.OPEN, 3);
    List<PileInterface> clist = PileFactory.makeListOfPiles(PileType.CASCADE, 3);

    assertEquals(3, flist.size());
    for(PileInterface iface : flist) {
      assertEquals(iface.toString(0), new FoundationPile().toString(0));
    }

    assertEquals(3, olist.size());
    for(PileInterface iface : olist) {
      assertEquals(iface.toString(0 ), new OpenPile().toString(0));
    }

    assertEquals(3, clist.size());
    for(PileInterface iface : clist) {
      assertEquals(iface.toString(0), new CascadePile().toString(0));
    }
  }
}
