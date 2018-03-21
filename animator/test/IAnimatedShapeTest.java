import static junit.framework.TestCase.assertEquals;

import cs3500.animator.animation.AnimationFactory;
import cs3500.animator.animation.AnimationSummary;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeFactory;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test Animated Shapes.
 */
public class IAnimatedShapeTest {

  private IAnimatedShape s;
  private IAnimation appear;
  private IAnimation mv1;
  private IAnimation mvBad;
  private IAnimation r2g;

  /**
   * Setup the test.
   */
  @Before
  public void setUp() {
    Position2D pos = new Position2D(10, 10);
    Position2D pos2 = new Position2D(20, 20);

    RGBColor red = new RGBColor(1, 0, 0);
    RGBColor green = new RGBColor(0, 1, 0);

    s = ShapeFactory.getRectangle("square", pos, red, 10, 10);

    appear = AnimationFactory.getAppearAnimation(0);
    mv1 = AnimationFactory.getMoveAnimation(pos, pos2, 2, 4);
    mvBad = AnimationFactory.getMoveAnimation(pos, pos2, 2, 4);

    r2g = AnimationFactory.getColorAnimation(red, green, 2, 4);

  }

  @Test
  public void testGoodAddAnimation() {
    s.addAnimation(mv1);
    List<AnimationSummary> sums = s.getSummary();

    List<String> expected = new ArrayList<>();
    expected.add(mv1.toString(s.getName()));

    for (int ii = 0; ii < sums.size(); ++ii) {
      assertEquals(expected.get(ii), sums.get(ii).getDescription());
    }
  }

  @Test
  public void testAddOverlapAnimationDifferentType() {
    s.addAnimation(r2g);
    s.addAnimation(mv1);

    List<AnimationSummary> sums = s.getSummary();

    List<String> expected = new ArrayList<>();
    expected.add(r2g.toString(s.getName()));
    expected.add(mv1.toString(s.getName()));

    for (int ii = 0; ii < sums.size(); ++ii) {
      assertEquals(expected.get(ii), sums.get(ii).getDescription());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOverlapAnimationSameType() {
    s.addAnimation(mv1);
    s.addAnimation(mvBad);
  }

  @Test
  public void testStateAt0() {
    s.addAnimation(appear);

    Position2D pos = new Position2D(10, 10);
    RGBColor red = new RGBColor(1, 0, 0);

    IShape s_expected = ShapeFactory.getRectangle("square", pos, red, 10, 10);
    s_expected.setOpacity(1);
    assertEquals(s_expected.toString(), s.stateAt(0).toString());
  }

  @Test
  public void testStateBeforeAnimation() {
    s.addAnimation(appear);
    s.addAnimation(mv1);

    Position2D pos = new Position2D(10, 10);
    RGBColor red = new RGBColor(1, 0, 0);

    IShape s_expected = ShapeFactory.getRectangle("square", pos, red, 10, 10);
    s_expected.setOpacity(1);
    assertEquals(s_expected.toString(), s.stateAt(1).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStateAtNegativeTime() {
    s.addAnimation(appear);
    s.stateAt(-1);
  }

  @Test(expected = NullPointerException.class)
  public void testAddNullAnimation() {
    s.addAnimation(null);
  }

}