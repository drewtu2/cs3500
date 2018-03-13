package cs3500.animator.animation.concrete;

import cs3500.animator.animation.Animation;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.dimension.IDimension;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Represents a scale animation.
 */
public class ScaleAnimation extends Animation {

  IDimension startDimension;
  IDimension endDimension;

  /**
   * Constructs a scale animation.
   *
   * @param startDimension the initial state of the shape
   * @param endDimension the end state of the shape
   * @param startTime the start time
   * @param endTime the end time
   */
  public ScaleAnimation(IDimension startDimension, IDimension endDimension, float startTime,
      float endTime) {

    if (endTime < startTime || startTime < 0) {
      throw new IllegalArgumentException("Invalid times");
    }

    this.type = AnimationType.SCALE;
    this.startDimension = startDimension;
    this.endDimension = endDimension;
    this.startTime = startTime;
    this.endTime = endTime;

  }

  /**
   * Copy Constructor.
   *
   * @param copy the animation to copy
   */
  public ScaleAnimation(ScaleAnimation copy) {

    this.type = AnimationType.SCALE;
    this.startDimension = copy.startDimension;
    this.endDimension = copy.endDimension;
    this.startTime = copy.startTime;
    this.endTime = copy.endTime;

  }

  @Override
  public void setState(IShape current, float time) {
    /*
    float deltT = duration();

    IDimension newDim = startDimension
        .getIntermediate(endDimension, deltT, time);

    current.setDimension(newDim);
    */
    //TODO: See interface for explanation
    throw new NotImplementedException();

  }

  @Override
  public String toString(String name) {
    StringBuilder myBuilder = new StringBuilder();

    myBuilder.append("Shape ");
    myBuilder.append(name);
    myBuilder.append(" scales from ");
    myBuilder.append(startDimension.toString());
    myBuilder.append(" to ");
    myBuilder.append(endDimension.toString());
    myBuilder.append(" from time t=");
    myBuilder.append(startTime);
    myBuilder.append(" to time t=");
    myBuilder.append(endTime);

    return myBuilder.toString();
  }
}
