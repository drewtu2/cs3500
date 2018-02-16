package cs3500.hw02.piles;

import cs3500.hw02.PileType;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;
import java.util.Stack;

public class FoundationPile extends AbstractPile {

  /**
   * The default constructor for a Foundation Pile.
   */
  public FoundationPile() {
    pile = new Stack<>();
    type = PileType.FOUNDATION;
  }

  /**
   * A card can be added to a foundation pile if and only if its suit matches that of the pile, and
   * its value is one more than that of the card currently on top of the pile (i.e. the next card in
   * foundation pile 2 in the figure above can only be 3♣). If a foundation pile is currently empty,
   * any ace can be added to it: there is no required ordering of suits in the foundation piles. The
   * suit of the pile is set in the case of an ace being added.
   *
   * @param inputCard The card to add
   * @return True if the card can be added to the pile.
   */
  @Override
  public boolean validAddition(PlayingCard inputCard) {
    if (pile.empty()) {
      // If the pile is empty and the card is an ACE, set the pile's suit and return true
      return (inputCard.valueIs(CardValue.ACE));
    } else {

      boolean correctSuit = inputCard.sameSuitAs(pile.peek());
      boolean correctValue = inputCard.isOneGreater(pile.peek());

      return correctSuit && correctValue;
    }
  }
}
