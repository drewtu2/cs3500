package cs3500.animator.view.interactive;

import static util.MyUtil.checkNull;

import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

public class ControlPane extends JPanel {

  static final int FPS_MIN = 0;
  static final int FPS_MAX = 100;
  static final int FPS_INIT = 1;

  ButtonPane buttonPane;
  ShapePane shapeList;
  SpeedPane speedSlider;
  LoopControl loopRadio;
  ExportPane exportPane;

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

  public void setListeners(ActionListener buttons, ChangeListener speed) {
    checkNull(buttons);
    checkNull(speed);

    buttonPane.setListeners(buttons);
    loopRadio.setListeners(buttons);
    exportPane.setListeners(buttons);
    speedSlider.setListeners(speed);

    //shapeList.setListeners(...);

  }

  public void setSpeed(int speed) {
    speedSlider.setSpeed(speed);
  }

  public String getExportFilename() {

    return exportPane.getFilename();
  }



}
