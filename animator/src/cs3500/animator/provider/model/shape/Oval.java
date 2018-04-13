package cs3500.animator.provider.model.shape;

import cs3500.animator.provider.model.Color;
import cs3500.animator.provider.model.Posn;

/**
 * Represents a circle shape object.
 */
public class Oval extends AbstractShape {
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
    this.radiusX = (float) width;
    this.radiusY = (float) height;

  }
}
