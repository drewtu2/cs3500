package cs3500.hw02.piles;

import cs3500.hw02.PileType;
import cs3500.hw02.cards.PlayingCard;
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
  public boolean validAddition(PlayingCard inputCard) {
    return pile.empty();
  }
}
