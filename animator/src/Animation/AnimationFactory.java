package animation;

import animation.concrete.AppearAnimation;
import animation.concrete.ColorAnimation;
import animation.concrete.DisappearAnimation;
import animation.concrete.MoveAnimation;
import animation.concrete.ScaleAnimation;
import shape.Position2D;
import shape.RGBColor;
import shape.dimension.IDimension;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AnimationFactory {

  /**
   * Creates an appear animation.
   *
   * @param appearTime the time to show it at
   * @return the animation representing the appearance
   */
  public static IAnimation getAppearAnimation(float appearTime) {
    return new AppearAnimation(appearTime);
  }

  /**
   * Creates a disappear animation.
   *
   * @param disappearTime the time to remove
   * @return the animation representing the disappearance
   */
  public static IAnimation getDisappearAnimation(float disappearTime) {
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
  public static IAnimation getColorAnimation(RGBColor start, RGBColor end, float startTime,
      float endTime) {
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
  public static IAnimation getMoveAnimation(Position2D start, Position2D end, float startTime,
      float endTime) {
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
  public static IAnimation getScaleAnimation(IDimension start, IDimension end, float startTime,
      float endTime) {
    return new ScaleAnimation(start, end, startTime, endTime);
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

}
