package cs3500.animator.shape;

import cs3500.animator.shape.concrete.Oval;
import cs3500.animator.shape.concrete.Rectangle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A factory class for shapes.
 */
public class ShapeFactory {

  /**
   * Creates a rectangle.
   */
  public static IAnimatedShape getRectangle(String name, Position2D pos, RGBColor color,
      float width, float height) {
    return new Rectangle(name, pos, color, width, height);
  }

  /**
   * Creates oval.
   */
  public static IAnimatedShape getOval(String name, Position2D pos, RGBColor color,
      float width, float height) {
    return new Oval(name, pos, color, width, height);
  }

  /**
   * Copy constructor.
   */
  public static IAnimatedShape getShape(IShape shape) {
    throw new NotImplementedException();
  }

}
