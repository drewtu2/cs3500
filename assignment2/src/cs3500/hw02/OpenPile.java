package cs3500.hw02;

import java.util.Stack;

public class OpenPile extends AbstractPile {

  public OpenPile() {
    pile = new Stack<>();
    type = PileType.OPEN;
  }

  /**
   * An open pile may contain at most one card. An open pile is usually used as a temporary buffer
   * during the game to hold cards
   *
   * @param inputCard The card being inserted.
   * @return Returns true if the list is currently empty.
   */
  @Override
  protected boolean validAddition(PlayingCard inputCard) {
    return pile.empty();
  }
}
