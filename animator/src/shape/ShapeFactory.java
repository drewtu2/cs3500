package shape;

import shape.concreteShape.Rectangle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A factory class for shapes.
 */
public class ShapeFactory {

  public static IAnimatedShape getRectangle(String name, Position2D pos, RGBColor color,
      float width, float height) {
    return new Rectangle(name, pos, color, width, height);
  }

  /**
   * Copy constructor
   */
  public static IAnimatedShape getShape(IShape shape) {
    throw new NotImplementedException();
  }

}
