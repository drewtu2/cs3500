package cs3500.animator;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.IController;
import java.io.IOException;

/**
 * Easy Animator main class.
 */
public class EasyAnimator {

  /**
   * The main method.
   * @param args input args.
   */
  public static void main(String[] args) {

    try {
      IController myAnimation = new AnimatorController.Builder().buildFromInputArgs(args);
      myAnimation.playAnimation();
    } catch (IOException e) {
      System.out.println("An IOException occured....");
    }

    //System.out.println("Showing view");
    //IView myView;
    //myView = new VisualView();
    //System.out.println("View Displayed");

    //try {
    //  myView.show(setup(), 1);
    //} catch (IOException e) {
    //  System.err.println("An Exception occured...");
    //  System.err.println(e);
    //}
  }

  //public static IAnimatorModel setup() {
  //  IAnimatorModel model = new AnimatorModel();

  //  Position2D pos = new Position2D(10, 10);
  //  RGBColor col = new RGBColor(1, 0, 0);

  //  IAnimatedShape basicSquare = ShapeFactory.getRectangle("square", pos, col, 10, 10);
  //  model.addShape(basicSquare);
  //  Position2D startPos = new Position2D(10, 10);
  //  Position2D endPos = new Position2D(150, 150);
  //  model.addAnimation("square", AnimationFactory.getAppearAnimation(1));
  //  model.addAnimation("square", AnimationFactory.getMoveAnimation(startPos, endPos, 2, 15));
  //  model.addAnimation("square", AnimationFactory.getDisappearAnimation(20));
  //  return model;
  //}
}
