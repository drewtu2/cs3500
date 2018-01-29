package cs3500.hw02;

import cs3500.hw02.cards.CardSuit;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;
import cs3500.hw02.piles.CascadePile;
import cs3500.hw02.piles.FoundationPile;
import cs3500.hw02.piles.OpenPile;
import cs3500.hw02.piles.PileFactory;
import cs3500.hw02.piles.PileInterface;
import cs3500.hw02.piles.PileType;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;


public class FreecellModel implements FreecellOperations<PlayingCard> {

  private List<PileInterface> FoundationPiles;
  private List<PileInterface> OpenPiles;
  private List<PileInterface> CascadePiles;

  private boolean gameStarted;

  private static final int C_MIN_CASCADE_PILES = 4;
  private static final int C_MIN_OPEN_PILES = 1;
  private static final int C_NUM_FOUNDATION_PILES = 4;

  /**
   * Constructs a default Freecell Model TODO: Write Constructor
   */
  public FreecellModel() {
    gameStarted = false;
    FoundationPiles = null;
    OpenPiles = null;
    CascadePiles = null;

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
    FoundationPiles = PileFactory.makeListOfPiles(PileType.FOUNDATION, C_NUM_FOUNDATION_PILES);
    OpenPiles = PileFactory.makeListOfPiles(PileType.OPEN, numOpenPiles);
    CascadePiles = PileFactory.makeListOfPiles(PileType.CASCADE, numCascadePiles);

    // Shuffle the Deck as necessary
    if (shuffle) {
      deck = shuffle(deck);
    }

    dealRoundRobin(deck);
  }

  /**
   * Deal cards in a round robin fashion across all cascade piles
   */
  private void dealRoundRobin(List<PlayingCard> myDeck) {
    int counter = 0;
    int index;
    PileInterface pile;

    for (PlayingCard card : myDeck) {
      index = counter % CascadePiles.size();
      pile = CascadePiles.get(index);
      // TODO: Is there a more elegant way of doing this?
      ((CascadePile) pile).unconditionalAdd(card);
      ++counter;
    }
  }

  // TODO: Do we need to throw illegal argument exceptions if the pile/card doesn't exist?
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalStateException {
    PileInterface sourcePile = getPile(source, pileNumber);
    PileInterface destinationPile = getPile(destination, destPileNumber);

    PlayingCard selectedCard = sourcePile.popCard(cardIndex);
    destinationPile.addToPile(selectedCard);


  }

  /**
   * Returns the requested pile
   *
   * @param type The type of pile that is being requested
   * @param pileNumber The pile number that is being requested
   * @return the requested pile interface
   */
  private PileInterface getPile(PileType type, int pileNumber) {
    switch (type) {
      case FOUNDATION:
        return FoundationPiles.get(pileNumber);
      case CASCADE:
        return CascadePiles.get(pileNumber);
      case OPEN:
        return OpenPiles.get(pileNumber);
      default:
        throw new IllegalArgumentException("Invalid requested type");
    }
  }

  @Override
  public boolean isGameOver() {
    boolean gameOver = true;

    for (PileInterface pile : FoundationPiles) {
      //TODO: Is there a more elegant way of doing this?
      gameOver = ((FoundationPile) pile).isComplete();

      // If any of the piles are not complete, we break
      if (!gameOver) {
        break;
      }
    }

    return gameOver;
  }

  @Override
  public String getGameState() {
    if (gameStarted) {
      return loPileToString(FoundationPiles)
          + "\n"
          + loPileToString(OpenPiles)
          + "\n"
          + loPileToString(CascadePiles);
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
   * Takes a deck and returns the deck in a randomized order
   *
   * @param inputDeck A List of cards to mix
   * @return a deck with all of the same cards in a different order
   */
  private List<PlayingCard> shuffle(List<PlayingCard> inputDeck) {
    List<PlayingCard> shuffledList = new ArrayList<>();
    Random rand = new Random();

    int max, index;

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
