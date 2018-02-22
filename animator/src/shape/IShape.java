package shape;

public interface IShape {

  /**
   * Sets the color of a shape to a given RGB value.
   * @param color the RGB color to set the shape to
   */
  void setColor(RGBColor color);

  /**
   * Sets the position of a shape to a given value.
   * @param pos the shape.Position2D to set it to
   */
  void setPosition(Position2D pos);

  /**
   * Sets the width to a given value.
   * @param width the given width
   */
  void setWidth(float width);

  /**
   * Sets the width to a given value.
   * @param height the given height
   */
  void setHeight(float height);

  /**
   * Sets the radius to a given value.
   * @param radius the given radius
   */
  //void setRadiusX(float radius);

  /**
   * Sets the radius to a given value.
   * @param radius the given radius
   */
  //void setRadiusY(float radius);

  /**
   * Returns the color of the shape.
   * @return the color the shape
   */
  RGBColor getColor();

  /**
   * Returns the width of the shape.
   * @return the width of the shape
   */
  float getWidth();

  /**
   * Returns the height of the shape.
   * @return the height of the shape
   */
  float getHeight();

  /**
   * Returns the radius width of the shape.
   * @return the radius width of the shape
   */
  float getRadiusX();

  /**
   * Returns the radius height of the shape.
   * @return the radius height of the shape
   */
  float getRadiusY();

  /**
   * Returns the position of the shape.
   * @return the position of the shape
   */
  Position2D getPosition();

  /**
   * Returns the type of the shape.
   * @return the type of the shape
   */
  ShapeType getType();

}
