package cs3500.animator.provider.adapter;

import cs3500.animator.provider.model.Color;
import cs3500.animator.provider.model.Posn;
import cs3500.animator.provider.model.shape.AbstractShape;

public class ShapeAdapter extends AbstractShape{

  /**
   * Constructs a shape with the given name and position.
   *
   * @param startTime the time to show the object
   * @param endTime the time to hide the object
   * @param name the object's name
   * @param location the object's location on a canvas
   * @param color the object's color
   * @throws IllegalArgumentException if the name, location or color is null
   */
  public ShapeAdapter(int startTime, int endTime, String name,
      Posn location, Color color)
      throws IllegalArgumentException {
    super(startTime, endTime, name, location, color);
  }

}
