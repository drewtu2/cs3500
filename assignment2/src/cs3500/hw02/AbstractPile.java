package cs3500.hw02;

import java.util.List;
import java.util.Stack;
import java.util.Collections;

public abstract class AbstractPile implements PileInterface {

  /**
   * The stack stores the data of a pile. Index 0 represents the bottom of the pile.
   */
  Stack<PlayingCard> pile;
  PileType type;

  @Override
  public void addToPile(PlayingCard inputCard) throws IllegalArgumentException {
    if (validAddition(inputCard)) {
      pile.push(inputCard);
    } else {
      throw new IllegalArgumentException("Card cannot be added");
    }
  }

  @Override
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


  /**
   * Returns true if the given card can be added to the current state of the pile.
   *
   * @param inputCard The card to add
   * @return returns true if the given card can be added.
   */
  protected abstract boolean validAddition(PlayingCard inputCard);
}
