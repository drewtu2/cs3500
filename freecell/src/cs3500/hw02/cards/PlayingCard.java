package cs3500.hw02.cards;

/**
 * Represents a single playing card in a freecell game. Has a suit and value.
 */
public class PlayingCard {

  private CardSuit suit;
  private CardValue value;

  /**
   * Constructs a playing card with a given suit and value.
   *
   * @param suit The suit of the playing card (ACE, CLUB, HEART, SPADE)
   * @param value The value of the playing card.
   */
  public PlayingCard(CardSuit suit, CardValue value) {
    this.suit = suit;
    this.value = value;
  }

  /**
   * Returns the value followed by the suit of this card.
   *
   * @return the string representation of this card.
   */
  @Override
  public String toString() {
    StringBuilder representation = new StringBuilder();
    representation.append(this.value.toString());
    representation.append(this.suit.toString());

    return representation.toString();
  }

  /**
   * Returns whether another playing card is equal to this playing card.
   *
   * @param a The playing card to compare to.
   * @return Whether the two cards are equal.
   */
  @Override
  public boolean equals(Object a) {
    // First: make sure that given object is of correct type, if its not, then
    // you know they can't be equal.
    if (!(a instanceof PlayingCard)) {
      return false;
    }

    // Now that we know the object is of the same type, we can cast to the desired type.
    // This will give us access to the class functions in order to begin comparing
    // them together.
    PlayingCard other = (PlayingCard) a;

    // Finally, perform a comparision that will assert whether or not the two objects
    // are the same.
    return (this.value == other.value) && (this.suit == other.suit);
    //return this.value == other.value;
  }

  /**
   * The hash value of the playing card is equal to its value.
   *
   * @return the hash value of the playing card.
   */
  @Override
  public int hashCode() {
    StringBuilder myBuilder = new StringBuilder();
    myBuilder.append(value).append(suit);
    return myBuilder.toString().hashCode();
  }

  /**
   * Returns true if the suit of the given card matches the suit of this card.
   *
   * @return true if the suit of the given card matches the suit of this card.
   */
  public boolean sameSuitAs(PlayingCard testCard) {
    return suit.equals(testCard.getSuit());
  }

  /**
   * Returns true if the given value matches the value of this card.
   *
   * @return true if the given value matches the value of this card.
   */
  public boolean valueIs(CardValue testValue) {
    return value.equals(testValue);
  }

  /**
   * Returns true if this card is one greater than the previous card.
   *
   * @return true if this card is one greater than the previous card.
   */
  public boolean isOneGreater(PlayingCard prevCard) {

    // Return False if the previous card is a KING (You can't be higher than a king)
    if (prevCard.getValue() == CardValue.KING) {
      return false;
    }

    return value == prevCard.getValue().next();
  }

  /**
   * Returns true if this card is a different color from the given card.
   *
   * @return true if this card is a different color from the given card.
   */
  public boolean differentColor(PlayingCard testCard) {
    return suit.getCardColor() != testCard.getSuit().getCardColor();
  }

  /**
   * Getter method for the card.
   *
   * @return The suit of the card.
   */
  public CardSuit getSuit() {
    return suit;
  }

  /**
   * Getter method for the card.
   *
   * @return The value of the card.
   */
  public CardValue getValue() {
    return value;
  }
}
