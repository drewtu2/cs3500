package cs3500.animator.shape.dimension;

import java.util.Objects;

/**
 * Represents the dimensions for a Width x Height rectangle.
 */
public class WidthHeightDim implements IDimension {

  float width;
  float height;

  public WidthHeightDim(float width, float height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public IDimension getIntermediate(IDimension end, float duration, float time) {
    if (end.getClass() != this.getClass()) {
      throw new IllegalArgumentException("The given dimension is of a different class");
    }

    if (time > duration || time < 0) {
      throw new IllegalArgumentException("Time out of bonunds");
    }

    float slopeHeight = (((WidthHeightDim) end).height - this.height) / duration;
    float slopeWidth = (((WidthHeightDim) end).width - this.width) / duration;

    return new WidthHeightDim(width + (time * slopeWidth), height + (time * slopeHeight));
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
