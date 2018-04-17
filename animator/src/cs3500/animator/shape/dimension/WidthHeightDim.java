package cs3500.animator.shape.dimension;

import java.util.Objects;
import util.IUtil;

/**
 * Represents the dimensions for a Width x Height rectangle.
 */
public class WidthHeightDim implements IDimension {

  private float width;
  private float height;

  /**
   * Constructor of a Width and Height Dimension.
   *
   * @param width the width
   * @param height the height
   */
  public WidthHeightDim(float width, float height) {
    this.width = width;
    this.height = height;
  }

  /**
   * Returns the width.
   *
   * @return the width
   */
  @Override
  public float getWidth() {
    return width;
  }

  /**
   * Returns the height.
   *
   * @return the height
   */
  @Override
  public float getHeight() {
    return height;
  }

  @Override
  public IDimension getIntermediate(IDimension end, int startTime, int endTime, int time) {
    if (end.getClass() != this.getClass()) {
      throw new IllegalArgumentException("The given dimension is of a different class");
    }
    if (time > endTime || time < startTime) {
      throw new IllegalArgumentException("Time out of bonunds");
    }

    float newHeight = IUtil.interpolate(this.height, ((WidthHeightDim) end).height,
        startTime, endTime, time);
    float newWidth = IUtil.interpolate(this.width, ((WidthHeightDim) end).width,
        startTime, endTime, time);

    return new WidthHeightDim(newWidth, newHeight);
  }

  @Override
  public IDimension getCopy() {
    return new WidthHeightDim(this.width, this.height);
  }

  @Override
  public boolean equals(Object test) {
    if (this.getClass() != test.getClass()) {
      return false;
    }
    WidthHeightDim tester = (WidthHeightDim) (test);
    return (this.width == tester.width) && (this.height == tester.height);

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.width, this.height);
  }

  @Override
  public String toString() {
    StringBuilder myBuilder = new StringBuilder();

    myBuilder.append("Width: ");
    myBuilder.append(width);
    myBuilder.append(" Height: ");
    myBuilder.append(height);

    return myBuilder.toString();
  }
}
