package cs3500.animator.view;

import java.util.List;

import cs3500.animator.shape.IShape;

/**
 * Interface representing the views of an animator.
 * As of now the views include text, visual, and svg.
 */
public interface IView {

  /**
   * Takes in the tempo of the animation at this time and performs view
   * implementation dependent on type of view.
   * @param tempo ticks/sec in this game
   */
  void show(int tempo);
}
