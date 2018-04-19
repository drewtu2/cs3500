package cs3500.animator.shape;

import static util.IUtil.checkNull;

import cs3500.animator.shape.dimension.IDimension;
import java.util.Objects;

/**
 * Represents the discrete representation of a shape. All information pertaining to the
 * representation of the shape is stored here.
 */
public abstract class AbstractShape implements IShape {

  protected String name;
  protected ShapeType type;

  protected IPosition position;
  protected IDimension dimension;
  protected IRGBColor color;
  protected float opacity;
  protected int rotation;

  protected IPosition originalPosition;
  protected IDimension originalDimension;
  protected IRGBColor originalColor;
  protected int originalRotation;
  protected float originalOpacity;

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
    myBuilder.append("\nRotation: ");
    myBuilder.append(rotation);

    return myBuilder.toString();

  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setColor(IRGBColor color) {
    checkNull(color);
    this.color = color;
  }

  @Override
  public void setPosition(IPosition pos) {
    checkNull(pos);

    this.position = pos;
  }

  @Override
  public void setRotation(int rotation) {
    this.rotation = rotation;
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
  public IRGBColor getColor() {
    return this.color;
  }

  @Override
  public IPosition getPosition() {
    return this.position;
  }

  @Override
  public IDimension getDimension() {
    return this.dimension;
  }

  @Override
  public int getRotation() { return this.rotation; }

  @Override
  public float getOpacity() {
    return this.opacity;
  }

  @Override
  public void setState(IShape state) {
    if (this.type != state.getType()) {
      throw new IllegalArgumentException("Shapes need to be the same type");
    }

    this.opacity = state.getOpacity();
    this.position = state.getPosition();
    this.color = state.getColor();
    this.dimension = state.getDimension();
    this.rotation = state.getRotation();
  }

  @Override
  public void reset() {
    this.color.set(this.originalColor);
    this.position.set(this.originalPosition);
    this.dimension.set(this.originalDimension);
    this.rotation = originalRotation;

  }

  @Override
  public IAnimatedShape makeCopy() {
    return ShapeFactory.getShape(this);
  }

  @Override
  public boolean equals(Object compare) {
    boolean bType = this.type.equals(((IShape) compare).getType());
    boolean bDim = this.dimension.equals(((IShape) compare).getDimension());
    boolean bCol = this.color.equals(((IShape) compare).getColor());
    boolean bPos = this.position.equals(((IShape) compare).getPosition());
    boolean bName = this.name.equals(((IShape) compare).getName());
    boolean bOpacity = this.opacity == (((IShape) compare).getOpacity());
    boolean bRotation = this.rotation == ((IShape) compare).getRotation();

    return bType && bCol && bDim && bPos && bName && bOpacity && bRotation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        this.type,
        this.dimension,
        this.color,
        this.name,
        this.opacity,
        this.type,
        this.position,
        this.rotation);
  }

}
