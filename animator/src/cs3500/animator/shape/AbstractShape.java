package cs3500.animator.shape;

import static cs3500.animator.util.myUtil.checkNull;

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
  protected float opacity;

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
    myBuilder.append(", Opacity: ");
    myBuilder.append(opacity);

    return myBuilder.toString();

  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setColor(RGBColor color) {
    checkNull(color);
    this.color = color;
  }

  @Override
  public void setPosition(Position2D pos) {
    checkNull(pos);

    this.position = pos;
  }

  @Override
  public void setDimension(IDimension dim) {
    checkNull(dim);

    this.dimension = dim;
  }

  @Override
  public void setOpacity(float input) {
    if (input < 0) {
      throw new IllegalArgumentException();
    }
    this.opacity = input;
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
    return this.position;
  }

  @Override
  public IDimension getDimension() {
    return this.dimension;
  }

  @Override
  public float getOpacity() {
    return this.opacity;
  }




}
