package cs3500.animator.shape;

/**
 * Represents an RGB Color.
 */
public interface IRGBColor {

  /**
   * Returns this color - subtractColor.
   *
   * @param subtractColor the color to subtract
   * @return the difference betweent the colors
   */
  IRGBColor subtract(IRGBColor subtractColor);

  /**
   * Sets the red value to a given amount.
   *
   * @param value the value to set to
   */
  void setRed(float value);

  /**
   * Sets the blue value to a given amount.
   *
   * @param value the value to set to
   */
  void setGreen(float value);

  /**
   * Sets the blue value to a given amount.
   *
   * @param value the value to set to
   */
  void setBlue(float value);

  /**
   * Sets this color equal to the given color.
   * @param color the color to set this equal to.
   */
  void set(IRGBColor color);

  /**
   * Returns the red value.
   *
   * @return the red value
   */
  float getRed();

  /**
   * Returns the green value.
   *
   * @return the green value
   */
  float getGreen();

  /**
   * Returns the blue value.
   *
   * @return the blue value
   */
  float getBlue();

  String toString();

  @Override
  boolean equals(Object a);

  @Override
  int hashCode();

}
