package cs3500.animator.provider.model;

import java.util.Objects;

/**
   * Represents an Color.
   */
  public class Color {

    protected float red;
    protected float green;
    protected float blue;

    /**
     * Constructs a Color.
     *
     * @param red the red value
     * @param green the green value
     * @param blue the blue value
     */
    public Color(float red, float green, float blue) {
      if (!validRange(red) || !validRange(green) || !validRange(blue)) {
        throw new IllegalArgumentException("Invalid RGB color");
      }

      this.red = red;
      this.green = green;
      this.blue = blue;

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

  public void setRed(float value) {
    this.red = value;
  }

  public void setGreen(float value) {
    this.green = value;
  }

  public void setBlue(float value) {
    this.blue = value;
  }

  public float getRed() {
    return this.red;
  }

  public float getGreen() {
    return this.green;
  }

  public float getBlue() {
    return this.blue;
  }

    @Override
    public boolean equals(Object a) {
      if (this == a) {
        return true;
      }
      if (!(a instanceof Color)) {
        return false;
      }

      Color that = (Color) a;

      return ((Math.abs(this.red - that.red) < 0.01)
              && (Math.abs(this.green - that.green) < 0.01)
              && (Math.abs(this.blue - that.blue) < 0.01));
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.red, this.green, this.blue);
    }


  public java.awt.Color transformToAwt() {
    return new java.awt.Color(red, green, blue);
  }
}
