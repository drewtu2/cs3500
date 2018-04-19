package cs3500.animator.shape;

import cs3500.animator.shape.concrete.Oval;
import cs3500.animator.shape.concrete.Rectangle;

/**
 * A factory class for shapes.
 */
public class ShapeFactory {

  /**
   * Creates a rectangle.
   */
  public static IAnimatedShape getRectangle(String name, IPosition pos, IRGBColor color,
      float width, float height) {
    return new Rectangle(name, pos, color, width, height);
  }

  /**
   * Creates a rectangle.
   */
  public static IAnimatedShape getRectangle(String name, IPosition pos, IRGBColor color,
      float width, float height, int rotation) {
    return new Rectangle(name, pos, color, width, height, rotation);
  }

  /**
   * Creates oval.
   */
  public static IAnimatedShape getOval(String name, IPosition pos, IRGBColor color,
      float width, float height) {
    return new Oval(name, pos, color, width, height);
  }

  /**
   * Creates oval.
   */
  public static IAnimatedShape getOval(String name, IPosition pos, IRGBColor color,
      float width, float height, int rotation) {
    return new Oval(name, pos, color, width, height, rotation);
  }

  /**
   * Copy constructor.
   */
  public static IAnimatedShape getShape(IShape shape) {
    switch (shape.getType()) {
      case OVAL:
        return new Oval((Oval) shape);
      case RECTANGLE:
        return new Rectangle((Rectangle) shape);
      default:
        throw new IllegalArgumentException("Unkown shape type");


    }
  }

}
