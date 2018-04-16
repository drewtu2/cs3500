package cs3500.animator.animation;

import cs3500.animator.shape.IPosition;
import cs3500.animator.shape.IRGBColor;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.dimension.IDimension;

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
   * Returns the index of creation.
   * @return the index of creation
   */
  int getCreationIndex();

  /**
   * Returns a string describing the overall animation.
   *
   * @param name the name of the object
   * @return a string describing the overall animation
   */
  String toString(String name);

  /**
   * Returns an animation summary object.
   * @param descripton the description
   * @return the summary of the animation, sortable.
   */
  IAnimationSummary getAnimationSummary(String descripton);

  /**
   * Returns the start position of the animation if applicable.
   * @throws UnsupportedOperationException if the method does not apply to the specific animation.
   * @return the start position of the animation.
   */
  IPosition getStartPos();

  /**
   * Returns the end position of the animation if applicable.
   * @throws UnsupportedOperationException if the method does not apply to the specific animation.
   * @return the end position of the animation.
   */
  IPosition getEndPos();

  /**
   * Returns the start color of the animation if applicable.
   * @throws UnsupportedOperationException if the method does not apply to the specific animation.
   * @return the start color of the animation of applicable
   */
  IRGBColor getStartColor();

  /**
   * Returns the end color of the animation if applicable.
   * @throws UnsupportedOperationException if the method does not apply to the specific animation.
   * @return the end color of the animation of applicable
   */
  IRGBColor getEndColor();

   /**
   * Returns the start dimension of the animation if applicable.
   * @throws UnsupportedOperationException if the method does not apply to the specific animation.
   * @return the start dimension of the animation of applicable
   */
  IDimension getStartDimension();

  /**
   * Returns the end dimension of the animation if applicable.
   * @throws UnsupportedOperationException if the method does not apply to the specific animation.
   * @return the end dimension of the animation of applicable
   */
  IDimension getEndDimension();

}
