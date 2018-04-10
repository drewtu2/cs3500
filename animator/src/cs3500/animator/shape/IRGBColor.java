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
  public IRGBColor subtract(RGBColor subtractColor);

  /**
   * Sets the red value to a given amount.
   *
   * @param value the value to set to
   */
  public void setRed(float value);

  /**
   * Sets the blue value to a given amount.
   *
   * @param value the value to set to
   */
  public void setGreen(float value);

  /**
   * Sets the blue value to a given amount.
   *
   * @param value the value to set to
   */
  public void setBlue(float value);

  /**
   * Returns the red value.
   *
   * @return the red value
   */
  public float getRed();

  /**
   * Returns the green value.
   *
   * @return the green value
   */
  public float getGreen();

  /**
   * Returns the blue value.
   *
   * @return the blue value
   */
  public float getBlue();

  public String toString();

  @Override
  public boolean equals(Object a);

  @Override
  public int hashCode();

}
