package cs3500.hw03.moveSequence;

import cs3500.hw02.PileType;
import cs3500.hw03.inputScanner.IInputScanner;

public class MoveSequence implements IMoveSequence {

  private PileType sourceType;
  private int sourceIndex;
  private int cardIndex;
  private PileType destinationType;
  private int destinationIndex;
  private boolean quitSequence;

  /**
   * Constructs a MoveSequence.
   *
   * @param sourceType the type of pile to move it from
   * @param sourceIndex which pile to move it from
   * @param cardIndex the index of the card in the pile
   * @param destinationType the type of pile to move it to
   * @param destinationIndex the index of the pile to move it to
   * @param quit true if this pile is a quit sequence
   */
  private MoveSequence(PileType sourceType, int sourceIndex, int cardIndex,
      PileType destinationType, int destinationIndex, boolean quit) {

    this.sourceType = sourceType;
    this.sourceIndex = sourceIndex;
    this.cardIndex = cardIndex;
    this.destinationType = destinationType;
    this.destinationIndex = destinationIndex;
    this.quitSequence = quit;
  }

  /**
   * Reads the inputs from the input scanner and builds a MoveSequence. If a quit character is
   * detected, a quit move sequence is returned.
   *
   * @param inputScanner the input to read the sequence from
   * @return a MoveSequence
   */
  public static MoveSequence readMoveSequence(IInputScanner inputScanner) {
    String sourcePile;
    String cardIndex;
    String destinationPile;

    sourcePile = inputScanner.getPileToken();
    if (quitToken(sourcePile)) {
      return genQuitSequence();
    }

    cardIndex = inputScanner.getIndexToken();
    if (quitToken(cardIndex)) {
      return genQuitSequence();
    }

    destinationPile = inputScanner.getPileToken();
    if (quitToken(destinationPile)) {
      return genQuitSequence();
    }

    PileType sourceType = extractType(sourcePile);
    int sourceIndex = extractIndex(sourcePile);
    int cIndex = Integer.parseInt(cardIndex) - 1;
    PileType destinationType = extractType(destinationPile);
    int destinationIndex = extractIndex(destinationPile);

    return new MoveSequence(sourceType, sourceIndex, cIndex, destinationType, destinationIndex,
        false);
  }

  @Override
  public PileType sourcePileType() {
    if (this.quitSequence) {
      throw new IllegalStateException();
    }
    return this.sourceType;
  }

  @Override
  public int sourcePileIndex() {
    if (this.quitSequence) {
      throw new IllegalStateException();
    }
    return this.sourceIndex;
  }

  @Override
  public int cardIndex() {
    if (this.quitSequence) {
      throw new IllegalStateException();
    }
    return this.cardIndex;
  }

  @Override
  public PileType destinationPileType() {
    if (this.quitSequence) {
      throw new IllegalStateException();
    }
    return this.destinationType;
  }

  @Override
  public int destinationIndex() {
    if (this.quitSequence) {
      throw new IllegalStateException();
    }
    return this.destinationIndex;
  }

  @Override
  public boolean isQuit() {
    return this.quitSequence;
  }


  /**
   * Returns true if the given token is a quit character. Q or q.
   *
   * @param token the token to test
   * @return true if the given token is a quit character
   */
  private static boolean quitToken(String token) {
    return token.toLowerCase().equals("q");
  }

  /**
   * Returns a valid quit sequence initialize with dummy values for all other fields.
   *
   * @return a quit sequence
   */
  public static MoveSequence genQuitSequence() {
    return new MoveSequence(PileType.FOUNDATION, 1, 1, PileType.FOUNDATION, 1, true);
  }

  /**
   * Returns the pile type of a given string where the string is in valid pile format.
   *
   * @return the PileType of a string representation
   * @throws IllegalStateException on invalid input
   */
  private static PileType extractType(String input) {
    String indicator = input.substring(0, 1).toLowerCase();

    switch (indicator) {
      case "f":
        return PileType.FOUNDATION;
      case "o":
        return PileType.OPEN;
      case "c":
        return PileType.CASCADE;
      default:
        throw new IllegalArgumentException("Invalid Input");
    }
  }

  /**
   * Returns the index of a given string where the string is in valid pile format.
   *
   * @return the PileType of a string representation
   */
  private static int extractIndex(String input) {
    String string_int = input.substring(1);

    return Integer.parseInt(string_int) - 1;

  }

}
