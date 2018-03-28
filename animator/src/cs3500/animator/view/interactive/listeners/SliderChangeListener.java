package cs3500.animator.view.interactive.listeners;

import cs3500.animator.view.interactive.IInteractive;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderChangeListener implements ChangeListener {

  IInteractive view;

  public SliderChangeListener(IInteractive view) {
    this.view = view;
  }


  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    if (!source.getValueIsAdjusting()) {
      int fps = (int)source.getValue();
      view.setSpeed(fps);
    }
  }
}
