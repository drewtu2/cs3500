package cs3500.animator.animation;

import cs3500.animator.shape.IShape;

/**
 * Represents any animation that can be applied to a shape.
 */
public interface IAnimation extends Comparable<IAnimation> {

  /**
   * Returns the start time of the animation.
   *
   * @return the start time of the animation
   */
  int getStartTime();

  /**
   * Returns the end time of the animation.
   *
   * @return the end time of the animation
   */
  int getEndTime();

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
   * Sets the new state of the shape at given time as a result of this animation. This function is
   * necessary in order to calcualte the state of a shape at as a functio of time. When every
   * animation has this function, the state of a shape at a given point in time is given by the
   * applying every type of animation to the state and displaying the result.
   */
  void setState(IShape givenShape, int time);


  /**
   * Returns a string describing the overall animation.
   *
   * @param name the name of the object
   * @return a string describing the overall animation
   */
  String toString(String name);

}
