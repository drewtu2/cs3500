package cs3500.animator.model.animation;

import cs3500.animator.model.Posn;
import cs3500.animator.model.shape.AbstractShape;

/**
 * Represents a translation animation to move an object to a destination location.
 */
public class Move extends AbstractAnimation {
  public static final String ERROR_DESTINATION_NULL =
          "The destination cannot be null.";

  private Posn startPosition;
  private Posn destination;

  /**
   * Constructs an move animation with a given move factor.
   *
   * @param startTime   the time to show the object
   * @param endTime     the time to hide the object
   * @param shape       the shape to apply the animation to
   * @param destination the destination to move the shape to
   * @throws IllegalArgumentException if the destination is null
   */
  public Move(int startTime, int endTime, AbstractShape shape, Posn destination) throws
          IllegalArgumentException {
  }

  /**
   * A getter for the destination.
   *
   * @return the destination posn
   */
  public Posn getDestination() {
  }

  @Override
  public void animate(AbstractShape s) {
  }

  @Override
  public void animate(int ticksElapsed) {
  }

  @Override
  public boolean sameType(AbstractAnimation other) {
  }

  @Override
  public String getAction(AbstractShape s) {
    StringBuilder builder = new StringBuilder();

    builder.append("moves from ")
            .append(s.getLocation().toString())
            .append(" to ")
            .append(destination.toString());

    return builder.toString();
  }
}
