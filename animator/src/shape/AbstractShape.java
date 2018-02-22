package shape;

public abstract class AbstractShape implements IShape{

  protected ShapeType type;
  protected RGBColor color;
  protected Position2D position;

  protected float width;
  protected float height;
  protected float radiusX;
  protected float radiusY;


  @Override
  public void setColor(RGBColor color) {
    this.color = color;
  }

  @Override
  public void setPosition(Position2D pos) {
    this.position = pos;
  }

  @Override
  public void setWidth(float width) {
    this.width = width;
  }

  @Override
  public void setHeight(float height) {
    this.height = height;
  }

  @Override
  public void setRadiusX(float rx) {
    this.radiusX = rx;
  }

  @Override
  public void setRadiusY(float ry) {
    this.radiusY = ry;
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
  public float getWidth() {
    return this.width;
  }

  @Override
  public float getHeight() {
    return this.height;
  }

  @Override
  public float getRadiusX() {
    return this.radiusX;
  }

  @Override
  public float getRadiusY() {
    return this.radiusY;
  }


}
