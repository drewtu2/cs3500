package cs3500.hw02.piles;


import cs3500.hw02.cards.PlayingCard;
import java.util.ArrayList;
import java.util.List;
import cs3500.hw02.PileType;

public class PileFactory {

  /**
   * Returns a PileInterface object of a given type. This should be used with FOUNDATION or OPEN
   * pile types
   *
   * @param type the type of pile to create where type is either a FOUNDATION or OPEN Type
   * @return a PileInterface object of the given type
   */
  public static PileInterface defaultConstructor(PileType type)
      throws UnsupportedOperationException {
    PileInterface myInterface;

    switch (type) {
      case FOUNDATION:
        myInterface = new FoundationPile();
        break;
      case OPEN:
        myInterface = new OpenPile();
        break;
      default:
        throw new UnsupportedOperationException("Can only instantiate FOUNDATION and OPEN types");

    }
    return myInterface;
  }

  /**
   * Returns a PileInterface object of a given type. This should be used with CASCADE Pile Types
   *
   * @param type the type of pile to create where type is a CASCADE
   * @return a PileInterface object of the given type
   */
  public static PileInterface defaultConstructor(PileType type, List<PlayingCard> initialCards) {
    PileInterface myInterface;

    if (type == PileType.CASCADE) {
      myInterface = new CascadePile(initialCards);
    } else {
      throw new UnsupportedOperationException("Can only instantiate CASCADE Types");
    }

    return myInterface;
  }

  /**
   * Returns a list of piles of a given type and number.
   *
   * @param type type of piles to return
   * @param numPiles number of piles in list to return
   * @return the list of piles
   */
  public static List<PileInterface> makeListOfPiles(PileType type, int numPiles) {
    List<PileInterface> myPiles = new ArrayList<>();
    for (int ii = 0; ii < numPiles; ++ii) {
      myPiles.add(defaultConstructor(type));
    }

    return myPiles;
  }

  /**
   * Returns a list of piles of a given type and number where each pile has been initialized with a
   * set of intial cards. The number of piles returned in the list will be equal to the number of
   * lists given.
   *
   * @param type type of piles to return
   * @param listOfInitalCards a list of lists of initial cards
   * @return the list of piles
   */
  public static List<PileInterface> makeListOfPiles(PileType type,
      List<List<PlayingCard>> listOfInitalCards) throws UnsupportedOperationException {
    List<PileInterface> myPiles = new ArrayList<>();

    if (type != PileType.CASCADE) {
      throw new UnsupportedOperationException("Only valid for CASCADE piles");
    }

    for (List<PlayingCard> initialCards : listOfInitalCards) {
      myPiles.add(defaultConstructor(type, initialCards));
    }

    return myPiles;
  }

}
