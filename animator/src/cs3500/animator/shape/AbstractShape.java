package cs3500.animator.shape;

import cs3500.animator.shape.dimension.IDimension;

/**
 * Represents the discrete representation of a shape. All information pertaining to the
 * representation of the shape is stored here.
 */
public abstract class AbstractShape implements IShape {

  protected String name;
  protected ShapeType type;
  protected Position2D position;
  protected IDimension dimension;
  protected RGBColor color;
  protected float transparency;

  @Override
  public String toString() {
    StringBuilder myBuilder = new StringBuilder();

    myBuilder.append("Name: ");
    myBuilder.append(name);
    myBuilder.append("\n");
    myBuilder.append("Type: ");
    myBuilder.append(type.toString());
    myBuilder.append("\n");
    myBuilder.append(type.getRef().toString());
    myBuilder.append(": ");
    myBuilder.append(position.toString());
    myBuilder.append(", ");
    myBuilder.append(dimension.toString());
    myBuilder.append(", ");
    myBuilder.append(color.toString());

    return myBuilder.toString();

  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setColor(RGBColor color) {
    this.color = color;
  }

  @Override
  public void setPosition(Position2D pos) {
    this.position = pos;
  }

  @Override
  public void setDimension(IDimension dim) {
    this.dimension = dim;
  }

  @Override
  public void setTransparency(float input) {
    if (input < 0) {
      throw new IllegalArgumentException();
    }
    this.transparency = input;
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public RGBColor getColor() {
    return this.color;
  }

  @Override
  public Position2D getPosition() {
    return position;
  }

  @Override
  public IDimension getDimension() {
    return dimension;
  }

  @Override
  public float getTransparency() {
    return this.transparency;
  }


}
