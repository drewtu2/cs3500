package cs3500.animator.controller.listeners;

import static util.MyUtil.checkNull;

import cs3500.animator.view.interactive.IInteractive;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Listener for the speed change slider.
 */
public class SliderChangeListener implements ChangeListener {

  private IInteractive view;

  /**
   * Default constructor.
   */
  public SliderChangeListener(IInteractive view) {
    this.view = view;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
<<<<<<< HEAD:animator/src/cs3500/animator/view/interactive/listeners/SliderChangeListener.java
    JSlider source = (JSlider) e.getSource();
=======
    checkNull(e);
    JSlider source = (JSlider)e.getSource();
>>>>>>> 27c5db066e3f29e9532da9272e178ae2f1713bc5:animator/src/cs3500/animator/controller/listeners/SliderChangeListener.java
    if (!source.getValueIsAdjusting()) {
      int fps = (int) source.getValue();
      view.setSpeed(fps);
    }
  }
}
