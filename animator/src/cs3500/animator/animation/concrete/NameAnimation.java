package cs3500.animator.animation.concrete;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import cs3500.animator.animation.Animation;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.RGBColor;

public class NameAnimation extends Animation {

  String oldName;
  String newName;

  /**
   * Constructs a name change animation.
   *
   * @param oldName the starting color
   * @param newName the ending color
   * @param startTime the starting time
   * @param endTime the ending time
   */
  public NameAnimation(String oldName, String newName, int startTime, int endTime) {
    if (endTime < startTime || startTime < 0) {
      throw new IllegalArgumentException("Invalid times");
    }

    this.type = AnimationType.NAME;
    this.oldName = oldName;
    this.newName = newName;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Copy Constructor.
   *
   * @param copy the animation we're copying.
   */
  public NameAnimation(NameAnimation copy) {
    this.type = copy.type;
    this.oldName = copy.oldName;
    this.newName = copy.newName;
    this.startTime = copy.startTime;
    this.endTime = copy.endTime;
  }

  @Override
  public void setState(IShape state, int time) {
    // TODO: See interface for explanation
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
    myBuilder.append(" changes name from ");
    myBuilder.append(oldName);
    myBuilder.append(" to ");
    myBuilder.append(newName);
    myBuilder.append(" from time t=");
    myBuilder.append(startTime);
    myBuilder.append(" to time t=");
    myBuilder.append(endTime);

    return myBuilder.toString();

  }
}
