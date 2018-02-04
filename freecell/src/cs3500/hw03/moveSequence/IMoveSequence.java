package cs3500.hw03.moveSequence;

import cs3500.hw02.PileType;
import cs3500.hw03.inputScanner.IInputScanner;

public interface IMoveSequence {

  /**
   * Returns the pile type of the source.
   * @return the pile type of the destination
   * @throws IllegalStateException if called when the sequence is a quit sequence
   */
  PileType sourcePileType() throws IllegalStateException;

  /**
   * Returns the index of the source pile.
   * @return the index of the source pile
   * @throws IllegalStateException if called when the sequence is a quit sequence
   */
  int sourcePileIndex() throws IllegalStateException;

  /**
   * Returns the index of the card pile from the sequence.
   * @return the index of the playing card
   * @throws IllegalStateException if called when the sequence is a quit sequence
   */
  int cardIndex() throws IllegalStateException;

  /**
   * Returns the pile type of the destination.
   * @return the pile type of the destination.
   * @throws IllegalStateException if called when the sequence is a quit sequence
   */
  PileType destinationPileType() throws IllegalStateException ;

  /**
   * Returns the index of the destination pile.
   * @return the index of the destination pile
   * @throws IllegalStateException if called when the sequence is a quit sequence
   */
  int destinationIndex() throws IllegalStateException;

  /**
   * Returns a boolean if the sequence represents a quit sequence
   * @return true if the sequence represents a quit sequence
   */
  boolean isQuit();
}
