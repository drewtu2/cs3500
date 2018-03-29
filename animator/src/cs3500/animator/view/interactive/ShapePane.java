package cs3500.animator.view.interactive;

import static util.MyUtil.checkNull;

import cs3500.animator.model.IModelView;
import cs3500.animator.shape.IAnimatedShape;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

    System.out.println("Shape list model view set");

    state = mv.getFullState();
    Map<String, JCheckBox> buttons = new HashMap<>();

    JCheckBox button;
    JPanel buttonContainer = new JPanel(new FlowLayout());

    for (IAnimatedShape shape : state.values()) {
      button = new JCheckBox(shape.getName());
      button.setActionCommand(shape.getName());
      button.setSelected(true);
      buttonContainer.add(button);

      buttons.put(button.getName(), button);
      System.out.println("Added shape " + button.getName());
    }

    JScrollPane listScroller;
    listScroller = new JScrollPane(buttonContainer,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    listScroller.add(buttonContainer);


    this.setPreferredSize(new Dimension(30, 50));

    this.add(listScroller);

  }

}
