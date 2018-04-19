package cs3500.animator.view.interactive;

import static util.IUtil.checkNull;

import cs3500.animator.model.IModelView;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

/**
 * Container for all of the control components.
 */
public class ControlPane extends JPanel {

  private static final int FPS_MIN = 0;
  private static final int FPS_MAX = 100;
  private static final int FPS_INIT = 1;

  private ButtonPane buttonPane;
  private ShapePane shapeList;
  private SpeedPane speedSlider;
  private LoopControl loopRadio;
  private ExportPane exportPane;
  private ScrubberPane scrubPane;

  /**
   * Default Constructor.
   */
  public ControlPane() {
    super();
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    buttonPane = new ButtonPane();
    this.add(buttonPane);

    shapeList = new ShapePane();
    this.add(shapeList);

    speedSlider = new SpeedPane(FPS_MIN, FPS_MAX, FPS_INIT);
    this.add(speedSlider);

    loopRadio = new LoopControl();
    this.add(loopRadio);

    exportPane = new ExportPane();
    this.add(exportPane);

    scrubPane = new ScrubberPane(0, 100, 0);
    this.add(scrubPane);


  }

  /**
   * Set all of the appropriate listeners.
   *
   * @param buttons the button listeners
   * @param slider the speed listener
   */
  public void setListeners(ActionListener buttons, ChangeListener slider) {
    checkNull(buttons);
    checkNull(slider);

    buttonPane.setListeners(buttons);
    loopRadio.setListeners(buttons);
    exportPane.setListeners(buttons);
    speedSlider.setListeners(slider);
    shapeList.setListeners(buttons);
    scrubPane.setListeners(slider);

  }

  /**
   * Pipe to speedSlider.
   *
   * @param speed the speed to set.
   */
  public void setSpeed(int speed) {
    speedSlider.setSpeed(speed);
  }

  /**
   * Pipe maximum value to scrubber.
   *
   * @param max the maximum value of the scrubber.
   */
  public void setScrubberMax(int max) {
    scrubPane.setMaximum(max);
  }

  /**
   * Pipe the tick number to the scrubber.
   *
   * @param tickNum the tick number to set.
   */
  public void setScrubberTick(int tickNum) {
    scrubPane.setTickNum(tickNum);
  }

  /**
   * Pipe from export pane.
   *
   * @return the file name from export pane.
   */
  public String getExportFilename() {

    return exportPane.getFilename();
  }

  /**
   * Pipe model view to shape pane.
   *
   * @param mv the model view
   */
  public void setModelView(IModelView mv) {
    shapeList.setModelView(mv);
  }
}
