package cs3500.hw02;

public class PlayingCard {

  private CardSuit suit;
  private int value;

  /**
   * Constructs a playing card with a given suit and value.
   * @param suit    The suit of the playing card (ACE, CLUB, HEART, SPADE)
   * @param value   The value of the playing card.
   */
  public PlayingCard(CardSuit suit, int value) {
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
    representation.append(this.value);
    representation.append(" of ");
    representation.append(this.suit);

    return representation.toString();
  }

  /**
   * Returns whether another playing card is equal to this playing card.
   * @param a The playing card to compare to.
   * @return Whether the two cards are equal.
   */
  @Override
  public boolean equals(Object a){
    // First: make sure that given object is of correct type, if its not, then
    // you know they can't be equal.
    if(!(a instanceof PlayingCard)) {
      return false;
    }

    // Now that we know the object is of the same type, we can cast to the desired type.
    // This will give us access to the class functions in order to begin comparing
    // them together.
    PlayingCard other = (PlayingCard)a;

    // Finally, perform a comparision that will assert wether or not the two objects
    // are the same.
    //return (this.value == other.value) && (this.suit == other.suit);
    return this.value == other.value;
  }

  /**
   * The hash value of the playing card is equal to its value.
   * @return the hash value of the playing card.
   */
  @Override
  public int hashCode(){
    StringBuilder myBuilder = new StringBuilder();
    myBuilder.append(value).append(suit);
    return myBuilder.toString().hashCode();
  }

  /**
   * Returns true if the given suit matches the suit of this card.
   *
   */
  public boolean suitIs(CardSuit testSuit) {
    return suit.equals(testSuit);
  }

  /**
   * Returns true if this card is one greater than the previous card.
   */
  public boolean isAfter(PlayingCard prevCard) {

    // Return False if either card is KING
    if (prevCard.getValue() == 13)
    {
      return false;
    } else if (value == 13) {
      return  false;
    }

    return value == prevCard.getValue() + 1;
  }

  /**
   * Getter method for the card.
   * @return The value of the card.
   */
  public int getValue() {
    return value;
  }
}
