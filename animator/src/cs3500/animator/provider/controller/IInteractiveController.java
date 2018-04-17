package cs3500.animator.provider.controller;

import cs3500.animator.provider.object.shape.IShape;

/**
 * Represents an interface to control any interactive view by receiving commands from the view and
 * the user and provides the view with updates on the UI state.
 */
public interface IInteractiveController extends IVisualController {

  /**
   * Handles when a start action is requested.
   */
  void onStartClicked();

  /**
   * Handles when a pause action is requested.
   */
  void onPauseClicked();

  /**
   * Handles when a resume action is requested.
   */
  void onResumeClicked();

  /**
   * Handles when a restart action is requested.
   */
  void onRestartClicked();

  /**
   * Handles when the looping checkbox is checked or not checked.
   *
   * @param selected whether or not the checkbox is checked
   */
  void onLoopingChanged(boolean selected);

  /**
   * Handles when the slider is moved.
   *
   * @param tempo the tempo of the animation
   */
  void onTempoChanged(double tempo);

  /**
   * Handles when a shape checkbox is selected.
   *
   * @param shape the shape to change the visibility of
   * @param selected whether or not the checkbox is selected
   */
  void onShapeVisibilityChanged(IShape shape, boolean selected);

  /**
   * Handles when the SVG export button is pressed.
   *
   * @param filename the name of the file being written
   */
  void onExportClicked(String filename);
}
