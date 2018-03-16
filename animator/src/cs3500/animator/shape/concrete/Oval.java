package cs3500.animator.shape.concrete;

import java.util.HashMap;

import cs3500.animator.shape.AbstractAnimatedShape;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeType;

/**
 * Represents an Oval object.
 */
public class Oval extends AbstractAnimatedShape {

  /**
   * Constructor for oval.
   * @param name the name
   * @param pos the position
   * @param col the color
   * @param width the width
   * @param height the height
   */
  public Oval(String name, Position2D pos, RGBColor col, float width, float height) {
    this.name = name;
    this.type = ShapeType.OVAL;
    this.position = pos;
    this.color = col;
    this.transparency = (float) 1.0;
    this.width = width;
    this.height = height;
    this.animationList = new HashMap<>();
  }

  /**
   * Copy constructor.
   * @param copy the shape to copy
   */
  public Oval(Oval copy) {
    this.name = copy.name;
    this.type = ShapeType.RECTANGLE;
    this.position = copy.position;
    this.color = copy.color;
    this.transparency = copy.transparency;
    this.width = copy.width;
    this.height = copy.height;
    this.animationList = copy.animationList;

  }

}
