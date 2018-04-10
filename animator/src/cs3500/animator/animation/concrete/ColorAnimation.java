package cs3500.animator.animation.concrete;

import cs3500.animator.animation.Animation;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.shape.IRGBColor;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.RGBColor;

/**
 * An animation that changes in color.
 */
public class ColorAnimation extends Animation {

  protected IRGBColor startColor;
  protected IRGBColor endColor;

  /**
   * Constructs a color animation.
   *
   * @param startColor the starting color
   * @param endColor the ending color
   * @param startTime the starting time
   * @param endTime the ending time
   */
  public ColorAnimation(IRGBColor startColor, IRGBColor endColor, int startTime, int endTime) {
    if (endTime < startTime || startTime < 0) {
      throw new IllegalArgumentException("Invalid times");
    }
    this.creationIndex = numCreated;
    this.numCreated++;
    this.type = AnimationType.COLOR;
    this.startColor = startColor;
    this.endColor = endColor;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Copy Constructor.
   *
   * @param copy the animation we're copying.
   */
  public ColorAnimation(ColorAnimation copy) {
    this.type = copy.type;
    this.startColor = copy.startColor;
    this.endColor = copy.endColor;
    this.startTime = copy.startTime;
    this.endTime = copy.endTime;
  }

  @Override
  public void setState(IShape current, int time) {
    float newRed = interpolate(startColor.getRed(), endColor.getRed(), time);
    float newGreen = interpolate(startColor.getGreen(), endColor.getGreen(), time);
    float newBlue = interpolate(startColor.getBlue(), endColor.getBlue(), time);

    IRGBColor newColor = new RGBColor(newRed, newGreen, newBlue);
    current.setColor(newColor);
  }

  @Override
  public String toString(String name) {
    StringBuilder myBuilder = new StringBuilder();

    myBuilder.append("Shape ");
    myBuilder.append(name);
    myBuilder.append(" changes color from ");
    myBuilder.append(startColor.toString());
    myBuilder.append(" to ");
    myBuilder.append(endColor.toString());
    myBuilder.append(" from time t=");
    myBuilder.append(startTime);
    myBuilder.append(" to time t=");
    myBuilder.append(endTime);

    return myBuilder.toString();

  }

  /**
   * Returns the start color.
   *
   * @return the start color.
   */
  public IRGBColor getStartColor() {
    return new RGBColor(startColor) {
    };
  }

  /**
   * Returns the end color.
   *
   * @return the end color.
   */
  public IRGBColor getEndColor() {
    return new RGBColor(endColor);
  }
}
