package shape;

import Animation.AnimationSummary;
import Animation.IAnimation;
import java.util.List;

/**
 * The interfce for a general an animated shape. Animated shapes can be thought of as shapes whose
 * state is a function of time.
 */
public interface IAnimatedShape extends IShape {

  /**
   * Adds an animation to the given shape.
   *
   * @param animation the animation to add
   * @throws IllegalArgumentException if the given animation overlaps with another animation
   */
  void addAnimation(IAnimation animation) throws IllegalArgumentException;

  /**
   * Returns the shape at a given time.
   *
   * @param t the time requested
   * @return the state at the requested time
   */
  IShape stateAt(float t);


  /**
   * String summary.
   *
   * @return list of the animation objects.
   */
  List<AnimationSummary> getSummary();
}
