package cs3500.animator.view.interactive;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExportPane extends JPanel {

  private JTextField fileName;
  private JButton exportButton;

  public ExportPane() {
    super();
    this.setLayout(new FlowLayout());

    fileName = new JTextField("animation.svg", 20);
    exportButton = new JButton("Export");
    exportButton.setActionCommand("export");

    this.add(fileName);
    this.add(exportButton);


  }

  public void setListeners(ActionListener button) {
    exportButton.addActionListener(button);
  }

  public String getFilename() {
    return fileName.getText();
  }

}
