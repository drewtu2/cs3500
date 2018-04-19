package cs3500.animator.view.interactive;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

/**
 * Information regarding the scrub state of the animation.
 */
public class ScrubberPane extends JPanel {

  public static final String name = "scrubber";

  private JSlider slider;

  /**
   * Default constructor.
   *
   * @param min minimum speed
   * @param max maximum speed
   * @param init initial speed
   */
  ScrubberPane(int min, int max, int init) {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    JLabel title = new JLabel("Tick Number");
    slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.setName(name);

    this.add(title);
    this.add(slider);
  }

  /**
   * Handle the case where the tick num is adjusted from some source that wasn't the slider. Need to
   * keep the two at the same value.
   *
   * @param tickNum the speed to set the indicator to.
   */
  public void setTickNum(int tickNum) {
    if (tickNum != slider.getValue()) {
      slider.setValue(tickNum);
    }
  }

  /**
   * Sets the scrubber maximum to a given value.
   * @param tickMax the maximum value to set the scrubber to.
   */
  public void setMaximum(int tickMax) {
    slider.setMaximum(tickMax);
    slider.setMajorTickSpacing(tickMax/10);
    slider.setMinorTickSpacing(tickMax/20);
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
