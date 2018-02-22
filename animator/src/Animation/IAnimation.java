package Animation;

import shape.IShape;

public interface IAnimation {

  /**
   * Returns the start time of the animation.
   * @return the start time of the animation
   */
  float getStartTime();

  /**
   * Returns the end time of the animation.
   * @return the end time of the animation
   */
  float getEndTime();

  /**
   * Returns true if the given time falls within the bounds of this animation
   * @param t the time requested
   * @return true if the given time falls within the bounds of this animation
   */
  boolean inBounds(float t);

  /**
   * Computes the new state of the shape at given time as a result of this animation.
   * @return the new state of the shape.
   */
  IShape getState(float time);
}
