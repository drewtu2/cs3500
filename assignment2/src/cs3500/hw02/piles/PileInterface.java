package cs3500.hw02.piles;

import cs3500.hw02.cards.PlayingCard;
import java.util.List;

/**
 * This is the interface for a Pile.
 */
public interface PileInterface {

  /**
   * Adds a given card to the top of the pile if it is a valid move. If it is an invalid operation,
   * then the card throws an IllegalArgumentException
   *
   * @param inputCard The playing card to add to the pile.
   */
  void addToPile(PlayingCard inputCard) throws IllegalStateException;

  /**
   * Returns the cards starting at index to the top of the deck as a list. (Bottom is index 0)
   *
   * @return the requested PlayingCard
   * @throws IllegalStateException if the index is not the top card
   */
  PlayingCard popCard(int index) throws IllegalStateException;

  /**
   * Returns a string representing the state of the the pile.
   *
   * @param index the index this pile is in a list of piles
   */
  String toString(int index);
}
