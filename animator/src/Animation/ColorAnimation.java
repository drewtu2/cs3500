package Animation;

import shape.IShape;
import shape.RGBColor;
import shape.ShapeFactory;

public class ColorAnimation extends Animation {

  public ColorAnimation(IShape startState, IShape endState) {
    this.type = AnimationType.COLOR;
    this.startState = startState;
    this.endState = endState;
  }

  @Override
  public IShape getState(float time) {
    RGBColor deltColor = endState.getColor().subtract(startState.getColor());

    float deltT = duration();
    int slopeR = (int)(deltColor.getRed()/duration());
    int slopeG = (int)(deltColor.getGreen()/duration());
    int slopeB = (int)(deltColor.getBlue()/duration());

    RGBColor newColor = new RGBColor(
        (int)(startState.getColor().getRed() + (slopeR * time)),
        (int)(startState.getColor().getGreen() + (slopeG * time)),
        (int)(startState.getColor().getBlue() + (slopeB * time)));

    IShape newShape = ShapeFactory.getShape(startState);
    newShape.setColor(newColor);

    return newShape;
  }
}
