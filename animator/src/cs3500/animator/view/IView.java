package cs3500.animator.view;

import cs3500.animator.model.IModelView;

/**
 * Interface representing the views of an animator.
 * As of now the views include text, visual, and svg.
 */
public interface IView {

  /**
   * Takes in the tempo of the animation at this time and performs view
   * implementation dependent on type of view.
   * @param state the model view of current state
   * @param tempo ticks/sec in this game
   */
  void show(IModelView state, int tempo);
}
