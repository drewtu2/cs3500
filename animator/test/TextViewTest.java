import static junit.framework.TestCase.assertEquals;

import cs3500.animator.animation.AnimationFactory;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeFactory;
import cs3500.animator.shape.dimension.WidthHeightDim;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class TextViewTest {

  private StringBuilder testString;
  private IAnimatorModel myModel;
  private IView view;

  private RGBColor red = new RGBColor(1, 0, 0);
  private RGBColor green = new RGBColor(0, 1, 0);
  private RGBColor blue = new RGBColor(0, 0, 1);

  @Before
  public void setup() {
    testString = new StringBuilder();
    myModel = new AnimatorModel();
    view = new TextView(testString);


  }

  @Test
  public void testAddShape() {

    myModel.addShape(ShapeFactory.getRectangle(
        "test",
        new Position2D(10, 10),
        red,
        10, 10));
    myModel.addAnimation("test", AnimationFactory.getAppearAnimation(0));
    myModel.addAnimation("test", AnimationFactory.getMoveAnimation(
        new Position2D(10, 10),
        new Position2D(50, 50),
        1, 10));
    myModel.addAnimation("test", AnimationFactory.getColorAnimation(red, green, 2, 5));
    myModel.addAnimation("test", AnimationFactory.getScaleAnimation(
        new WidthHeightDim(10, 10),
        new WidthHeightDim(20, 20),
        4, 10));

    myModel.addShape(ShapeFactory.getOval("test2",
        new Position2D(20, 20),
        blue, 20, 20));
    myModel.addAnimation("test2", AnimationFactory.getAppearAnimation(0));
    myModel.addAnimation("test2", AnimationFactory.getMoveAnimation(
        new Position2D(10, 10),
        new Position2D(50, 50),
        1, 10));
    myModel.addAnimation("test2", AnimationFactory.getColorAnimation(red, green, 2, 5));
    myModel.addAnimation("test2", AnimationFactory.getScaleAnimation(
        new WidthHeightDim(10, 10),
        new WidthHeightDim(20, 20),
        4, 10));

    try {
      view.show(myModel, 1);
    } catch (IOException e) {
      // meh...
    }
    assertEquals("Shapes:\n"
            + "Name: test\n"
            + "Type: rectangle\n"
            + "Min-corner: (10.000000, 10.000000), Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0), Opacity: 0.0\n"
            + "\n"
            + "Name: test2\n"
            + "Type: oval\n"
            + "Center: (20.000000, 20.000000), Width: 20.0 Height: 20.0, Color: (0.0, 0.0, 1.0), Opacity: 0.0\n"
            + "\n"
            + "\n"
            + "Animations:\n"
            + "Shape test Appears at t=0.0s\n"
            + "Shape test2 Appears at t=0.0s\n"
            + "Shape test moves from (10.000000, 10.000000) to (50.000000, 50.000000) from t=1.0s to t=10.0s\n"
            + "Shape test2 moves from (10.000000, 10.000000) to (50.000000, 50.000000) from t=1.0s to t=10.0s\n"
            + "Shape test changes from Color: (1.0, 0.0, 0.0) to Color: (0.0, 1.0, 0.0) from t=2.0s to t=5.0s\n"
            + "Shape test2 changes from Color: (1.0, 0.0, 0.0) to Color: (0.0, 1.0, 0.0) from t=2.0s to t=5.0s\n"
            + "Shape test scales from Width: 10.0 Height: 10.0 to Width: 20.0 Height: 20.0 from t=4.0s to t=10.0s\n"
            + "Shape test2 scales from Width: 10.0 Height: 10.0 to Width: 20.0 Height: 20.0 from t=4.0s to t=10.0s\n",
        testString.toString());
  }


}
