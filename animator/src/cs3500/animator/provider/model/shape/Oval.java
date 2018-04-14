package cs3500.animator.provider.model.shape;

import cs3500.animator.provider.model.Color;
import cs3500.animator.provider.model.Posn;

/**
 * Represents a standard oval shape with a basic x radius and y radius.
 */
public class Oval extends AbstractShape {
  public static final String ERROR_RADIUS_BOUNDS =
          "The radius must be greater than 0.";

  private double originalRadiusX;
  private double originalRadiusY;
  private double radiusX;
  private double radiusY;

  /**
   * Constructs an oval with the given x radius and y radius.
   *
   * @param startTime the time to show the object
   * @param endTime   the time to hide the object
   * @param name      the object's name
   * @param location  the object's location on a canvas
   * @param color     the object's color
   * @param radiusX   the x radius of the oval
   * @param radiusY   the y radius of the oval
   * @throws IllegalArgumentException if the x radius or y radius is 0 or less
   */
  public Oval(int startTime, int endTime, String name, Posn location, Color color,
              double radiusX, double radiusY) throws IllegalArgumentException {
    super(startTime, endTime, name, location, color);
    if(startTime > endTime) {
      throw new IllegalArgumentException("invalid time");
    }
    this.radiusX = radiusX;
    this.radiusY = radiusY;
    this.originalRadiusX = radiusX;
    this.originalRadiusY = radiusY;
  }

  @Override
  public String getType() {
    return "oval";
  }

  @Override
  public String getAttributes() {
    StringBuilder builder = new StringBuilder();

    builder.append("Center: ")
            .append(this.getLocation().toString())
            .append(", ")
            .append(this.getSizeDescription())
            .append(", Color: ")
            .append(this.getColor().toString());

    return builder.toString();
  }

  @Override
  public String getSizeDescriptionWithScale(double scaleX, double scaleY) {
    StringBuilder builder = new StringBuilder();

    builder.append("X radius: ")
            .append(this.radiusX * scaleX)
            .append(", Y radius: ")
            .append(this.radiusY * scaleY);

    return builder.toString();
  }

  public AbstractShape clone() {
    return new Oval(this.getStartTime(),
        this.getEndTime(),
        this.getName(),
        this.getLocation(),
        this.getColor(),
        radiusX,
        radiusY);
  }

  @Override
  public void scale(double scaleX, double scaleY) {
    this.radiusX *= scaleX;
    this.radiusY *= scaleY;
  }

  @Override
  public void updateSize(double width, double height) {
    this.radiusX = width;
    this.radiusY = height;
  }

  /**
   * A getter for the x radius.
   *
   * @return the x radius value
   */
  public double getRadiusX() {
    return radiusX;
  }

  /**
   * A getter for the y radius.
   *
   * @return the y radius value
   */
  public double getRadiusY() {
    return radiusY;
  }

  @Override
  public void reset() {
    this.radiusX = originalRadiusX;
    this.radiusY = originalRadiusY;
  }
}
