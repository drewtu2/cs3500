package cs3500.animator.provider.object.animation;

import cs3500.animator.provider.object.ICanvasObject;
import cs3500.animator.provider.object.Posn;
import cs3500.animator.provider.object.shape.IShape;
import util.MyUtil;

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
  public Move(int startTime, int endTime, IShape shape, Posn destination) throws
          IllegalArgumentException {
    super(startTime, endTime, shape);
    if (destination == null) {
      throw new IllegalArgumentException(ERROR_DESTINATION_NULL);
    }
    this.destination = destination;
    this.startPosition = this.shape.getLocation();
  }

  @Override
  public void animate(IShape s) {
    s.move(this.destination);
  }

  @Override
  public void animate(int ticksElapsed) {
    if (!this.animationStarted) {
      this.startPosition = shape.getLocation();
      this.animationStarted = true;
    }
    double newX = this.startPosition.getX() * this.getStartCoef(ticksElapsed)
            + this.destination.getX() * this.getEndCoef(ticksElapsed);
    double newY = this.startPosition.getY() * this.getStartCoef(ticksElapsed)
            + this.destination.getY() * this.getEndCoef(ticksElapsed);
    shape.move(new Posn(newX, newY));
  }

  @Override
  public boolean sameType(IAnimation other) {
    return other instanceof Move;
  }

  @Override
  public String getAction(IShape s) {
    StringBuilder builder = new StringBuilder();

    builder.append("moves from ")
            .append(s.getLocation().toString())
            .append(" to ")
            .append(destination.toString());

    return builder.toString();
  }

  /**
   * Returns the destination of this animation.
   * @return the destination of this animation.
   */
  public Posn getDestination() {
    return this.destination;
  }

  }
