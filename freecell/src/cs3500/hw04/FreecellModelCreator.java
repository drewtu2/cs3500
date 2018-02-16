package cs3500.hw04;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.cards.PlayingCard;

/**
 * A Factory Class for creating objects of type FreecellOperations.
 */
public class FreecellModelCreator {

  public enum GameType {
    SINGLEMOVE,
    MULTIMOVE
  }

  /**
   * Creates a model of the given type.
   *
   * @param type a model of a given game type
   * @return a model of the requested type.
   */
  public static FreecellOperations<PlayingCard> create(GameType type) {
    switch (type) {
      case MULTIMOVE:
        return new FreecellModelMulti();
      case SINGLEMOVE:
        return new FreecellModel();
      default:
        throw new IllegalArgumentException("Bad GameType");
    }
  }

}
