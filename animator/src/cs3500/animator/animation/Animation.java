package cs3500.animator.animation;

import cs3500.animator.shape.IShape;

/**
 * Represents the general body of an animation.
 */
public abstract class Animation implements IAnimation {

  protected float startTime;
  protected float endTime;
  protected AnimationType type;

  @Override
  public float getStartTime() {
    return startTime;
  }

  @Override
  public float getEndTime() {
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
    return (int) (this.startTime - o.getStartTime());
  }

  @Override
  public abstract void setState(IShape state, float time);

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
   * @param startValue the start value
   * @param endValue the end value
   * @param time the point in time we're looking for
   */
  protected float interpolate(float startValue, float endValue, float time) {
    float componentA = startValue * (endTime - time)/(endTime - startTime);
    float componentB = endValue * (time - startTime)/(endTime - startTime);

    return componentA + componentB;
  }

}
