package Animation;

import shape.IShape;
import shape.Position2D;
import shape.ShapeFactory;

/**
 * Represents an animation that changes the shapes position
 */
public class MoveAnimation extends Animation{


  /**
   * Constructs a move animation
   * @param startState the initial state of the shape
   * @param endState the end state of the shape
   */
  public MoveAnimation(IShape startState, IShape endState) {
    this.type = AnimationType.MOVE;
    this.startState = startState;
    this.endState = endState;
  }

  @Override
  public IShape getState(float time) {
    float startX = (float) startState.getPosition().getX();
    float startY = (float) startState.getPosition().getY();
    float endX = (float) endState.getPosition().getX();
    float endY = (float) endState.getPosition().getY();

    float deltX = (endX - startX);
    float deltY = (endY - startY);

    float deltT = duration();
    float slopeX = deltX/deltT;
    float slopeY = deltY/deltT;

    Position2D newPos = new Position2D(startX + (slopeX * time), startY + (slopeY * time));

    IShape newShape = ShapeFactory.getShape(startState);

    newShape.setPosition(newPos);

    return newShape;
  }
}
