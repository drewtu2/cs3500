package cs3500.animator.shape;

import cs3500.animator.animation.AnimationSummary;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.animation.IAnimation;
import java.util.List;
import java.util.Map;

/**
 * The interfce for a general an animated shape. Animated shapes can be thought of as shapes whose
 * state is a function of time.
 */
public interface IAnimatedShape extends IShape, Comparable<IAnimatedShape>{

  /**
   * Adds an animation to the given shape.
   *
   * @param animation the animation to add
   * @throws IllegalArgumentException if the given animation overlaps with another animation
   */
  void addAnimation(IAnimation animation) throws IllegalArgumentException;

  /**
   * Returns the shape at a given time. This function is necessary to get the state of a shape at a
   * given point in time. The view will need to see what every animated shape looks like as a result
   * of all of the applied animations. This function will return the animation of tis particular
   * shape up to whomever needs to see it.
   *
   * @param t the time requested
   * @return the state at the requested time
   */
  IShape stateAt(int t);

  /**
   * Returns the map of animations associated with this shape.
   *
   * @return the map of animations associated with this shape
   */
  Map<AnimationType, List<IAnimation>> getAnimations();

  /**
   * String summary.
   *
   * @return list of the animation objects.
   */
  List<AnimationSummary> getSummary();

  /**
   * Returns the creation index.
   * @return the creation index.
   */
  int getCreationIndex();
}
