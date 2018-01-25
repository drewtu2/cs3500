package cs3500.hw02;

import java.util.Stack;

public class CascadePile extends AbstractPile{
  public CascadePile() {
    pile = new Stack<>();
    type = PileType.CASCADE;

  }

  /**
   * A card from some pile can be moved to the end of a cascade pile if and only if its
   * color is different from that of the currently last card, and its value is exactly one less than
   * that of the currently last card (e.g. in the figure above, the next card in cascade pile 1 can
   * be 4♦ or 4♥ while the next card in cascade pile 3 can be 10♠ or 10♣).
   * @param inputCard The card to add
   * @return true if the card is valid
   */
  @Override
  protected boolean validAddition(PlayingCard inputCard) {
    return false;
  }
}
