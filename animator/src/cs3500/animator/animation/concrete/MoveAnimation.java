package cs3500.animator.animation.concrete;

import cs3500.animator.animation.Animation;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.shape.IPosition;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.Position2D;

/**
 * Represents an animation that changes the shapes position.
 */
public class MoveAnimation extends Animation {

  protected Position2D startPos;
  protected Position2D endPos;

  /**
   * Constructs a move animation.
   *
   * @param startPos the initial state of the shape
   * @param endPos the end state of the shape
   * @param startTime the start time
   * @param endTime the end time
   */
  public MoveAnimation(Position2D startPos, Position2D endPos, int startTime, int endTime) {

    if (endTime < startTime || startTime < 0) {
      throw new IllegalArgumentException("Invalid times");
    }
    this.creationIndex = numCreated;
    numCreated++;

    this.type = AnimationType.MOVE;
    this.startPos = startPos;
    this.endPos = endPos;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Copy Constructor.
   *
   * @param copy the animation to copy
   */
  public MoveAnimation(MoveAnimation copy) {
    this.type = copy.type;
    this.startPos = copy.startPos;
    this.endPos = copy.endPos;
    this.startTime = copy.startTime;
    this.endTime = copy.endTime;
  }

  @Override
  public void setState(IShape state, int time) {

    if (!inBounds(time)) {
      throw new IllegalArgumentException("Time out of bounds");
    }
    float currentX = this.interpolate(startPos.getX(), endPos.getX(), time);
    float currentY = this.interpolate(startPos.getY(), endPos.getY(), time);

    IPosition newPos = new Position2D(currentX, currentY);

    state.setPosition(newPos);
  }

  @Override
  public String toString(String name) {
    StringBuilder myBuilder = new StringBuilder();

    myBuilder.append("Shape ");
    myBuilder.append(name);
    myBuilder.append(" moves from ");
    myBuilder.append(startPos.toString());
    myBuilder.append(" to ");
    myBuilder.append(endPos.toString());
    myBuilder.append(" from time t=");
    myBuilder.append(startTime);
    myBuilder.append(" to time t=");
    myBuilder.append(endTime);

    return myBuilder.toString();

  }

  /**
   * Returns the start position.
   *
   * @return the start position.
   */
  public Position2D getStartPos() {
    return new Position2D(startPos);
  }

  /**
   * Returns the end position.
   *
   * @return the end position.
   */
  public Position2D getEndPos() {
    return new Position2D(endPos);
  }
}
