package cs3500.animator.model;

import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IShape;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IModelView {

  /**
   * Returns the full map of animated shapes and their corresponding animations.
   * @return Map of ShapeName, AnimatedShape
   */
  Map<String, IAnimatedShape> getFullState();

  /**
   * Returns the state of the animator as a collection of states.
   *
   * @param time the time we want the state at
   * @return a list of states representing the shapes at the given time
   */
  List<IShape> getState(float time);

  /**
   * Returns a list of the shapes by name in the animator.model.
   *
   * @return a list of the shapes by name in the animator.model
   */
  Set<String> listShapes();

  /**
   * A summary of the Animator animator.model.
   *
   * @return String representation of the summary.
   */
  String toString();

  /**
   * Returns the state of the animator at a given time as a string.
   *
   * @param time the time we want the state at
   * @return the text representation of the animator
   */
  String toString(float time);
}
