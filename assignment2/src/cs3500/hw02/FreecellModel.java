package cs3500.hw02;

import cs3500.hw02.cards.CardSuit;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;
import cs3500.hw02.piles.CascadePile;
import cs3500.hw02.piles.FoundationPile;
import cs3500.hw02.piles.OpenPile;
import cs3500.hw02.piles.PileInterface;
import cs3500.hw02.piles.PileType;
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

public class FreecellModel implements FreecellOperations<PlayingCard> {
  List<FoundationPile> FoundationPiles;
  List<OpenPile> OpenPiles;
  List<CascadePile> CascadePiles;
  boolean gameStarted;

  /**
   * Constructs a default Freecell Model
   */
  public FreecellModel() {

  }

  @Override
  public List<PlayingCard> getDeck() {
    List<PlayingCard> myList = new Stack<>();

    for (CardValue value : CardValue.values()) {
      for (CardSuit suit : CardSuit.values()) {
        myList.add(new PlayingCard(suit, value));
      }
    }

    return myList;
  }

  @Override
  public void startGame(List<PlayingCard> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
  throws IllegalArgumentException {

  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException {

  }

  @Override
  public boolean isGameOver() {
    boolean gameOver = true;

    for (FoundationPile pile : FoundationPiles) {
      gameOver = gameOver && pile.isComplete();

      // If any of the piles are not complete, we break
      if (!gameOver) {
        break;
      }
    }

    return gameOver;
  }

  @Override
  public String getGameState() {
    if (gameStarted) {
      StringBuilder myBuilder = new StringBuilder();
      myBuilder.append(FoundationPiles.toString());
      myBuilder.append("\n");
      myBuilder.append(OpenPiles.toString());
      myBuilder.append("\n");
      myBuilder.append(CascadePiles.toString());

      return myBuilder.toString();
    } else {
      return "";
    }
  }

  /**
   * Returns the string representation of a list of files. No Trailing newline char.
   * @param loPile The list of piles to print
   * @return String representation of the list of pile.
   */
  private String loPileToString(List<PileInterface> loPile) {
    StringBuilder myBuilder = new StringBuilder();
    int count = 0;
    for (PileInterface pile : loPile) {
      myBuilder.append(pile.toString(count));
      ++count;
    }
    // Remove the trailing newline.
    myBuilder.deleteCharAt(myBuilder.length() - 1);

    return myBuilder.toString();
  }

  /**
   * Returns a boolean
   * @param inputDeck
   * @return
   */
  private boolean isDeckValid(List<PlayingCard> inputDeck) {

    return true;
  }

  /**
   * Takes a deck and returns the deck in a randomized order
   * @param inputDeck   A List of cards to mix
   * @return a deck with all of the same cards in a different order
   */
  private List<PlayingCard> shuffle(List<PlayingCard> inputDeck) {
    List<PlayingCard> shuffledList = new ArrayList<>();

    return shuffledList;
  }
}
