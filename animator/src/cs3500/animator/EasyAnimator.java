package cs3500.animator;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.IController;
import java.io.IOException;

public class EasyAnimator {
  public static void main(String[] args) {
    try {
      IController myAnimation = new AnimatorController.Builder().buildFromInputArgs(args);
      myAnimation.playAnimation();
    } catch (IOException e) {
      System.out.println("An IOException occured....");
    }
  }
}
