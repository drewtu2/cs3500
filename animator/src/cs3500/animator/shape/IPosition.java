package cs3500.animator.shape;

/**
 * Represents a position of a point on a plane.
 */
public interface IPosition {

  /**
   * Returns the pos x value.
   *
   * @return the pos x value
   */
  float getX();

  /**
   * Returns the pos y value.
   *
   * @return the pos y value
   */
  float getY();

  /**
   * Sets this position equal to the given copy.
   * @param copy the position to copy.
   */
  void set(IPosition copy);

}
