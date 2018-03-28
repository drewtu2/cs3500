package cs3500.animator.view.interactive;

import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Panel for determining if this the animation is going to loop or not.
 */
public class LoopControl extends JPanel {

  protected JRadioButton dontLoop;
  protected JRadioButton doLoop;

  /**
   * Default Constructor.
   */
  LoopControl() {
    super();

    dontLoop = new JRadioButton("Don't Loop");
    dontLoop.setActionCommand("dont loop");
    dontLoop.setSelected(true);

    doLoop = new JRadioButton("Loop");
    doLoop.setActionCommand("loop");

    ButtonGroup group = new ButtonGroup();
    group.add(dontLoop);
    group.add(doLoop);

    this.add(dontLoop);
    this.add(doLoop);
  }

  /**
   * Set the action listener for the buttons.
   *
   * @param l the listener
   */
  public void setListeners(ActionListener l) {
    dontLoop.addActionListener(l);
    doLoop.addActionListener(l);
  }

}
