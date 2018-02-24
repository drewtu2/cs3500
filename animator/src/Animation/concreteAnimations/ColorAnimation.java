package Animation.concreteAnimations;

import Animation.Animation;
import Animation.AnimationType;
import shape.IShape;
import shape.RGBColor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * An animation that changes in color.
 */
public class ColorAnimation extends Animation {

  RGBColor startColor;
  RGBColor endColor;

  /**
   * Constructs a color animation.
   *
   * @param startColor the starting color
   * @param endColor the ending color
   * @param startTime the starting time
   * @param endTime the ending time
   */
  public ColorAnimation(RGBColor startColor, RGBColor endColor, float startTime, float endTime) {
    if (endTime < startTime || startTime < 0) {
      throw new IllegalArgumentException("Invalid times");
    }

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
  public void setState(IShape current, float time) {
    // Hasn't been tested...
    throw new NotImplementedException();

    /*
    RGBColor deltColor = endColor.subtract(startColor);

    float deltT = duration();
    int slopeR = (int) (deltColor.getRed() / duration());
    int slopeG = (int) (deltColor.getGreen() / duration());
    int slopeB = (int) (deltColor.getBlue() / duration());

    RGBColor newColor = new RGBColor(
        (int) (startColor.getRed() + (slopeR * time)),
        (int) (startColor.getGreen() + (slopeG * time)),
        (int) (startColor.getBlue() + (slopeB * time)));

    current.setColor(newColor);
    */
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
}
