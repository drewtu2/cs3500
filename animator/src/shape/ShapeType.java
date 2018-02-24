package shape;

/**
 * Represents the different possible shapes. Since reference point is determined by shape type, the
 * shape type contains the appropriate reference type.
 */
public enum ShapeType {
  RECTANGLE("rectangle", ReferencePoint.LOWER_LEFT_CORNER),
  OVAL("oval", ReferencePoint.CENTER);

  private final String description;
  private final ReferencePoint ref;

  /**
   * Constructs a shape type.
   *
   * @param description the textual representation
   * @param ref the reference point of this type of shape
   */
  ShapeType(String description, ReferencePoint ref) {
    this.description = description;
    this.ref = ref;
  }

  /**
   * Returns the description of the ShapeType.
   *
   * @return the description of the ShapeType
   */
  @Override
  public String toString() {
    return description;
  }

  /**
   * Returns the ReferencePoint of the ShapeType.
   *
   * @return the ReferencePoint of the ShapeType
   */
  public ReferencePoint getRef() {
    return ref;
  }
}
