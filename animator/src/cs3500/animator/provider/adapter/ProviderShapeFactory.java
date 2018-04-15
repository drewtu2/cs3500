package cs3500.animator.provider.adapter;

import cs3500.animator.provider.object.IColor;
import cs3500.animator.provider.object.Posn;
import cs3500.animator.provider.object.shape.IShape;
import cs3500.animator.provider.object.shape.Oval;
import cs3500.animator.provider.object.shape.Rectangle;
import cs3500.animator.shape.ShapeType;

public class ProviderShapeFactory {

  /**
   * Creates an abstract shape of the given type with the appropriate parameters.
   * @param type the type of shape
   * @param name the unique id of the shape
   * @param startTick the start tick
   * @param endTick the end tick
   * @param thisPosn the shapes position
   * @param thisColor the color of the shape
   * @param width the width fo the shape
   * @param height the height of the shape
   * @return an abstract shape of the providers type
   */
  public static IShape getShape(ShapeType type, String name, int startTick, int endTick, Posn thisPosn,
      IColor thisColor, float width, float height)
  {
    switch(type) {
      case RECTANGLE:
        return new Rectangle(startTick, endTick, name, thisPosn, thisColor, width, height);
      case OVAL:
        return new Oval(startTick, endTick, name, thisPosn, thisColor, width, height);
      default:
        throw new IllegalArgumentException("baaaadddd type");
    }
  }
}
