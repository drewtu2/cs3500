package cs3500.animator.provider.model;

import java.util.Objects;

/**
 * Represents position (x and y).
 */
public class Posn {

  private final float x;
  private final float y;


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


  /**
   * Initialize this object to the specified position.
   */
  public Posn(float x, float y) {
    this.x = x;
    this.y = y;
  }


  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Posn)) {
      return false;
    }

    Posn that = (Posn) a;

    return ((Math.abs(this.x - that.x) < 0.01) && (Math.abs(this.y - that.y) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

}
