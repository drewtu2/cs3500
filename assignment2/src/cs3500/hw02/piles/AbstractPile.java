package cs3500.hw02.piles;

import cs3500.hw02.cards.PlayingCard;
import java.util.Stack;
import cs3500.hw02.PileType;

public abstract class AbstractPile implements PileInterface {

  /**
   * The stack stores the data of a pile. Index 0 represents the bottom of the pile.
   */
  Stack<PlayingCard> pile;
  PileType type;

  @Override
  public void addToPile(PlayingCard inputCard) throws IllegalStateException {
    if (validAddition(inputCard)) {
      pile.push(inputCard);
    } else {
      throw new IllegalStateException("Card cannot be added");
    }
  }

  @Override
  public PlayingCard popCard(int index) throws IllegalStateException {
    if (index != pile.size() - 1) {
      throw new IllegalStateException("Cannot move this card. Not the top!");
    }

    return pile.pop();
  }

  /*@Override
  public List<PlayingCard> popCards(int index) {
    Stack<PlayingCard> ReturnStack = new Stack<>();

    // Catch the case where the index is out of range.
    if (index >= pile.size()) {
      throw new IllegalArgumentException("Index does not exist!");
    }

    int startingPileSize = pile.size();

    for (int i = 0; i < startingPileSize - index; i++) {
      ReturnStack.push(pile.pop());
    }

    // At the end of the popping, we're left with a stack with the original
    // bottom of card on top. Reverse it so we've essentially pulled the stack
    // off the top.
    Collections.reverse(ReturnStack);

    return ReturnStack;
  }
  */

  /**
   * Prints the pile with bottom of the pile appearing at the left and going to the right. The index
   * is used to indicate which pile this is.
   *
   * @param index The index of the pile
   * @throws IllegalArgumentException on invalid type
   */
  public String toString(int index) throws IllegalArgumentException {
    StringBuilder myBuilder = new StringBuilder();

    switch (type) {
      case OPEN:
        myBuilder.append("O");
        break;
      case CASCADE:
        myBuilder.append("C");
        break;
      case FOUNDATION:
        myBuilder.append("F");
        break;
      default:
        throw new IllegalArgumentException("Invalid type");
    }
    myBuilder.append(index + 1);
    myBuilder.append(":");

    if (pile.empty()) {
      // Do nothing
    } else {
      for (PlayingCard card : pile) {
        myBuilder.append(" ");
        myBuilder.append(card.toString());
        myBuilder.append(",");
      }
      // Delete the last comma
      myBuilder.deleteCharAt(myBuilder.length() - 1);
    }
    //System.out.println(myBuilder.toString());

    return myBuilder.toString();
  }

  @Override
  public int size() {
    return pile.size();
  }

  /**
   * Returns true if the given card can be added to the current state of the pile.
   *
   * @param inputCard The card to add
   * @return returns true if the given card can be added.
   */
  protected abstract boolean validAddition(PlayingCard inputCard);
}
