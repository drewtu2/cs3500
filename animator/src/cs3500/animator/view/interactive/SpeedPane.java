package cs3500.animator.view.interactive;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class SpeedPane extends JPanel {

  private JSlider slider;

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
   *
   * @param speed
   */
  public void setSpeed(int speed) {
    // Handle the case where the speed is adjusted from some
    // source that wasn't the slider. Need to keep the two at the same
    // value.
    if(speed != slider.getValue()) {
      slider.setValue(speed);
    }
  }

  /**
   * Adds a change listener to the slider.
   * @param l the change listener
   */
  public void setListeners(ChangeListener l) {
    slider.addChangeListener(l);
  }



}
