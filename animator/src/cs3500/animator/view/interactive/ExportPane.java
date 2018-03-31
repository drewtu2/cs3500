package cs3500.animator.view.interactive;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Handles the export functionality.
 */
public class ExportPane extends JPanel {

  protected JTextField fileName;
  protected JButton exportButton;

  /**
   * Default constructor.
   */
  public ExportPane() {
    super();
    this.setLayout(new FlowLayout());

    fileName = new JTextField("animation.svg", 20);
    exportButton = new JButton("Export");
    exportButton.setActionCommand("export");

    this.add(fileName);
    this.add(exportButton);


  }

  /**
   * Set the listeners for the export button.
   *
   * @param button the listener.
   */
  public void setListeners(ActionListener button) {
    exportButton.addActionListener(button);
  }

  /**
   * Get the current state of the text field (corresponding to file name).
   *
   * @return the contents of the file name.
   */
  public String getFilename() {
    return fileName.getText();
  }

}
