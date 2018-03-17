package cs3500.animator.model;

import cs3500.animator.animation.IAnimation;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IShape;
import java.util.List;
import java.util.Set;

/**
 * Interface for an Animator animator.model type.
 */
public interface IAnimatorModel extends IModelView{

  /**
   * Adds a new shape to the animator.model.
   *
   * @param newShape the shape to add.
   * @throws IllegalArgumentException if a shape with the given name already exists.
   */
  void addShape(IAnimatedShape newShape) throws IllegalArgumentException;

  /**
   * Deletes the given shape from the animator.
   *
   * @param shapeName the shape to be deleted
   * @throws IllegalArgumentException if the gvien shape doesn't exist.
   */
  void deleteShape(String shapeName) throws IllegalArgumentException;

  /**
   * Adds the given animation to a given shape.
   *
   * @param shapeName which shape is being applied to
   * @param animation the animation to be applied
   * @throws IllegalArgumentException if the requested shape doesn't exist or if conflict occurs
   *        with another animation on the same shape
   */
  void addAnimation(String shapeName, IAnimation animation) throws IllegalArgumentException;

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


}
