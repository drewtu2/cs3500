package cs3500.animator.controller.listeners;

import static util.IUtil.checkNull;

import cs3500.animator.view.interactive.IInteractive;
import cs3500.animator.view.interactive.ScrubberPane;
import cs3500.animator.view.interactive.SpeedPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Listener for the speed change slider.
 */
public class SliderChangeListener implements ChangeListener {

  private IInteractive view;

  /**
   * Default constructor. Sets the view that is currently being manipulated.
   */
  public SliderChangeListener(IInteractive view) {
    this.view = view;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    checkNull(e);
    JSlider source = (JSlider) e.getSource();

    switch (source.getName()) {
      case SpeedPane.name:
        view.setSpeed(source.getValue());
        break;
      case ScrubberPane.name:
        if(source.getValueIsAdjusting()) {
          view.pause();
        }

        view.setTickNum(source.getValue());
        break;
      default:
        throw new IllegalArgumentException("Name not found");
    }
  }
}
