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
   * Unconditionally adds card to the top of the pile.
   *
   * @param card the card to add
   */
  void unconditionalAdd(PlayingCard card);

  /**
   * Returns the cards starting at index to the top of the deck as a list. (Bottom is index 0)
   * Removes the cards from the pile. (Updated for homewokr 4 to return a list of cards instead of
   * just a single card)
   *
   * @return the requested PlayingCard
   * @throws IllegalArgumentException if the index is not the top card
   */
  List<PlayingCard> popCard(int index) throws IllegalArgumentException;

  /**
   * Returns the cards starting at index to the top of the deck as a list. (Bottom is index 0) Does
   * not remove pile. (Added for homework 4 so that order could be accessed without removing cards)
   *
   * @return the requested PlayingCard
   * @throws IllegalArgumentException if the index is not the top card
   */
  List<PlayingCard> peekBuild(int index) throws IllegalArgumentException;

  /**
   * Returns a string representing the state of the the pile.
   *
   * @param index the index this pile is in a list of piles
   * @return the string representation of the pile.
   */
  String toString(int index);

  /**
   * Returns the pile size.
   *
   * @return the size of the pile
   */
  int size();

  /**
   * Returns true if the given card can be added to the current state of the pile. (Added to
   * interface from homework3)
   *
   * @param inputCard The card to add
   * @return returns true if the given card can be added.
   */
  boolean validAddition(PlayingCard inputCard);
}
