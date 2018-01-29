package cs3500.hw02.piles;

import cs3500.hw02.cards.PlayingCard;
import java.util.Stack;

public class CascadePile extends AbstractPile {

  public CascadePile() {
    pile = new Stack<>();
    type = PileType.CASCADE;

  }

  /**
   * A card from some pile can be moved to the end of a cascade pile if and only if its color is
   * different from that of the currently last card, and its value is exactly one less than that of
   * the currently last card (e.g. in the figure above, the next card in cascade pile 1 can be 4♦ or
   * 4♥ while the next card in cascade pile 3 can be 10♠ or 10♣).
   *
   * @param inputCard The card to add
   * @return true if the card is valid
   */
  @Override
  protected boolean validAddition(PlayingCard inputCard) {
    PlayingCard topCard = pile.peek();

    // Input card is the opposite color
    boolean differentColor = topCard.differentColor(inputCard);
    // Input card is one less than top card
    boolean oneLess = topCard.isOneGreater(inputCard);

    return differentColor && oneLess;
  }

  /**
   * Unconditionally adds cards to the pile
   *
   * @param inputCard the card to add
   */
  public void unconditionalAdd(PlayingCard inputCard) {
    pile.push(inputCard);
  }
}
