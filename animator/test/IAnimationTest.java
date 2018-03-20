import static cs3500.animator.animation.AnimationType.COLOR;
import static cs3500.animator.animation.AnimationType.CREATE;
import static cs3500.animator.animation.AnimationType.DESTROY;
import static cs3500.animator.animation.AnimationType.MOVE;
import static cs3500.animator.animation.AnimationType.SCALE;
import static junit.framework.TestCase.assertEquals;

import cs3500.animator.animation.AnimationFactory;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeFactory;
import cs3500.animator.shape.dimension.IDimension;
import cs3500.animator.shape.dimension.WidthHeightDim;
import org.junit.Before;
import org.junit.Test;

public class IAnimationTest {

  private IAnimation appear;
  private IAnimation mv1;
  private IAnimation r2g;
  private IAnimation myScale;
  private IAnimation myDisap;
  private IShape test;
  private IShape comparison;

  /**
   * Setup the test.
   */
  @Before
  public void setUp() {
    Position2D pos = new Position2D(10, 10);
    Position2D pos2 = new Position2D(20, 20);

    RGBColor red = new RGBColor(1, 0, 0);
    RGBColor green = new RGBColor(0, 1, 0);

    IDimension scale1 = new WidthHeightDim(10, 10);
    IDimension scale2 = new WidthHeightDim(20, 20);

    appear = AnimationFactory.getAppearAnimation(2);
    mv1 = AnimationFactory.getMoveAnimation(pos, pos2, 2, 4);
    r2g = AnimationFactory.getColorAnimation(green, red, 2, 4);
    myScale = AnimationFactory.getScaleAnimation(scale1, scale2, 2, 4);
    myDisap = AnimationFactory.getDisappearAnimation(2);

    test = ShapeFactory.getRectangle("rect", pos, green, 10, 10);
    comparison = ShapeFactory.getRectangle("rect", pos, green, 10, 10);
  }

  @Test
  public void testGetStartTime() {
    int two = 2;
    assertEquals(two, appear.getStartTime());
    assertEquals(two, mv1.getStartTime());
    assertEquals(two, r2g.getStartTime());
    assertEquals(two, myScale.getStartTime());
    assertEquals(two, myDisap.getStartTime());
  }

  @Test
  public void testGetEndTime() {
    int two = 2;
    int four = 4;
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

    assertEquals(comparison.toString(), test.toString());
    // Before
    appear.setState(test, 1);
    assertEquals(comparison.toString(), test.toString());

    appear.setState(test, 2);
    comparison.setOpacity(1);
    assertEquals(comparison.toString(), test.toString());

    appear.setState(test, 3);
    assertEquals(comparison.toString(), test.toString());

    mv1.setState(test, 2);
    assertEquals(comparison.toString(), test.toString());

    mv1.setState(test, 4);
    comparison.setPosition(new Position2D(20, 20));
    assertEquals(comparison.toString(), test.toString());

    r2g.setState(test, 2);
    assertEquals(comparison.toString(), test.toString());

    r2g.setState(test, 4);
    comparison.setColor(new RGBColor(1, 0, 0));
    assertEquals(comparison.toString(), test.toString());

    myScale.setState(test, 2);
    assertEquals(comparison.toString(), test.toString());
    myScale.setState(test, 4);
    comparison.setDimension(new WidthHeightDim(20, 20));
    assertEquals(comparison.toString(), test.toString());

    myDisap.setState(test, 1);
    assertEquals(comparison.toString(), test.toString());
    myDisap.setState(test, 2);
    comparison.setOpacity(0);
    assertEquals(comparison.toString(), test.toString());
  }

}