import static junit.framework.TestCase.assertEquals;

import Animation.AnimationFactory;
import Animation.AnimationSummary;
import Animation.IAnimation;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import shape.IAnimatedShape;
import shape.Position2D;
import shape.RGBColor;
import shape.ShapeFactory;

public class IAnimatedShapeTest {

  IAnimatedShape s;
  IAnimation appear;
  IAnimation mv1;
  IAnimation mvBad;
  IAnimation mv2;
  IAnimation r2g;
  IAnimation g2r;

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
    mv2 = AnimationFactory.getMoveAnimation(pos2, pos, 5, 7);

    r2g = AnimationFactory.getColorAnimation(red, green, 2, 4);
    g2r = AnimationFactory.getColorAnimation(green, red, 5, 7);

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
    s.addAnimation(mv1);
    s.addAnimation(r2g);

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
    assertEquals("", s.getSummary());
  }

  @Test
  public void testStateAt0() {
    s.addAnimation(appear);
    assertEquals("", s.stateAt(0));
  }

  @Test
  public void testStateAtNegativeTime() {
    s.addAnimation(appear);
    assertEquals("", s.stateAt(-1));
  }

}