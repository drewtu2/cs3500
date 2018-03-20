import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeFactory;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import org.junit.Before;
import org.junit.Test;

public class TextViewTest {

  StringBuilder testString;
  IAnimatorModel myModel;

  @Before
  public void setup() {

    myModel = new AnimatorModel();
    IView view = new TextView(testString);
  }

  @Test
  public void testAddShape()
  {

    myModel.addShape(ShapeFactory.getRectangle(
        "test",
        new Position2D(10, 10),
        new RGBColor(1, 0, 0),
        10, 10));


  }


}
