package animation.concrete;

import animation.Animation;
import animation.AnimationType;
import shape.IShape;
import shape.Position2D;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
  public MoveAnimation(Position2D startPos, Position2D endPos, float startTime, float endTime) {

    if (endTime < startTime || startTime < 0) {
      throw new IllegalArgumentException("Invalid times");
    }

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
  public void setState(IShape state, float time) {
    /*
    if (!inBounds(time)) {
      throw new IllegalArgumentException("Time out of bounds");
    }

    float startX = (float) startPos.getX();
    float startY = (float) startPos.getY();
    float endX = (float) endPos.getX();
    float endY = (float) endPos.getY();

    float deltX = (endX - startX);
    float deltY = (endY - startY);

    float deltT = duration();
    float slopeX = deltX / deltT;
    float slopeY = deltY / deltT;

    Position2D newPos = new Position2D(startX + (slopeX * time), startY + (slopeY * time));

    state.setPosition(newPos);
    */

    //TODO: See interface for explanation
    throw new NotImplementedException();
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
}
