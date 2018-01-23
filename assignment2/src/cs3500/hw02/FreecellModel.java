package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

public class FreecellModel implements FreecellOperations<PlayingCard> {

  /**
   * Constructs a default Freecell Model
   */
  public FreecellModel() {

  }

  @Override
  public List<PlayingCard> getDeck() {
    List<PlayingCard> myList = new ArrayList<>();
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
    return false;
  }

  @Override
  public String getGameState() {

    return "";
  }
}
