package shape.concrete;

import java.util.HashMap;
import shape.AbstractAnimatedShape;
import shape.Position2D;
import shape.RGBColor;
import shape.ShapeType;
import shape.dimension.RectangleDim;

/**
 * Represents a rectangle object.
 */
public class Rectangle extends AbstractAnimatedShape {

  /**
   * Constructor for rectangle.
   * @param name the name
   * @param pos the position
   * @param col the color
   * @param width the width
   * @param height the height
   */
  public Rectangle(String name, Position2D pos, RGBColor col, float width, float height) {
    this.name = name;
    this.type = ShapeType.RECTANGLE;
    this.position = pos;
    this.color = col;
    this.transparency = (float) 1.0;
    this.dimension = new RectangleDim(width, height);
    this.animationList = new HashMap<>();
  }

  /**
   * Copy constructor.
   * @param copy the shape to copy
   */
  public Rectangle(Rectangle copy) {
    this.name = copy.name;
    this.type = ShapeType.RECTANGLE;
    this.position = copy.position;
    this.color = copy.color;
    this.transparency = copy.transparency;
    this.dimension = copy.dimension;
    this.animationList = copy.animationList;

  }

}
