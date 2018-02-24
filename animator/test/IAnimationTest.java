import static Animation.AnimationType.COLOR;
import static Animation.AnimationType.CREATE;
import static Animation.AnimationType.DESTROY;
import static Animation.AnimationType.MOVE;
import static Animation.AnimationType.SCALE;
import static junit.framework.TestCase.assertEquals;

import Animation.AnimationFactory;
import Animation.IAnimation;
import org.junit.Before;
import org.junit.Test;
import shape.IShape;
import shape.Position2D;
import shape.RGBColor;
import shape.ShapeFactory;
import shape.dimension.IDimension;
import shape.dimension.RectangleDim;

public class IAnimationTest {

  IAnimation appear;
  IAnimation mv1;
  IAnimation r2g;
  IAnimation myScale;
  IAnimation myDisap;
  IShape test;

  @Before
  public void setUp() {
    Position2D pos = new Position2D(10, 10);
    Position2D pos2 = new Position2D(20, 20);

    RGBColor red = new RGBColor(1, 0, 0);
    RGBColor green = new RGBColor(0, 1, 0);

    IDimension scale1 = new RectangleDim(10, 10);
    IDimension scale2 = new RectangleDim(20, 20);

    appear = AnimationFactory.getAppearAnimation(2);
    mv1 = AnimationFactory.getMoveAnimation(pos, pos2, 2, 4);
    r2g = AnimationFactory.getColorAnimation(red, green, 2, 4);
    myScale = AnimationFactory.getScaleAnimation(scale1, scale2, 2, 4);
    myDisap = AnimationFactory.getDisappearAnimation(2);

    test = ShapeFactory.getRectangle("rect", pos, green, 10, 10);
  }

  @Test
  public void testGetStartTime() {
    float two = (float) 2.0;
    assertEquals(two, appear.getStartTime());
    assertEquals(two, mv1.getStartTime());
    assertEquals(two, r2g.getStartTime());
    assertEquals(two, myScale.getStartTime());
    assertEquals(two, myDisap.getStartTime());
  }

  @Test
  public void testGetEndTime() {
    float two = (float) 2.0;
    float four = (float) 4.0;
    assertEquals(two, appear.getEndTime());
    assertEquals(four, mv1.getEndTime());
    assertEquals(four, r2g.getEndTime());
    assertEquals(four, myScale.getEndTime());
    assertEquals(two, myDisap.getEndTime());
  }

  @Test
  public void testGetType() {
    assertEquals(CREATE, appear.getType());
    assertEquals(MOVE, mv1.getType());
    assertEquals(COLOR, r2g.getType());
    assertEquals(SCALE, myScale.getType());
    assertEquals(DESTROY, myDisap.getType());
  }

  @Test
  public void testInBounds() {
    assertEquals(true, appear.inBounds(2));
    assertEquals(false, appear.inBounds(3));
    assertEquals(true, mv1.inBounds(3));
    assertEquals(false, mv1.inBounds(5));
    assertEquals(true, r2g.inBounds(3));
    assertEquals(false, r2g.inBounds(5));
    assertEquals(true, myScale.inBounds(3));
    assertEquals(false, myScale.inBounds(5));
    assertEquals(true, myDisap.inBounds(2));
    assertEquals(false, myDisap.inBounds(3));
  }

  @Test
  public void testSetState() {

    // Before
    appear.setState(test, 1);
    assertEquals("", test.toString());

    appear.setState(test, 2);
    assertEquals("", test.toString());

    appear.setState(test, 3);
    assertEquals("", test.toString());

    mv1.setState(test, 1);
    assertEquals("", test.toString());
    mv1.setState(test, 2);
    assertEquals("", test.toString());
    mv1.setState(test, 4);
    assertEquals("", test.toString());
    mv1.setState(test, 5);
    assertEquals("", test.toString());

    r2g.setState(test, 1);
    assertEquals("", test.toString());
    r2g.setState(test, 2);
    assertEquals("", test.toString());
    r2g.setState(test, 4);
    assertEquals("", test.toString());
    r2g.setState(test, 5);
    assertEquals("", test.toString());

    myScale.setState(test, 1);
    assertEquals("", test.toString());
    myScale.setState(test, 2);
    assertEquals("", test.toString());
    myScale.setState(test, 4);
    assertEquals("", test.toString());
    myScale.setState(test, 5);

    myDisap.setState(test, 1);
    assertEquals("", test.toString());
    myDisap.setState(test, 2);
    assertEquals("", test.toString());
    myDisap.setState(test, 3);
    assertEquals("", test.toString());
  }

}