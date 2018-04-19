package cs3500.animator.animation.concrete;

import cs3500.animator.animation.Animation;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.shape.IShape;

/**
 * Represents a rotation animation.
 */
public class RotationAnimation extends Animation {

  private int startRotation;
  private int endRotation;

  /**
   * Rotation Animation.
   *
   * @param startTick start tick
   * @param endTick end tick
   * @param startRotation start rotation
   * @param endRotation end rotation
   */
  public RotationAnimation(int startRotation, int endRotation, int startTick, int endTick) {

    if (endTime < startTime || startTime < 0) {
      throw new IllegalArgumentException("Invalid times");
    }

    this.startTime = startTick;
    this.endTime = endTick;
    this.startRotation = startRotation;
    this.endRotation = endRotation;

    this.type = AnimationType.ROTATION;

    this.creationIndex = numCreated;
    numCreated++;

  }

  /**
   * Copy Constructor.
   *
   * @param copy the animation to copy
   */
  public RotationAnimation(RotationAnimation copy) {

    this.type = AnimationType.ROTATION;
    this.startRotation = copy.startRotation;
    this.endRotation = copy.endRotation;
    this.startTime = copy.startTime;
    this.endTime = copy.endTime;

  }

  @Override
  public void setState(IShape state, int time) {
    float intermediateRotation = this.interpolate(startRotation, endRotation, time);

    state.setRotation((int) intermediateRotation);
  }

  @Override
  public String toString(String name) {
    StringBuilder myBuilder = new StringBuilder();

    myBuilder.append("Shape ");
    myBuilder.append(name);
    myBuilder.append(" rotates from ");
    myBuilder.append(startRotation);
    myBuilder.append(" to ");
    myBuilder.append(endRotation);
    myBuilder.append(" from time t=");
    myBuilder.append(startTime);
    myBuilder.append(" to time t=");
    myBuilder.append(endTime);

    return myBuilder.toString();
  }

  @Override
  public int getStartRotation() {
    return this.startRotation;
  }

  @Override
  public int getEndRotation() {
    return this.endRotation;
  }
}
