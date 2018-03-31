package cs3500.animator.animation;

import cs3500.animator.shape.IShape;
import util.MyUtil;

/**
 * Represents the general body of an animation.
 */
public abstract class Animation implements IAnimation {

  protected int startTime;
  protected int endTime;
  protected AnimationType type;
  protected int creationIndex;
  protected static int numCreated = 0;

  @Override
  public int getCreationIndex() {
    return creationIndex;
  }

  @Override
  public int getStartTime() {
    return startTime;
  }

  @Override
  public int getEndTime() {
    return endTime;
  }

  @Override
  public AnimationType getType() {
    return type;
  }

  @Override
  public boolean inBounds(float t) {
    return t >= startTime && t <= endTime;
  }

  @Override
  public int compareTo(IAnimation o) {
    int startTime = (int) (this.startTime - o.getStartTime());
    if (startTime == 0) {
      return this.creationIndex - o.getCreationIndex();
    } else {
      return startTime;
    }
  }

  @Override
  public abstract void setState(IShape state, int time);

  @Override
  public abstract String toString(String name);


  /**
   * Returns the total duration of the animation.
   *
   * @return the total duration of the animation
   */
  protected float duration() {
    return endTime - startTime;
  }

  /**
   * Returns the intermediate value that occurs between two points in time based on linear
   * interpolation.
   *
   * @param startValue the start value
   * @param endValue the end value
   * @param time the point in time we're looking for
   */
  protected float interpolate(float startValue, float endValue, int time) {
    return MyUtil.interpolate(startValue, endValue, startTime, endTime, time);
  }

}
