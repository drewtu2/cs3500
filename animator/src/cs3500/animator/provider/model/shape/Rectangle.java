package cs3500.animator.provider.model.shape;
import cs3500.animator.provider.model.Color;
import cs3500.animator.provider.model.Posn;

/**
 * Represents a rectangle shape object.
 */
public class Rectangle extends AbstractShape {
  private int startTime;
  private int endTime;
  private String name;
  private Posn location;
  private Color color;
  private float width;
  private float height;

  public Rectangle(int startTime, int endTime, String name, Posn location, Color color, float width, float height) {
    super(startTime, endTime, name, location, color);
    this.width = width;
    this.height = height;
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  @Override
  public String getType() {
    return "rectangle";
  }

  @Override
  public String getAttributes() {
    return Integer.toString(startTime) + " " + Integer.toString(endTime)+ " " + this.name
            + location.toString() + color.toString();
  }

  @Override
  public String getSizeDescriptionWithScale(double scaleX, double scaleY) {
    return null;
  }

  @Override
  public AbstractShape clone() {
    return null;
  }

  @Override
  public void scale(double scaleX, double scaleY) {

  }

  @Override
  public void updateSize(double width, double height) {

  }
}
