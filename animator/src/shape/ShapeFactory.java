package shape;

/**
 * A factory class for shapes.
 */
public class ShapeFactory {

  /**
   * Returns a shape of a requested type with a given width and height.
   *
   * @param type the type of shape
   * @param width the width of the shape
   * @param height the height of the shape
   * @return the shape.
   * @throws UnsupportedOperationException if the given shape type doesn't support this factory
   *         method
   */
  public static IShape getShape(ShapeType type, float width, float height)
      throws UnsupportedOperationException {

    return null;
  }

  /**
   * Returns a shape of a requested type with a given width and height.
   *
   * @param type the type of shape
   * @param radius the radius of the shape
   * @return the shape.
   * @throws UnsupportedOperationException if the given shape type doesn't support this factory
   *         method
   */
  //public static IShape getShape(ShapeType type, float radius) throws UnsupportedOperationException {

  //  return null;
  //}

  /**
   * Returns a copied shape
   * @param shape
   * @return
   */
  public static IShape getShape(IShape shape) {
    return null;
  }

}
