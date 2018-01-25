package cs3500.hw02;
import java.util.List;

/**
 * This is the interface for a Pile.
 */
public interface PileInterface {

  /**
   * Adds a given card to the top of the pile if it is a valid move. If it is
   * an invalid operation, then the card throws an IllegalArgumentException
   * @param inputCard The playing card to add to the pile.
   * @throws IllegalArgumentException
   */
  void addToPile(PlayingCard inputCard) throws IllegalArgumentException;

  /**
   * Returns the cards starting at index to the top of the deck as a list.
   * (Bottom is index 0)
   * @return a list of PlayingCard objects
   * @throws IllegalArgumentException if the index is out of range
   */
  List<PlayingCard> popCards(int index) throws IllegalArgumentException;
}
