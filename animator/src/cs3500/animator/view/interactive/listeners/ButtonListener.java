package cs3500.animator.view.interactive.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Button Listener class taken from example code.
 */
public class ButtonListener implements ActionListener {

  Map<String, Runnable> buttonClickedActions;

  /**
   * Empty default constructor.
   */
  public ButtonListener() {
    //default empty constructor
  }

  /**
   * Set the map for key typed events. Key typed events in Java Swing are characters
   */
  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}
