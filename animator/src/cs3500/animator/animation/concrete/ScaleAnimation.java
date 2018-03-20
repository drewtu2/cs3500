package cs3500.animator.animation.concrete;

import cs3500.animator.animation.Animation;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.dimension.IDimension;

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
  public ScaleAnimation(IDimension startDimension, IDimension endDimension, int startTime,
      int endTime) {

    if (endTime < startTime || startTime < 0) {
      throw new IllegalArgumentException("Invalid times");
    }
    this.creationIndex = numCreated;
    numCreated++;

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
  public void setState(IShape current, int time) {

    IDimension newDim = startDimension.getIntermediate(endDimension, startTime, endTime, time);

    current.setDimension(newDim);
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

  /**
   * Returns the start position.
   * @return the start position.
   */
  public IDimension getStartDimension() {
    return startDimension.getCopy();
  }

  /**
   * Returns the end dimension.
   * @return the end dimension.
   */
  public IDimension getEndDimension() {
    return endDimension.getCopy();
  }
}
