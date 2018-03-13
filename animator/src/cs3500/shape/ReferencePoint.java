package cs3500.shape;

/**
 * Represents the set of locations that a shape can be referenced by.
 */
public enum ReferencePoint {
  CENTER("Center"), LOWER_LEFT_CORNER("Lower-left-corner");

  String description;

  /**
   * Constructs a reference point.
   *
   * @param description the string representation of the point
   */
  ReferencePoint(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
