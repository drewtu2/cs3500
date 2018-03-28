package cs3500.animator.view.interactive;

import static util.MyUtil.checkNull;

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
  }

  /**
   * Set all of the appropriate listeners.
   *
   * @param buttons the button listeners
   * @param speed the speed listener
   */
  public void setListeners(ActionListener buttons, ChangeListener speed) {
    checkNull(buttons);
    checkNull(speed);

    buttonPane.setListeners(buttons);
    loopRadio.setListeners(buttons);
    exportPane.setListeners(buttons);
    speedSlider.setListeners(speed);

    //shapeList.setListeners(...);

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
   * Pipe from export pane.
   *
   * @return the file name from export pane.
   */
  public String getExportFilename() {

    return exportPane.getFilename();
  }


}
