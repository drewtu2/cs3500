package shape;

public class RGBColor {

  protected int red;
  protected int green;
  protected int blue;

  /**
   * Constructs a RGBColor
   * @param red the red value
   * @param green the green value
   * @param blue the blue value
   */
  public RGBColor(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;

  }

  /**
   * Returns this color - subtractColor
   * @param subtractColor the color to subtract
   * @return the difference betweent the colors
   */
  public RGBColor subtract(RGBColor subtractColor) {
    return new RGBColor(this.red - subtractColor.red,
        this.green - subtractColor.green,
        this.blue - subtractColor.blue);
  }

  /**
   * Sets the red value to a given amount.
   * @param value the value to set to
   */
  public void setRed(int value) {
    this.red = value;
  }

  /**
   * Sets the blue value to a given amount.
   * @param value the value to set to
   */
  public void setGreen(int value) {
    this.green = value;
  }

  /**
   * Sets the blue value to a given amount.
   * @param value the value to set to
   */
  public void setBlue(int value) {
    this.blue = value;
  }

  /**
   * Returns the red value.
   * @return the red value
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Returns the green value.
   * @return the green value
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Returns the blue value.
   * @return the blue value
   */
  public int getBlue() {
   return this.blue;
  }

}
