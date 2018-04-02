package cs3500.animator.view.interactive;

import cs3500.animator.view.IView;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;

/**
 * Operaitons for an interactive view.
 */
public interface IInteractive extends IView {

  /**
   * Sets the speed of the animation.
   *
   * @param speed the speed of the animation
   */
  void setSpeed(int speed);

  /**
   * Gets the speed of the animation.
   */
  int getSpeed();

  /**
   * Start the animation.
   */
  void start();

  /**
   * Pause the animation.
   */
  void pause();

  /**
   * Reset the animation to the beginning. Leaves the animaiton in a paused state.
   */
  void reset();

  /**
   * Start running the animation after it was paused.
   */
  void resume();

  /**
   * Export the animation as an SVG to the file specified by the control panel text field.
   */
  void export();

  /**
   * Set whether or not the animation should loop.
   *
   * @param loop whether or not the animation should loop.
   */
  void setLoop(boolean loop);

  /**
   * Set the listeners for the different components.
   *
   * @param button the button listener
   * @param slider the speed change listener
   */
  void setListeners(ActionListener button, ChangeListener slider);

  /**
   * Set the given shape as enabled or disabled.
   *
   * @param name the name of the shape
   */
  void toggleShape(String name);
}
