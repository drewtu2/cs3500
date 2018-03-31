package cs3500.animator.view.interactive;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class representing the start, pause, reset, resume button features.
 */
public class ButtonPane extends JPanel {

  private JButton startButton;
  private JButton pauseButton;
  private JButton resetButton;
  private JButton resumeButton;

  /**
   * Default Constructor.
   */
  ButtonPane() {
    setLayout(new FlowLayout());

    startButton = new JButton("Start");
    startButton.setActionCommand("start");
    this.add(startButton);

    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("pause");
    this.add(pauseButton);

    resumeButton = new JButton("Resume");
    resumeButton.setActionCommand("resume");
    this.add(resumeButton);

    resetButton = new JButton("Reset");
    resetButton.setActionCommand("reset");
    this.add(resetButton);

  }

  /**
   * Set the listeners for these buttons.
   *
   * @param l the listener for all four buttons
   */
  public void setListeners(ActionListener l) {
    startButton.addActionListener(l);
    pauseButton.addActionListener(l);
    resetButton.addActionListener(l);
    resumeButton.addActionListener(l);

  }

}
