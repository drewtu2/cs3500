package cs3500.animator.shape;

import java.util.Objects;

/**
 * Represents an RGB Color.
 */
public class RGBColor {

  protected float red;
  protected float green;
  protected float blue;

  /**
   * Constructs a RGBColor.
   *
   * @param red the red value
   * @param green the green value
   * @param blue the blue value
   */
  public RGBColor(float red, float green, float blue) {
    if (!validRange(red) || !validRange(green) || !validRange(blue)) {
      throw new IllegalArgumentException("Invalid RGB color");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;

  }

  /**
   * Copy constructor for a RGBColor.
   *
   * @param copy thing to copy
   */
  public RGBColor(RGBColor copy) {
    this.red = copy.red;
    this.green = copy.green;
    this.blue = copy.blue;

  }

  /**
   * Returns true if the given float is a valid RGB value.
   *
   * @param value the value
   * @return true if the value is valid
   */
  private boolean validRange(float value) {
    return value >= 0 && value <= 1;
  }


  /**
   * Returns this color - subtractColor.
   *
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
   *
   * @param value the value to set to
   */
  public void setRed(float value) {
    this.red = value;
  }

  /**
   * Sets the blue value to a given amount.
   *
   * @param value the value to set to
   */
  public void setGreen(float value) {
    this.green = value;
  }

  /**
   * Sets the blue value to a given amount.
   *
   * @param value the value to set to
   */
  public void setBlue(float value) {
    this.blue = value;
  }

  /**
   * Returns the red value.
   *
   * @return the red value
   */
  public float getRed() {
    return this.red;
  }

  /**
   * Returns the green value.
   *
   * @return the green value
   */
  public float getGreen() {
    return this.green;
  }

  /**
   * Returns the blue value.
   *
   * @return the blue value
   */
  public float getBlue() {
    return this.blue;
  }

  @Override
  public String toString() {
    StringBuilder myBuiler = new StringBuilder();

    myBuiler.append("Color: (");
    myBuiler.append(red);
    myBuiler.append(", ");
    myBuiler.append(green);
    myBuiler.append(", ");
    myBuiler.append(blue);
    myBuiler.append(")");

    return myBuiler.toString();
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof RGBColor)) {
      return false;
    }

    RGBColor that = (RGBColor) a;

    return ((Math.abs(this.red - that.red) < 0.01)
            && (Math.abs(this.green - that.green) < 0.01)
            && (Math.abs(this.blue - that.blue) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue);
  }


}
