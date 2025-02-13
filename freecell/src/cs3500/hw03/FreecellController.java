package cs3500.hw03;

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.cards.PlayingCard;
import cs3500.hw03.inputscanner.IInputScanner;
import cs3500.hw03.inputscanner.InputScanner;
import cs3500.hw03.movesequence.IMoveSequence;
import cs3500.hw03.movesequence.MoveSequence;
import java.io.IOException;
import java.util.List;

/**
 * Concrete implementation of an IFreecellController interface.
 */
public class FreecellController implements IFreecellController<PlayingCard> {

  private Appendable outputStream;
  private IInputScanner inputScanner;

  /**
   * Default constructor for a FreecellController.
   *
   * @throws IllegalArgumentException if rd or ap is null
   */
  public FreecellController(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Null inputs");
    }
    outputStream = ap;
    inputScanner = new InputScanner(rd);
  }

  @Override
  public void playGame(List<PlayingCard> deck, FreecellOperations<PlayingCard> model,
      int numCascades, int numOpens, boolean shuffle) throws IOException {

    IMoveSequence moveSequence = MoveSequence.genQuitSequence();

    // Validate Inputs
    //validatePlayGameInputs(deck, model);
    if (deck == null || model == null) {
      throw new IllegalArgumentException("Null inputs");
    }

    // Start game with provided parameters
    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (Exception e) {
      outputStream.append("Could not start game.");
      return;
    }

    // While game is not over
    while (!model.isGameOver()) {

      // Transmit game state to appendable
      outputStream.append(model.getGameState());
      outputStream.append("\n");

      // Wait on valid user input
      moveSequence = MoveSequence.readMoveSequence(inputScanner);

      // If this is a quit sequence, break
      if (moveSequence.isQuit()) {
        break;
      }

      try {
        // Send data to the model
        model.move(moveSequence.sourcePileType(),
            moveSequence.sourcePileIndex(),
            moveSequence.cardIndex(),
            moveSequence.destinationPileType(),
            moveSequence.destinationIndex());
      } catch (IllegalArgumentException e) {
        // Catch any exceptions
        outputStream.append("Invalid Move. Try Again. Bad Inputs.\n");
      } catch (IllegalStateException e) {
        outputStream.append("Invalid Move. Try Again. Game not started.\n");
      }
    }

    // If detected a quit command
    if (moveSequence.isQuit()) {
      outputStream.append("Game quit prematurely.\n");
    }

    // If game over
    if (model.isGameOver()) {
      // transmit final game state
      outputStream.append(model.getGameState());
      outputStream.append("\n");

      // Print "Game over"
      outputStream.append("Game over.\n");
    }
  }

  ///**
  // * Throws an IllegalArgumentException in the case that the deck or model is invalid.
  // */
  //private void validatePlayGameInputs(List<PlayingCard> deck,
  //    FreecellOperations<PlayingCard> model) {
  //  if (deck == null) {
  //    throw new IllegalArgumentException("Deck cannot be null");
  //  } else if (model == null) {
  //    throw new IllegalArgumentException("Model cannot be null");
  //  }
  //}


}
