package Animation.concreteAnimations;

import Animation.Animation;
import Animation.AnimationType;
import shape.IShape;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Represents the a shape disappearing.
 */
public class DisappearAnimation extends Animation {

  /**
   * Constructs an animation.
   *
   * @param disappearTime the time to disappear.
   */
  public DisappearAnimation(float disappearTime) {

    if (disappearTime < 0) {
      throw new IllegalArgumentException("Invalid times");
    }

    this.type = AnimationType.DESTROY;
    this.startTime = disappearTime;
    this.endTime = disappearTime;
  }

  /**
   * Copy constructor.
   */
  public DisappearAnimation(DisappearAnimation copy) {
    this.type = copy.type;
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

    //TODO
    throw new NotImplementedException();
  }

  @Override
  public String toString(String name) {
    StringBuilder myBuilder = new StringBuilder();

    myBuilder.append("Shape ");
    myBuilder.append(name);
    myBuilder.append(" disappears at t=");
    myBuilder.append(startTime);

    return myBuilder.toString();
  }
}
