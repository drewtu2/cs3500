package cs3500.animator.view.interactive;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPane extends JPanel {

  private JButton startButton;
  private JButton pauseButton;
  private JButton resetButton;
  private JButton resumeButton;

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

  public void setListeners(ActionListener l) {
    startButton.addActionListener(l);
    pauseButton.addActionListener(l);
    resetButton.addActionListener(l);
    resumeButton.addActionListener(l);

  }

}
