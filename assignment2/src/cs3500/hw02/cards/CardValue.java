package cs3500.hw02.cards;

public enum CardValue {
  ACE("A"),
  TWO("2"),
  THREE("3"),
  FOUR("4"),
  FIVE("5"),
  SIX("6"),
  SEVEN("7"),
  EIGHT("8"),
  NINE("9"),
  TEN("10"),
  JACK("J"),
  QUEEN("Q"),
  KING("K");

  private final String value;

  /**
   * Creates a CardValue with a given value.
   */
  CardValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

  /**
   * Returns the next value in the sequence of cards.
   * @return the next value in the sequence of cards.
   */
  public CardValue next() {
    CardValue[] values = CardValue.values();
    int ordinal = (this.ordinal() + 1) % values.length;
    return values[ordinal];
  }
}
