package cs3500.animator.shape.concrete;


import cs3500.animator.shape.AbstractAnimatedShape;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeType;
import cs3500.animator.shape.dimension.WidthHeightDim;
import java.util.HashMap;
import java.util.Objects;

/**
 * Represents an oval object.
 */
public class Oval extends AbstractAnimatedShape {

    /**
     * Constructor for rectangle.
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
      this.opacity = (float) 0.0;
      this.dimension = new WidthHeightDim(width, height);
      this.animationList = new HashMap<>();
    }

    /**
     * Copy constructor.
     * @param copy the shape to copy
     */
    public Oval(Oval copy) {
      this.name = copy.name;
      this.type = ShapeType.OVAL;
      this.position = copy.position;
      this.color = copy.color;
      this.opacity = copy.opacity;
      this.dimension = copy.dimension;
      this.animationList = copy.animationList;

    }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Oval)) {
      return false;
    }

    Oval that = (Oval) a;

    return ( this.name.equals(that.name)
            && this.type.equals(that.type)
            && this.position.equals(that.position)
            && this.color.equals(that.color)
            && Math.abs(this.opacity - that.opacity) < 0.01)
            && this.dimension.equals(that.dimension)
            && this.animationList.equals(that.animationList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.type, this.position, this.color,
            this.opacity, this.dimension, this.animationList);
  }
}
