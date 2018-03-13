package cs3500.animation;

import cs3500.shape.IShape;

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

  /**
   * Returns the total duration of the animation.
   *
   * @return the total duration of the animation
   */
  protected float duration() {
    return endTime - startTime;
  }

  @Override
  public abstract void setState(IShape state, float time);

  @Override
  public abstract String toString(String name);

}
