package Animation;

import shape.IShape;

/**
 * Represents any animation that can be applied to a shape.
 */
public interface IAnimation extends Comparable<IAnimation> {

  /**
   * Returns the start time of the animation.
   *
   * @return the start time of the animation
   */
  float getStartTime();

  /**
   * Returns the end time of the animation.
   *
   * @return the end time of the animation
   */
  float getEndTime();

  /**
   * Returns the type of the animation.
   *
   * @return the type of the animation
   */
  AnimationType getType();

  /**
   * Convenience function that returns true if the given time falls within the bounds of this
   * animation.
   *
   * @param t the time requested
   * @return true if the given time falls within the bounds of this animation
   */
  boolean inBounds(float t);

  /**
   * Sets the new state of the shape at given time as a result of this animation.
   */
  void setState(IShape givenShape, float time);

  /**
   * Returns a string describing the overall animation.
   *
   * @param name the name of the object
   * @return a string describing the overall animation
   */
  String toString(String name);

}
