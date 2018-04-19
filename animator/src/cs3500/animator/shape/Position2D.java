package cs3500.animator.shape;

import java.util.Objects;

/**
 * This class represents a 2D position. **Taken from lecture notes.**
 */
public class Position2D implements IPosition {

  private float x;
  private float y;


  public float getX() {
    return x;
  }

  /**
   * Returns the pos y value.
   *
   * @return the pos y value
   */
  public float getY() {
    return y;
  }

  @Override
  public void set(IPosition copy) {
    this.x = copy.getX();
    this.y = copy.getY();
  }


  /**
   * Initialize this object to the specified position.
   */
  public Position2D(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor.
   */
  public Position2D(IPosition v) {
    this(((Position2D) v).x, ((Position2D) v).y);
  }

  @Override
  public String toString() {
    return String.format("(%f, %f)", this.x, this.y);
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Position2D)) {
      return false;
    }

    Position2D that = (Position2D) a;

    return ((Math.abs(this.x - that.x) < 0.01) && (Math.abs(this.y - that.y) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }
}
