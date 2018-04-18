package cs3500.animator.provider.view;

import cs3500.animator.provider.controller.IInteractiveController;

/**
 * Represents an interface to view any interactive view by receiving commands from a controller to
 * update the UI.
 */
public interface IInteractiveView extends IView {

  /**
   * Performs the actions necessary when given a controller.
   *
   * @param controller the controller to operate with
   */
  void setController(IInteractiveController controller);

  /**
   * Update the tempo label.
   *
   * @param tempo the tempo to display
   */
  void updateTempoLabel(double tempo);

  /**
   * Shows the error message for an invalid export filename.
   *
   * @param message IOexception error message
   */
  void showExportError(String message);
}
