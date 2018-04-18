package cs3500.animator.provider.object.shape;

import cs3500.animator.provider.object.IColor;
import cs3500.animator.provider.object.Posn;

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
   * @param endTime the time to hide the object
   * @param name the object's name
   * @param location the object's location on a canvas
   * @param color the object's color
   * @param radiusX the x radius of the oval
   * @param radiusY the y radius of the oval
   * @throws IllegalArgumentException if the x radius or y radius is 0 or less
   */
  public Oval(int startTime, int endTime, String name, Posn location, IColor color,
      double radiusX, double radiusY) throws IllegalArgumentException {
    super(startTime, endTime, name, location, color);
    if (radiusX <= 0 || radiusY <= 0) {
      throw new IllegalArgumentException(ERROR_RADIUS_BOUNDS);
    }

    this.originalRadiusX = radiusX;
    this.originalRadiusY = radiusY;
    this.radiusX = radiusX;
    this.radiusY = radiusY;
  }


  @Override
  public String getType() {
    return "oval";
  }

  @Override
  public double getX() {
    return radiusX;
  }

  @Override
  public double getY() {
    return radiusY;
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

  @Override
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

  @Override
  public void reset() {
    super.reset();
    this.radiusX = originalRadiusX;
    this.radiusY = originalRadiusY;
  }
}
