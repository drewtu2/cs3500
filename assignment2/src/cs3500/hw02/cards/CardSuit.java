package cs3500.hw02.cards;

public enum CardSuit {
  SPADE(Color.BLACK, "♠"),
  CLUB(Color.BLACK, "♣"),
  HEART(Color.RED, "♥"),
  DIAMOND(Color.RED, "♦");

  private Color cardColor;
  private String symbol;

  /**
   * Constructs a card suit with a given color.
   * @param myCardColor the color of this card.
   */
  CardSuit(Color myCardColor, String inputSymbol) {
    this.cardColor = myCardColor;
    this.symbol = inputSymbol;
  }

  /**
   * Returns the color of this card suit.
   * @return the color of this card suit.
   */
  public Color getCardColor() {
    return this.cardColor;
  }

  @Override
  public String toString() {
    return this.symbol;
  }
}
