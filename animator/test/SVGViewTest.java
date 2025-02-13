import static junit.framework.TestCase.assertEquals;

import cs3500.animator.animation.AnimationFactory;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeFactory;
import cs3500.animator.shape.dimension.WidthHeightDim;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the SVG View of an animator.
 */
public class SVGViewTest {

  private StringBuilder testString;
  private StringBuilder loopTestString;
  private IAnimatorModel myModel;
  private IAnimatorModel myLoopModel;
  private IView view;
  private IView loopView;

  private RGBColor red = new RGBColor(1, 0, 0);
  private RGBColor green = new RGBColor(0, 1, 0);
  private RGBColor blue = new RGBColor(0, 0, 1);

  /**
   * Setup the test.
   */
  @Before
  public void setup() {
    testString = new StringBuilder();
    loopTestString = new StringBuilder();
    myModel = new AnimatorModel();
    myLoopModel = new AnimatorModel();
    view = new SVGView(testString, false);
    loopView = new SVGView(loopTestString, true);
  }

  @Test
  public void testTxtToSVG() {
    IAnimatedShape r1 = ShapeFactory.getRectangle(
            "R",
            new Position2D(200, 200),
            red, 50, 100);

    IAnimatedShape o1 = ShapeFactory.getOval(
            "C",
            new Position2D(500, 100),
            blue, 60, 30);

    r1.setOpacity(1);
    o1.setOpacity(1);

    myModel.addShape(r1);
    myModel.addShape(o1);

    IAnimation rectMoveAnim = AnimationFactory.getMoveAnimation(r1.getPosition(),
            new Position2D(300, 200), 1, 4);

    IAnimation ovalMoveAnim = AnimationFactory.getMoveAnimation(o1.getPosition(),
            new Position2D(600, 400), 2, 5);

    myModel.addAnimation("C", AnimationFactory.getAppearAnimation(0));
    myModel.addAnimation("R", rectMoveAnim);
    myModel.addAnimation("C", ovalMoveAnim);
    myModel.addAnimation("R", AnimationFactory.getColorAnimation(red, green,
            2, 5));
    myModel.addAnimation("R", AnimationFactory.getScaleAnimation(
            new WidthHeightDim(50, 100),
            new WidthHeightDim(20, 20),
            4, 10));

    try {
      view.show(myModel, 1);
    } catch (IOException e) {
      System.out.print("invalid input");

    }
    assertEquals("<svg width=\"1000\" height=\"900\" version=\"1.1\"\n" +
            "\txmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"1.0s\" dur=\"3.0s\" "
            + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"2.0s\" dur=\"3.0s\" "
            + "attributeName=\"fill\" from=\"rgb(255,0,0)\" "
            + "to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"4.0s\" dur=\"6.0s\" "
            + "attributeName=\"width\" from=\"50\" to=\"20\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"4.0s\" dur=\"6.0s\" "
            + "attributeName=\"height\" from=\"100\" to=\"20\" fill=\"freeze\" />\n"
            +
            "</rect>\n" +
            "\n" +
            "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"0.0s\" dur=\"0s\" "
            + "attributeName=\"visibility\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"2.0s\" dur=\"3.0s\" "
            + "attributeName=\"cx\" from=\"500\" to=\"600\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"2.0s\" dur=\"3.0s\" "
            + "attributeName=\"cy\" from=\"100\" to=\"400\" fill=\"freeze\" />\n"
            +
            "</ellipse>\n" +
            "\n" +
            "</svg>\n", testString.toString());

  }


  @Test
  public void testLoopableTxtToSVG() {
    IAnimatedShape r1 = ShapeFactory.getRectangle(
            "R",
            new Position2D(200, 200),
            red, 50, 100);

    IAnimatedShape o1 = ShapeFactory.getOval(
            "C",
            new Position2D(500, 100),
            blue, 60, 30);

    r1.setOpacity(1);
    o1.setOpacity(1);

    myLoopModel.addShape(r1);
    myLoopModel.addShape(o1);

    IAnimation rectMoveAnim = AnimationFactory.getMoveAnimation(r1.getPosition(),
            new Position2D(300, 200), 1, 4);

    IAnimation ovalMoveAnim = AnimationFactory.getMoveAnimation(o1.getPosition(),
            new Position2D(600, 400), 2, 5);

    myLoopModel.addAnimation("C", AnimationFactory.getAppearAnimation(0));
    myLoopModel.addAnimation("R", rectMoveAnim);
    myLoopModel.addAnimation("C", ovalMoveAnim);
    myLoopModel.addAnimation("R", AnimationFactory.getColorAnimation(red, green,
            2, 5));
    myLoopModel.addAnimation("R", AnimationFactory.getScaleAnimation(
            new WidthHeightDim(50, 100),
            new WidthHeightDim(20, 20),
            4, 10));

    try {
      loopView.show(myLoopModel, 1);
    } catch (IOException e) {
      System.out.print("invalid input");

    }
    assertEquals("<svg width=\"1000\" height=\"900\" version=\"1.1\"\n" +
            "\txmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.begin+1.0s\" dur=\"3.0s\" "
            + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.begin+2.0s\" dur=\"3.0s\" "
            + "attributeName=\"fill\" from=\"rgb(255,0,0)\" "
            + "to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.begin+4.0s\" dur=\"6.0s\" "
            + "attributeName=\"width\" from=\"50\" to=\"20\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.begin+4.0s\" dur=\"6.0s\" "
            + "attributeName=\"height\" from=\"100\" to=\"20\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.end\" dur=\"0.5ms\" " +
            "attributeName=\"x\" to=\"200.0\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.end\" dur=\"0.5ms\" " +
            "attributeName=\"width\" to=\"50.0\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.end\" dur=\"0.5ms\" " +
            "attributeName=\"height\" to=\"100.0\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.end\" dur=\"0.5ms\" " +
            "attributeName=\"fill\" to=\"rgb(255,0,0)\" fill=\"freeze\" />\n"
            +

            "</rect>\n" +
            "\n" +
            "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.begin+0.0s\" dur=\"0s\" "
            + "attributeName=\"visibility\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.begin+2.0s\" dur=\"3.0s\" "
            + "attributeName=\"cx\" from=\"500\" to=\"600\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.begin+2.0s\" dur=\"3.0s\" "
            + "attributeName=\"cy\" from=\"100\" to=\"400\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.end\" dur=\"0.5ms\" " +
            "attributeName=\"cx\" to=\"500.0\" fill=\"freeze\" />\n"
            +
            "\t<animate attributeType=\"xml\" begin=\"base.end\" dur=\"0.5ms\" " +
            "attributeName=\"cy\" to=\"100.0\" fill=\"freeze\" />\n"
            +
            "</ellipse>\n\n"
            +
            "<rect>\n"
            +
            "\t<animate id=\"base\" begin=\"0;base.end\" dur=\"10.001"
            + "s\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
            +
            "</rect>\n\n"
            +
            "</svg>\n", loopTestString.toString());

  }
}
