package shape;

import shape.dimension.IDimension;

/**
 * The representation for a shape. This is the shape at a discrete point in time.
 */
public interface IShape {

  /**
   * Sets the color of a shape to a given RGB value.
   *
   * @param color the RGB color to set the shape to
   */
  void setColor(RGBColor color);

  /**
   * Sets the position of a shape to a given value.
   *
   * @param pos the shape.Position2D to set it to
   */
  void setPosition(Position2D pos);

  /**
   * Sets the dimension of a shape to a given value.
   *
   * @param dim the value to set the dimension to
   */
  void setDimension(IDimension dim);

  /**
   * Returns the color of the shape.
   *
   * @return the color the shape
   */
  RGBColor getColor();

  /**
   * Returns the position of the shape.
   *
   * @return the position of the shape
   */
  Position2D getPosition();

  /**
   * Returns the type of the shape.
   *
   * @return the type of the shape
   */
  ShapeType getType();

  /**
   * Returns the transparency of the shape.
   *
   * @return the transparency of the shape
   */
  float getTransparency();

  /**
   * Sets the transparency of the shape.
   *
   * @param input the transparency to set
   */
  void setTransparency(float input);

  /**
   * Returns the shape's name.
   *
   * @return the shape's name
   */
  String getName();

  /**
   *
   */
  String toString();

  /**
   * Return the shape's dimensions.
   *
   * @return the shape's dimensions
   */
  IDimension getDimension();
}
