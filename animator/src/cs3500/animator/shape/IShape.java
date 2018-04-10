package cs3500.animator.shape;

import cs3500.animator.shape.dimension.IDimension;

/**
 * The representation for a shape. This is the shape at a discrete point in time.
 */
public interface IShape {

  /**
   * Sets the color of a shape to a given RGB value.
   *
   * @param color the RGB color to set the shape to
   */
  void setColor(IRGBColor color);

  /**
   * Returns the color of the shape.
   *
   * @return the color the shape
   */
  IRGBColor getColor();

  /**
   * Sets the position of a shape to a given value.
   *
   * @param pos the Position2D to set it to
   */
  void setPosition(IPosition pos);

  /**
   * Returns the position of the shape.
   *
   * @return the position of the shape
   */
  IPosition getPosition();

  /**
   * Sets the dimension of a shape to a given value.
   *
   * @param dim the value to set the dimension to
   */
  void setDimension(IDimension dim);

  /**
   * Return the shape's dimensions.
   *
   * @return the shape's dimensions
   */
  IDimension getDimension();

  /**
   * Returns the type of the shape.
   *
   * @return the type of the shape
   */
  ShapeType getType();

  /**
   * Sets the opacity of the shape. 0: invisible 1: visible
   *
   * @param input the opacity to set
   */
  void setOpacity(float input);

  /**
   * Returns the opacity of the shape.
   *
   * @return the opacity of the shape
   */
  float getOpacity();

  /**
   * Sets the state of the shape to the same properties as a given state.
   *
   * @param state the state to duplicate
   */
  void setState(IShape state);

  /**
   * Returns the shape's name.
   *
   * @return the shape's name
   */
  String getName();

  /**
   * The string representation.
   *
   * @return the string representation
   */
  String toString();

}
