package cs3500.animator.view.interactive;

import static util.MyUtil.checkNull;

import cs3500.animator.model.IModelView;
import cs3500.animator.shape.IAnimatedShape;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

// Useful Notes: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
/**
 * Information regarding the shapes in the animation.
 */
public class ShapePane extends JPanel {

  protected Map<String, IAnimatedShape> state;
  protected Map<String, JCheckBox> buttons;
  protected ActionListener buttonListener;

  /**
   * Default constructor.
   */
  ShapePane() {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    JLabel title = new JLabel("Shapes");
    this.add(title);
  }

  public void setModelView(IModelView mv) {
    checkNull(mv);

    state = mv.getFullState();
    buttons = new HashMap<>();

    JCheckBox button;
    JPanel buttonContainer = new JPanel();
    //buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.PAGE_AXIS));
    buttonContainer.setLayout(new GridLayout(0, 4));

    for (IAnimatedShape shape : state.values()) {
      button = new JCheckBox(shape.getName());
      button.setActionCommand(shape.getName());
      button.setSelected(true);
      button.addActionListener(buttonListener);
      buttonContainer.add(button);

      buttons.put(button.getName(), button);
    }

    JScrollPane listScroller;
    listScroller = new JScrollPane(buttonContainer,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    listScroller.setPreferredSize(new Dimension(50, 50));
    //listScroller.setAlignmentX(LEFT_ALIGNMENT);


    this.add(listScroller);
    this.setPreferredSize(new Dimension(50, 50));
    this.repaint();

  }

  public void setListeners(ActionListener l) {
    buttonListener = l;
  }

}
