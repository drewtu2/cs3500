package cs3500.hw02.piles;

import java.util.ArrayList;
import java.util.List;

public class PileFactory {

  /**
   * Returns a PileInterface object of a given type
   *
   * @param type the type of pile to create
   * @return a PileInterface object of the given type
   */
  public static PileInterface defaultConstructor(PileType type) {
    PileInterface myInterface;

    switch (type) {
      case FOUNDATION:
        myInterface = new FoundationPile();
        break;
      case OPEN:
        myInterface = new OpenPile();
        break;
      case CASCADE:
        myInterface = new CascadePile();
        break;
      default:
        throw new IllegalArgumentException("Invalid PileType");

    }
    return myInterface;
  }

  /**
   * Returns a list of piles of a given type and number
   *
   * @param type type of piles to return
   * @param numPiles number of piles in list to return
   * @returns the list of piles
   */
  public static List<PileInterface> makeListOfPiles(PileType type, int numPiles) {
    List<PileInterface> myPiles = new ArrayList<>();
    for (int ii = 0; ii < numPiles; ++ii) {
      myPiles.add(defaultConstructor(type));
    }

    return myPiles;
  }
}
