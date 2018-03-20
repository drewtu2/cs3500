package cs3500.animator;

import cs3500.animator.animation.AnimationFactory;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeFactory;
import cs3500.animator.view.IView;
import cs3500.animator.view.visual.VisualView;
import java.io.IOException;

public class EasyAnimator {
  public static void main(String[] args) {

    System.out.println("Showing view");
    IView myView;
    myView = new VisualView();
    System.out.println("View Displayed");

    try {
      myView.show(setup(), 1);
    } catch (IOException e) {
      System.err.println("An Exception occured...");
      System.err.println(e);
    }
    //try {
    //  IController myAnimation = new AnimatorController.Builder().buildFromInputArgs(args);
    //  myAnimation.playAnimation();
    //} catch (IOException e) {
    //  System.out.println("An IOException occured....");
    //}
  }

  public static IAnimatorModel setup() {
    IAnimatorModel model = new AnimatorModel();

    Position2D pos = new Position2D(10, 10);
    RGBColor col = new RGBColor(1, 0, 0);

    IAnimatedShape basicSquare = ShapeFactory.getRectangle("square", pos, col, 10, 10);
    model.addShape(basicSquare);
    Position2D startPos = new Position2D(10, 10);
    Position2D endPos = new Position2D(150, 150);
    model.addAnimation("square", AnimationFactory.getAppearAnimation(1));
    model.addAnimation("square", AnimationFactory.getMoveAnimation(startPos, endPos, 2, 15));
    model.addAnimation("square", AnimationFactory.getDisappearAnimation(20));

    return model;
  }
}
