package cs3500.animator.animation.concrete;

import cs3500.animator.animation.Animation;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.shape.IShape;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Represents an appear animation.
 */
public class AppearAnimation extends Animation {

  /**
   * Constructs an appear animation.
   */
  public AppearAnimation(float appearTime) {

    if (appearTime < 0) {
      throw new IllegalArgumentException("Invalid times");
    }

    this.type = AnimationType.CREATE;

    this.startTime = appearTime;
    this.endTime = appearTime;
  }

  /**
   * Copy constructor.
   *
   * @param copy we want to construct
   */
  public AppearAnimation(AppearAnimation copy) {
    this.type = AnimationType.CREATE;

    this.startTime = copy.startTime;
    this.endTime = copy.endTime;

  }

  @Override
  public void setState(IShape state, float time) {

    /*
    if (time >= startTime) {
      // Manipulate state
      // state;
    } else {
      // Manipulate state
      // state;
    }
    */

    //TODO see interface for explanation
    throw new NotImplementedException();
  }

  @Override
  public String toString(String name) {
    StringBuilder myBuilder = new StringBuilder();

    myBuilder.append("Shape ");
    myBuilder.append(name);
    myBuilder.append(" appears at t=");
    myBuilder.append(startTime);

    return myBuilder.toString();
  }
}
