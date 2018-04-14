package cs3500.animator.provider.model;

import java.util.List;
import java.util.Map;

import cs3500.animator.provider.object.animation.AbstractAnimation;
import cs3500.animator.provider.object.shape.AbstractShape;

/**
 * Represents the operations that can be performed on a model that holds animations for use on
 * shapes.
 */
public interface IAnimatorModel {
  public static final String ERROR_CONFLICTING_ANIMATION =
          "Cannot add an animation to the model that is of the same type, operates on the same " +
                  "shape, and overlaps the time period of another animation.";

  /**
   * Adds an animation to the collection of animations that occur in the model.
   *
   * @param anim the animation to add
   * @throws IllegalArgumentException if an animation is added that conflicts with existing
   *                                  animations
   */
  void addAnimation(AbstractAnimation anim) throws IllegalArgumentException;

  /**
   * Produces a String representing all of the shapes and animations held by the model.
   *
   * @return the animation state String
   */
  String getAnimatorDescription();

  /**
   * Produces a copy of each animation held by the model.
   *
   * @return the list of animation copies
   */
  List<AbstractAnimation> getAnimations();

  /**
   * Produces a copy of each shape held by the model.
   *
   * @return the list of abstract shape copies
   */
  List<AbstractShape> getShapes();

  /**
   * Sets the order mapping in which the shapes should appear.
   * @param shapeOrder the order mapping in which the shapes should appear
   */
  void setShapeOrder(Map<AbstractShape, Integer> shapeOrder);

  /**
   * Produces a copy of the shape order mapping.
   *
   * @return the shape order mapping copy
   */
  Map<AbstractShape, Integer> getShapeOrder();
}
