package Animation;

import shape.IShape;

public abstract class Animation implements IAnimation {
  protected float startTime;
  protected float endTime;
  protected AnimationType type;
  protected IShape startState;
  protected IShape endState;

  @Override
  public float getStartTime() {
    return startTime;
  }

  @Override
  public float getEndTime() {
    return endTime;
  }

  @Override
  public boolean inBounds(float t) {
    return t >= startTime && t <= endTime;
  }

  /**
   * Returns the total duration of the animation
   * @return the total duration of the animation
   */
  float duration() {
    return endTime - startTime;
  }

  public abstract IShape getState(float time);
}
