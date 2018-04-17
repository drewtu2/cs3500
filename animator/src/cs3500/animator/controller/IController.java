package cs3500.animator.controller;

/**
 * Describes a controller.
 */
public interface IController {

  /**
   * Plays the described animation.
   */
  void playAnimation();

  /**
   * Closes the Controller gracefully. If the controller owns objects that need to be closed, that
   * is taken care of here.
   */
  void close();
}
