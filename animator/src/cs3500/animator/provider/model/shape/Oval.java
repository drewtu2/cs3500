package cs3500.animator.provider.model.shape;

import cs3500.animator.provider.model.Color;
import cs3500.animator.provider.model.Posn;

/**
 * Represents a circle shape object.
 */
public class Oval extends AbstractShape {
  private int startTime;
  private int endTime;
  private String name;
  private Posn location;
  private Color color;
  private float radiusX;
  private float radiusY;

  public Oval(int startTime, int endTime, String name, Posn location, Color color, float radiusX,
              float radiusY) {
    super(startTime, endTime, name, location, color);
    this.radiusX = radiusX;
    this.radiusY = radiusY;
  }

  public float getRadiusX() {
    return radiusX;
  }

  public float getRadiusY() {
    return radiusY;
  }

  @Override
  public String getType() {
    return null;
  }

  @Override
  public String getAttributes() {
    return null;
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
