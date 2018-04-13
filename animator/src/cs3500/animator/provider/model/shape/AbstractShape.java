package cs3500.animator.model.shape;

import cs3500.animator.model.Color;
import cs3500.animator.model.AbstractCanvasObject;
import cs3500.animator.model.Posn;

/**
 * Represents an abstract shape that can be placed in a canvas.
 */
public abstract class AbstractShape extends AbstractCanvasObject {
  public static final String ERROR_NAME_NULL =
          "The name cannot be null.";
  public static final String ERROR_LOCATION_NULL =
          "The location cannot be null.";
  public static final String ERROR_COLOR_NULL =
          "The color cannot be null.";
  public static final String ERROR_NAME_HAS_SPACE =
          "The name cannot have a space in it.";

  private String name;
  private Posn originalLocation;
  private Posn location;
  private Color originalColor;
  private Color color;


  /**
   * Constructs a shape with the given name and position.
   *
   * @param startTime the time to show the object
   * @param endTime   the time to hide the object
   * @param name      the object's name
   * @param location  the object's location on a canvas
   * @param color     the object's color
   * @throws IllegalArgumentException if the name, location or color is null
   */
  public AbstractShape(int startTime, int endTime, String name, Posn location, Color color)
          throws IllegalArgumentException {
  }

  /**
   * A getter for the name.
   *
   * @return the name String
   */
  public String getName() {
    return this.name;
  }

  /**
   * A getter for the location.
   *
   * @return the location Posn
   */
  public Posn getLocation() {
    return this.location;
  }

  /**
   * A getter for the color.
   *
   * @return the color object
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Produces a string describing the type of the object, usually the object's class name.
   *
   * @return the type description string
   */
  public abstract String getType();

  /**
   * Produces a string describing the attributes associated with the shape.
   *
   * @return the attribute description string
   */
  public abstract String getAttributes();

  /**
   * Produces a string describing the attributes associated with this shape's size if the size is
   * multiplied by a scale factor in the x and y directions.
   *
   * @param scaleX the amount to scale the shape in the x direction
   * @param scaleY the amount to scale the shape in the y direction
   * @return the size attribute description string with the scaling factor taken into place
   */
  public abstract String getSizeDescriptionWithScale(double scaleX, double scaleY);

  /**
   * Produces an identical copy of this shape in a different memory location.
   *
   * @return the cloned shape
   */
  public abstract AbstractShape clone();

  /**
   * A convenience method describing the attributes associated with this shape's size and without
   * a scale factor.
   *
   * @return the size attribute description string
   */
  public String getSizeDescription() {
    return getSizeDescriptionWithScale(1.0, 1.0);
  }

  /**
   * Modifies this shape to be located at the new given location.
   *
   * @param newLocation the location to move this shape to
   */
  public void move(Posn newLocation) {
  }

  /**
   * Modifies this shape to scale itself by the given x and y scaling factors.
   *
   * @param scaleX the factor to scale in the x direction
   * @param scaleY the factor to scale in the y direction
   */
  public abstract void scale(double scaleX, double scaleY);

  /**
   * Modifies this shape's width and height to the new width and height.
   * @param width width of the shape
   * @param height height of the shape
   */
  public abstract void updateSize(double width, double height);

  /**
   * Modifies this shape to be colored the new given color.
   *
   * @param newColor the color to change this shape to
   */
  public void changeColor(Color newColor) {
  }

  @Override
  public boolean equals(Object other) {
    // two shapes should only be considered the same if they are the same in memory
    return this == other;
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append("Name: ").append(this.name);
    builder.append("\n");
    builder.append("Type: ").append(this.getType());
    builder.append("\n");
    builder.append(this.getAttributes());
    builder.append("\n");
    builder.append("Appears at t=").append(this.getStartTime());
    builder.append("\n");
    builder.append("Disappears at t=").append(this.getEndTime());

    return builder.toString();
  }

  /**
   * Sets the properties of this shape to its original properties at construction.
   */
  public void reset() {
  }
}
