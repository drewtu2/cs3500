package cs3500.hw03;

import cs3500.hw02.FreecellOperations;
import java.util.List;
import java.io.*;

public interface IFreecellController<K> {

  /**
   * This method should play a new game of Freecell using the provided model, number of cascade and
   * open piles and the provided deck. If “shuffle” is set to false, the deck must be used as-is,
   * else the deck should be shuffled. To do so, it should ask the provided model to start a game
   * with the provided parameters, and then “run” the game in the following sequence until the game
   * is over: Transmit game state to the Appendable object exactly as the model provides it. If the
   * game is ongoing, wait for user input from the Readable object. A valid user input for a move is
   * a sequence of three inputs (separated by spaces or newlines), asked one at a time: The source
   * pile (e.g., "C1", as a single word). The pile number begins at 1, so that it is more
   * human-friendly. The card index, again with the index beginning at 1. The destination pile
   * (e.g., "F2", as a single word). The pile number is again counted from 1. The controller will
   * parse these inputs and pass the information on to the model to make the move. See below for
   * more detail. If the game has been won, the method should transmit the final game state, and a
   * message "Game over." on a new line and return.
   *
   * @param deck a complete list of playing cards
   * @param model the type of freecell to be played
   * @param numCascades the number of cascade piles
   * @param numOpens the number of open piles
   * @param shuffle a boolean representing whether the deck should be shuffled
   * @throws IllegalArgumentException if the model or deck are null.
   * @throws IOException if the controller is unable to successfully receive input or transmit
   * output
   */
  void playGame(List<K> deck, FreecellOperations<K> model, int numCascades,
      int numOpens, boolean shuffle) throws IllegalArgumentException, IOException;

}
