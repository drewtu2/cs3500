package cs3500.animator.view.interactive;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

// Useful Notes: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
/**
 *
 */
public class ShapePane extends JPanel {

  ShapePane() {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    JLabel title = new JLabel("Shapes");
    DefaultListModel lm = new DefaultListModel();
    lm.addElement("test1");
    lm.addElement("test2");
    lm.addElement("test3");

    JList list = new JList(lm);
    list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    list.setLayoutOrientation(JList.HORIZONTAL_WRAP);

    JScrollPane listScroller = new JScrollPane(list,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    listScroller.add(list);


    this.setPreferredSize(new Dimension(30, 50));

    this.add(title);
    this.add(list);

  }

}
