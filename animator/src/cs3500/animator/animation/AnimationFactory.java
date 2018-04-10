package cs3500.animator.animation;

import cs3500.animator.animation.concrete.AppearAnimation;
import cs3500.animator.animation.concrete.ColorAnimation;
import cs3500.animator.animation.concrete.DisappearAnimation;
import cs3500.animator.animation.concrete.MoveAnimation;
import cs3500.animator.animation.concrete.ScaleAnimation;
import cs3500.animator.shape.IRGBColor;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.dimension.IDimension;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Factory for producing animations.
 */
public class AnimationFactory {

  /**
   * Creates an appear animation.
   *
   * @param appearTime the time to show it at
   * @return the animation representing the appearance
   */
  public static IAnimation getAppearAnimation(int appearTime) {
    return new AppearAnimation(appearTime);
  }

  /**
   * Creates a disappear animation.
   *
   * @param disappearTime the time to remove
   * @return the animation representing the disappearance
   */
  public static IAnimation getDisappearAnimation(int disappearTime) {
    return new DisappearAnimation(disappearTime);
  }

  /**
   * Changes the color of a shape.
   *
   * @param start the shape with the original color of the shape
   * @param end the shape with the final color of the shape
   * @param startTime the time the color change starts at
   * @param endTime the time the color change finishes by
   * @return the animation representing the color change
   */
  public static IAnimation getColorAnimation(IRGBColor start, IRGBColor end, int startTime,
      int endTime) {
    return new ColorAnimation(start, end, startTime, endTime);
  }

  /**
   * Changes the position of a shape.
   *
   * @param start the shape with the original position of the shape
   * @param end the shape with the final position of the shape
   * @param startTime the time the move starts
   * @param endTime the time the move finishes by
   * @return the animation representing the move
   */
  public static IAnimation getMoveAnimation(Position2D start, Position2D end, int startTime,
      int endTime) {
    return new MoveAnimation(start, end, startTime, endTime);
  }

  /**
   * Changes the scale of the shape.
   *
   * @param start the shape with the original scale
   * @param end the shape with the final scale
   * @param startTime the time the scale shift begins
   * @param endTime the time the scale shift ends
   * @return a scale animation
   */
  public static IAnimation getScaleAnimation(IDimension start, IDimension end, int startTime,
      int endTime) {
    return new ScaleAnimation(start, end, startTime, endTime);
  }

  /**
   * Changes the name of the shape.
   *
   * @param start the shape with original name
   * @param end the shape with the final name
   * @param startTime time the change in name starts
   * @param endTime the time the name change ends
   * @return a name animation
   */
  public static IAnimation getNameAnimation(String start, String end, int startTime,
      int endTime) {
    // TODO: no needed yet...
    throw new NotImplementedException();
    //return new NameAnimation(start, end, startTime, endTime);
  }

  /**
   * General use copy constructor.
   *
   * @param copy the animation to copy
   * @return a copy of the animation
   */
  public static IAnimation copyAnimation(IAnimation copy) {
    // TODO Need this for to easily use the copy constructors through a factory method.
    throw new NotImplementedException();
  }

  /**
   * Creates an animation summary based on the given parameters.
   * @param time the start time of the animation
   * @param description the description
   * @param creationIndex the creation index
   * @return
   */
  public static IAnimationSummary getAnimationSummary(int time, String description, int creationIndex) {
    return new AnimationSummary(time, description, creationIndex);
  }

}
