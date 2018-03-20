package cs3500.animator.shape.dimension;

import cs3500.animator.util.MyUtil;
import java.util.Objects;

/**
 * Represents the dimensions for a Width x Height rectangle.
 */
public class WidthHeightDim implements IDimension {

  private int width;
  private int height;

  public WidthHeightDim(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
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

    int newHeight = MyUtil.interpolate(this.height, ((WidthHeightDim)end).height,
        startTime, endTime, time);
    int newWidth = MyUtil.interpolate(this.width, ((WidthHeightDim)end).width,
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
