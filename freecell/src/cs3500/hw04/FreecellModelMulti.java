package cs3500.hw04;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;
import cs3500.hw02.cards.PlayingCard;
import cs3500.hw02.piles.PileInterface;
import java.util.List;

/**
 * FreecellModelMulti represents a FreecellModel that can move multiple cards from a stack at once.
 */
public class FreecellModelMulti extends FreecellModel {

  /**
   * Default constructor of FreecellModelMulti just calls the super constructor.
   */
  public FreecellModelMulti() {
    super();
  }

  /**
   * Multi Move obeys the following rules. <ul> <li> multimove is a collection of single valid
   * moves. </li> <li> the maximum number of cards that can be moved when there are N free open
   * piles and K empty cascade piles is (N+1)*2K </li> <li> Cards from index to top should form a
   * valid build, i.e. they should be arranged in alternating colors and consecutive, descending
   * values in the cascade pile that they are moving from. </li> <li> The second condition is the
   * same for any move to a cascade pile: these cards should form a build with the last card in the
   * destination cascade pile.</li> </ul>
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {

    // Error out of the game hasn't started
    if (!super.gameStarted) {
      throw new IllegalStateException("Game hasn't started!");
    }

    // Error out if we can't actually move
    if (!canMove(source, pileNumber, cardIndex, destination, destPileNumber)) {
      throw new IllegalArgumentException("Invalid Move");
    }

    PileInterface sourcePile = super.getPile(source, pileNumber);
    PileInterface destinationPile = super.getPile(destination, destPileNumber);

    List<PlayingCard> build = sourcePile.popCard(cardIndex);

    for (PlayingCard card : build) {
      destinationPile.addToPile(card);
    }

  }

  /**
   * Returns true if all of the following criteria are met. <ul> <li> Number of cards to be moved is
   * less than the maximum cards that can be moved </li> <li> Base card can be placed on top card
   * </li> <li> Cards form a valid build </li> </ul>
   *
   * @param source source pile type
   * @param pileNumber source pileNumber
   * @param cardIndex cardIndex in pile
   * @param destination destination pile type
   * @param destPileNumber destination pile number
   * @return true if the cards can be moved.
   */
  private boolean canMove(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {

    PileInterface myDest = getPile(destination, destPileNumber);
    PileInterface mySrc = getPile(source, pileNumber);

    // We're trying to move more than one card
    if (multi(mySrc, cardIndex)) {

      // False if both piles aren't cascade
      if (!cascade2cascade(source, destination)) {
        return false;
      }

      // False if there aren't enough free spots to execute the move
      if (!possibleMove(mySrc, cardIndex)) {
        return false;
      }

      // The Build given is invalid
      if (!validBuild(mySrc, cardIndex)) {
        return false;
      }
    }

    // The bottom card can be stacked on the destPile
    return myDest.validAddition(mySrc.peekBuild(cardIndex).get(0));
  }

  /**
   * Returns true if more than one card is trying to be moved AND at least one of the piles is not a
   * cascade problem.
   *
   * @param mySrc the source pile
   * @param cardIndex the index or the card requested
   * @return returns true if this move is attempting to move more than one card is not a cascade
   */
  private boolean multi(PileInterface mySrc, int cardIndex) {
    PileInterface sourcePile = mySrc;
    int numCards = sourcePile.size() - cardIndex;

    return (numCards > 1);
  }

  /**
   * Returns true if both source and destination are cascade piles.
   *
   * @param src source pile type
   * @param dest destination pile type
   * @return true if both source and destination are cascade piles
   */
  private boolean cascade2cascade(PileType src, PileType dest) {
    return src == PileType.CASCADE && dest == PileType.CASCADE;
  }

  /**
   * Returns true if there are enough open spots for this move to be possible.
   *
   * @param srcPile the source pile type
   * @param cardIndex cardIndex in pile
   * @return True if move is possible.
   */
  private boolean possibleMove(PileInterface srcPile, int cardIndex) {
    PileInterface sourcePile = srcPile;
    int maxPossible;
    int numCards;
    int n;
    int k;

    n = 0;
    k = 0;

    for (PileInterface pile : super.cascadePiles) {
      if (pile.size() == 0) {
        ++k;
      }
    }

    for (PileInterface pile : super.openPiles) {
      if (pile.size() == 0) {
        ++n;
      }
    }

    numCards = sourcePile.size() - cardIndex;

    // (N+1)*2^K
    // N = Open
    // K = cascade

    maxPossible = (n + 1) * (int) Math.pow(2, k);

    return maxPossible >= numCards;
  }

  /**
   * Returns true if the given index and pile represent a valid build.
   *
   * @param srcPile source pile type
   * @param cardIndex cardIndex in pile
   * @return True if valid build.
   */
  private boolean validBuild(PileInterface srcPile, int cardIndex) {
    PileInterface sourcePile = srcPile;

    if (cardIndex < 0 || cardIndex >= sourcePile.size()) {
      throw new IllegalArgumentException("Index out of bounds");
    }

    List<PlayingCard> build = sourcePile.peekBuild(cardIndex);
    PlayingCard next;
    PlayingCard card;
    boolean iteration_result;

    for (int count = 0; count < build.size() - 2; ++count) {
      card = build.get(count);
      next = build.get(count + 1);

      // Input card is the opposite color
      boolean differentColor = card.differentColor(next);
      // Input card is one less than top card
      boolean oneLess = card.isOneGreater(next);

      iteration_result = differentColor && oneLess;
      if (!iteration_result) {
        return false;
      }
    }

    return true;
  }

}
