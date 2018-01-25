package cs3500.hw02;

import java.util.Stack;

public class FoundationPile extends AbstractPile{
  CardSuit pileSuit;
  public FoundationPile(CardSuit suit) {
    pile = new Stack<>();
    type = PileType.FOUNDATION;
    pileSuit = suit;
  }

  /**
   * A card can be added to a foundation pile if and only if its suit matches that of the pile, and
   * its value is one more than that of the card currently on top of the pile (i.e. the next card
   * in foundation pile 2 in the figure above can only be 3♣). If a foundation pile is currently
   * empty, any ace can be added to it: there is no required ordering of suits in the foundation
   * piles.
   * @param inputCard The card to add
   * @return True if the card can be added to the pile.
   */
  @Override
  protected boolean validAddition(PlayingCard inputCard) {

    boolean correctSuit = inputCard.suitIs(pileSuit);
    boolean correctValue = inputCard.isAfter(pile.peek());

    return correctSuit && correctValue;
  }
}
