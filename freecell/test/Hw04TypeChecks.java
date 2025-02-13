////
//// DO NOT MODIFY THIS FILE
////
//// You don't need to submit it, but you should make sure it compiles.
//// Further explanation appears below.
////


import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;
import cs3500.hw03.FreecellController;
import cs3500.hw03.IFreecellController;
import cs3500.hw04.FreecellModelCreator;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 * This class is provided to check that your code implements the expected API. If your code compiles
 * with an unmodified version of this class, then it very likely will also compile with the tests
 * that we use to evaluate your code.
 */
public class Hw04TypeChecks {

  // This doesn't really need to be a dynamic method, since it doesn't use `this`
  static void checkSignatures() {
    Reader stringReader;
    StringBuffer out;
    FreecellOperations<?> model = FreecellModelCreator.create(
        FreecellModelCreator.GameType
            .MULTIMOVE);

    checkNewModel(
        FreecellModelCreator.create(FreecellModelCreator.GameType
            .MULTIMOVE),
        FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE)
            .getDeck());
    stringReader = new StringReader("C1 8 F1 q");
    out = new StringBuffer();
    checkNewController(
        FreecellModelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE),
        FreecellModelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE)
            .getDeck(),
        new FreecellController(stringReader, out));
    checkNewController(
        FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE),
        FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE)
            .getDeck(),
        new FreecellController(stringReader, out));

  }

  // This doesn't really need to be a dynamic method, since it doesn't use `this`
  static <K> void checkNewController(FreecellOperations<K> model, List<K> deck,
      IFreecellController<K> controller) {
    String input = "4 3";

    try {
      controller.playGame(deck, model, 7, 4, false);
    } catch (IOException e) {
    // Why is this failing....
    }
  }

  static <K> void checkNewModel(FreecellOperations<K> model, List<K> deck) {
    List<K> initialDeck = model.getDeck();
    model.startGame(initialDeck, 7, 4, false);
    model.move(PileType.CASCADE, 0, 7, PileType.OPEN, 0);
    String result = model.getGameState();
    boolean done = model.isGameOver();
  }

  private Hw04TypeChecks() {
    throw new RuntimeException("Don't instantiate this: use it as a reference");
  }
}
