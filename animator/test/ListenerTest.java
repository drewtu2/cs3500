import static junit.framework.TestCase.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.model.IModelView;
import cs3500.animator.view.interactive.IInteractive;
import cs3500.animator.view.interactive.InteractiveView;
import cs3500.animator.view.interactive.listeners.ButtonListener;
import cs3500.animator.view.interactive.listeners.SliderChangeListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the listeners.
 */
public class ListenerTest {

  protected IInteractive view;
  protected IAnimatorModel model;
  protected ChangeListener cl;
  protected ButtonListener al;


  /**
   * Setup the test code.
   */
  @Before
  public void setup() {
    view = new InteractiveView();
    model = new AnimatorModel();
    try {
      view.show((IModelView) model, 1);
    } catch (IOException e) {
      // Do nohting
    }
    cl = new SliderChangeListener(view);
    al = new ButtonListener();
  }

  @Test
  public void testCl() {
    JSlider slider;
    slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 7);
    slider.setMajorTickSpacing(10);
    slider.setMinorTickSpacing(1);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);

    ChangeEvent ce = new ChangeEvent(slider);

    cl.stateChanged(ce);

    assertEquals(7, view.getSpeed());

  }

  @Test
  public void TestAl() {

    JButton button = new JButton("test");
    button.setActionCommand("test");

    ActionEvent ae = new ActionEvent(button, 1, "test");

    Map<String, Runnable> mp = new HashMap<>();
    mp.put("test", () -> view.setSpeed(10));

    al.setButtonClickedActionMap(mp);

    al.actionPerformed(ae);

    assertEquals(10, view.getSpeed());


  }

}
