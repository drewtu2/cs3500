package cs3500.animator.view.interactive;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

/**
 * Information regarding the speed of the animation.
 */
public class SpeedPane extends JPanel {

  private JSlider slider;

  /**
   * Default constructor.
   *
   * @param min minimum speed
   * @param max maximum speed
   * @param init initial speed
   */
  SpeedPane(int min, int max, int init) {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    JLabel title = new JLabel("Speed (Ticks/Second)");
    slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
    slider.setMajorTickSpacing(10);
    slider.setMinorTickSpacing(1);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);

    this.add(title);
    this.add(slider);
  }

  /**
   * Handle the case where the speed is adjusted from some source that wasn't the slider. Need to
   * keep the two at the same value.
   *
   * @param speed the speed to set the indicator to.
   */
  public void setSpeed(int speed) {
    if (speed != slider.getValue()) {
      slider.setValue(speed);
    }
  }

  /**
   * Adds a change listener to the slider.
   *
   * @param l the change listener
   */
  public void setListeners(ChangeListener l) {
    slider.addChangeListener(l);
  }


}
