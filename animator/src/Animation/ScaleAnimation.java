package Animation;

import shape.IShape;
import shape.ShapeFactory;

public class ScaleAnimation extends Animation {

  /**
   * Constructs a move animation
   * @param startState the initial state of the shape
   * @param endState the end state of the shape
   */
  public ScaleAnimation(IShape startState, IShape endState) {
    this.type = AnimationType.SCALE;
    this.startState = startState;
    this.endState = endState;
  }

  @Override
  public IShape getState(float time) {
    float deltT = duration();

    float deltH = endState.getHeight() - startState.getHeight();
    float deltW = endState.getWidth() - startState.getWidth();

    float slopeH = deltH/deltT;
    float slopeW = deltW/deltT;

    IShape newShape = ShapeFactory.getShape(startState);
    newShape.setHeight(startState.getHeight() + (slopeH * time));
    newShape.setWidth(startState.getWidth() + (slopeW * time));

    return newShape;
  }
}
