package cs3500.hw02;

import cs3500.hw02.cards.CardSuit;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;
import cs3500.hw02.piles.PileFactory;
import cs3500.hw02.piles.PileInterface;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;


public class FreecellModel implements FreecellOperations<PlayingCard> {

  private List<PileInterface> foundationPiles;
  private List<PileInterface> openPiles;
  private List<PileInterface> cascadePiles;

  private boolean gameStarted;

  private static final int C_MIN_CASCADE_PILES = 4;
  private static final int C_MIN_OPEN_PILES = 1;
  private static final int C_NUM_FOUNDATION_PILES = 4;

  /**
   * Constructs a default Freecell Model.
   */
  public FreecellModel() {
    gameStarted = false;
    foundationPiles = null;
    openPiles = null;
    cascadePiles = null;

  }

  @Override
  public List<PlayingCard> getDeck() {
    List<PlayingCard> myList = new ArrayList<>();

    for (CardValue value : CardValue.values()) {
      for (CardSuit suit : CardSuit.values()) {
        myList.add(new PlayingCard(suit, value));
      }
    }

    return myList;
  }

  @Override
  public void startGame(List<PlayingCard> deck, int numCascadePiles, int numOpenPiles,
      boolean shuffle)
      throws IllegalArgumentException {

    // Validate inputs
    if (!isDeckValid(deck)) {
      throw new IllegalArgumentException("Invalid Deck");
    }
    if (numCascadePiles < C_MIN_CASCADE_PILES) {
      throw new IllegalArgumentException("Need at least " + C_MIN_CASCADE_PILES + " cascade piles");
    }
    if (numOpenPiles < C_MIN_OPEN_PILES) {
      throw new IllegalArgumentException("Need at least " + C_MIN_OPEN_PILES + " open piles");
    }

    // Mark the game as started.
    gameStarted = true;

    // Create our piles
    foundationPiles = PileFactory.makeListOfPiles(PileType.FOUNDATION, C_NUM_FOUNDATION_PILES);
    openPiles = PileFactory.makeListOfPiles(PileType.OPEN, numOpenPiles);

    // Shuffle the Deck as necessary
    if (shuffle) {
      deck = shuffle(deck);
    }

    List<List<PlayingCard>> listOfDealtCards = dealRoundRobin(deck, numCascadePiles);
    cascadePiles = PileFactory.makeListOfPiles(PileType.CASCADE, listOfDealtCards);
  }

  /**
   * Deal cards in a round robin fashion across all cascade piles.
   */
  private List<List<PlayingCard>> dealRoundRobin(List<PlayingCard> myDeck, int numCascadePiles) {
    int counter = 0;
    int index;
    List<List<PlayingCard>> deltPiles = new ArrayList<>();

    for (PlayingCard card : myDeck) {
      index = counter % numCascadePiles;

      // We need to create this list first, then add the card
      if (index == deltPiles.size()) {
        deltPiles.add(new ArrayList<>());
      }

      deltPiles.get(index).add(card);
      ++counter;
    }

    return deltPiles;
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalStateException {
    PileInterface sourcePile = getPile(source, pileNumber);
    PileInterface destinationPile = getPile(destination, destPileNumber);

    PlayingCard selectedCard = sourcePile.popCard(cardIndex);
    destinationPile.addToPile(selectedCard);


  }

  /**
   * Returns the requested pile.
   *
   * @param type The type of pile that is being requested
   * @param pileNumber The pile number that is being requested
   * @return the requested pile interface
   */
  private PileInterface getPile(PileType type, int pileNumber)
      throws IllegalStateException, IllegalArgumentException {
    try {
      switch (type) {
        case FOUNDATION:
          return foundationPiles.get(pileNumber);
        case CASCADE:
          return cascadePiles.get(pileNumber);
        case OPEN:
          return openPiles.get(pileNumber);
        default:
          throw new IllegalArgumentException("Invalid requested type");
      }
    } catch (NullPointerException e) {
      throw new IllegalStateException();
    } catch (IndexOutOfBoundsException e) {
      // If we threw an index out of bounds exception, it means we requested something out of
      // bounds.
      throw new IllegalStateException("That pile doesn't exist");
    }
  }

  @Override
  public boolean isGameOver() {

    for (PileInterface pile : foundationPiles) {
      // If any of the piles are not complete, we break
      if (pile.size() != CardValue.values().length) {
        return false;
      }
    }

    return true;
  }

  @Override
  public String getGameState() {
    if (gameStarted) {
      return loPileToString(foundationPiles)
          + "\n"
          + loPileToString(openPiles)
          + "\n"
          + loPileToString(cascadePiles);
    } else {
      return "";
    }
  }

  /**
   * Returns the string representation of a list of files. No Trailing newline char.
   *
   * @param loPile The list of piles to print
   * @return String representation of the list of pile.
   */
  private String loPileToString(List<PileInterface> loPile) {
    StringBuilder myBuilder = new StringBuilder();
    int count = 0;
    for (PileInterface pile : loPile) {
      myBuilder.append(pile.toString(count));
      myBuilder.append("\n");
      ++count;
    }
    // Remove the trailing newline.
    myBuilder.deleteCharAt(myBuilder.length() - 1);

    return myBuilder.toString();
  }

  /**
   * Returns a boolean if the deck is valid. A deck is invalid if a card is missing or if a card
   * occurs more than once.
   *
   * @returns true if the deck is valid
   */
  private boolean isDeckValid(List<PlayingCard> inputDeck) {

    int first_instance = 0;
    int last_instance = 0;
    PlayingCard testCard;

    if (inputDeck == null) {
      return false;
    }

    for (CardValue value : CardValue.values()) {
      for (CardSuit suit : CardSuit.values()) {
        testCard = new PlayingCard(suit, value);
        first_instance = inputDeck.indexOf(testCard);
        last_instance = inputDeck.lastIndexOf(testCard);

        // At any point, if a card
        // - is missing, the deck is invalid.
        // - if a card appears more than once, the deck is invalid
        if (first_instance == -1 || first_instance != last_instance) {
          return false;
        }
      }
    }

    // If we didn't fail out anywhere, continue...
    return true;
  }

  /**
   * Takes a deck and returns the deck in a randomized order.
   *
   * @param inputDeck A List of cards to mix
   * @return a deck with all of the same cards in a different order
   */
  private List<PlayingCard> shuffle(List<PlayingCard> inputDeck) {
    List<PlayingCard> shuffledList = new ArrayList<>();
    Random rand = new Random();

    int max;
    int index;

    // Generate a random number from 0 to size of inputDeck
    int sizeOfDeck = inputDeck.size();

    for (int i = 0; i < sizeOfDeck; ++i) {
      max = inputDeck.size();
      index = rand.nextInt(max);

      shuffledList.add(inputDeck.get(index));
      inputDeck.remove(index);
    }

    return shuffledList;
  }
}
