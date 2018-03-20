import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animator.animation.AnimationFactory;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;


public class AnimatorModelTest {

  IAnimatorModel myAnimator;
  IAnimatedShape basicSquare;
  IAnimatedShape basicSquare2;

  /**
   * Sets up the test.
   */
  @Before
  public void setup() {
    myAnimator = new AnimatorModel();

    Position2D pos = new Position2D(30, 30);
    RGBColor col = new RGBColor(1, 0, 0);

    basicSquare = ShapeFactory.getRectangle("square", pos, col, 10, 10);
    basicSquare2 = ShapeFactory.getRectangle("square2", pos, col, 10, 10);
  }

  // *****************************************************************
  // Test Adding Shapes
  // *****************************************************************
  @Test
  public void testTweenModelBuilder() {
    AnimatorModel.Builder builder = new AnimatorModel.Builder();
    builder.addRectangle("test", 0, 0, 10, 10, 1, 0, 0, 0, 10);
    builder.addOval("atest2", 0, 0, 10, 10, 1, 0, 0, 0, 10);

    builder.addMove("test", 0, 0, 10, 0, 1, 2);
    builder.addScaleToChange("test", 10, 10, 20, 20, 2, 4);
    builder.addColorChange("test", 1, 0, 0, 0, 1, 0, 5, 6);

    assertEquals("Shapes:\n"
            + "Name: test\n"
            + "Type: rectangle\n"
            + "Min-corner: (0.000000, 0.000000), Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0), Opacity: 0.0\n"
            + "Name: atest2\n"
            + "Type: oval\n"
            + "Center: (0.000000, 0.000000), Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0), Opacity: 0.0\n"
            + "Animations:\n"
            + "Shape test appears at t=0\n"
            + "Shape atest2 appears at t=0\n"
            + "Shape test moves from (0.000000, 0.000000) to (10.000000, 0.000000) from time t=1 to time t=2\n"
            + "Shape test scales from Width: 10.0 Height: 10.0 to Width: 20.0 Height: 20.0 from time t=2 to time t=4\n"
            + "Shape test changes color from Color: (1.0, 0.0, 0.0) to Color: (0.0, 1.0, 0.0) from time t=5 to time t=6\n"
            + "Shape test disappears at t=10\n"
            + "Shape atest2 disappears at t=10\n",
        builder.build().toString());


  }

  @Test
  public void testAddShape() {
    myAnimator.addShape(basicSquare);
    List<String> myList = new ArrayList<>();
    myList.add("square");

    assertEquals(myList.toArray(), myAnimator.listShapes().toArray());

    myList.add("square2");
    myAnimator.addShape(basicSquare2);
    assertEquals(myList.toArray(), myAnimator.listShapes().toArray());
  }

  @Test
  public void testDeleteShape() {
    myAnimator.addShape(basicSquare);
    myAnimator.deleteShape("square");

    List<String> myList = new ArrayList<>();

    assertEquals(myList.toArray(), myAnimator.listShapes().toArray());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNameAlreadyExists() {
    myAnimator.addShape(basicSquare);
    myAnimator.addShape(basicSquare);
  }

  @Test
  public void testAddAnimation() {
    Position2D startPos = new Position2D(10, 20);
    Position2D endPos = new Position2D(30, 50);
    myAnimator.addShape(basicSquare);
    myAnimator.addAnimation("square", AnimationFactory.getAppearAnimation(1));
    myAnimator.addAnimation("square", AnimationFactory.getMoveAnimation(startPos, endPos, 2, 5));
    myAnimator.addAnimation("square", AnimationFactory.getDisappearAnimation(10));

    assertEquals("Shapes:\n"
            + "Name: square\n"
            + "Type: rectangle\n"
            + "Min-corner: (30.000000, 30.000000), "
            + "Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0), Opacity: 0.0\n"
            + "Animations:\n"
            + "Shape square appears at t=1\n"
            + "Shape square moves from (10.000000, 20.000000) to "
            + "(30.000000, 50.000000) from time t=2 to time t=5\n"
            + "Shape square disappears at t=10\n",
        myAnimator.toString());

    myAnimator.addShape(basicSquare2);
    assertEquals("Shapes:\n"
            + "Name: square\n"
            + "Type: rectangle\n"
            + "Min-corner: (30.000000, 30.000000), Width: 10.0 "
            + "Height: 10.0, Color: (1.0, 0.0, 0.0), Opacity: 0.0\n"
            + "Name: square2\n"
            + "Type: rectangle\n"
            + "Min-corner: (30.000000, 30.000000), Width: 10.0 "
            + "Height: 10.0, Color: (1.0, 0.0, 0.0), Opacity: 0.0\n"
            + "Animations:\n"
            + "Shape square appears at t=1\n"
            + "Shape square moves from (10.000000, 20.000000) to "
            + "(30.000000, 50.000000) from time t=2 to time t=5\n"
            + "Shape square disappears at t=10\n",
        myAnimator.toString());

    myAnimator.addAnimation("square2", AnimationFactory.getAppearAnimation(1));

    assertEquals("Shapes:\n"
            + "Name: square\n"
            + "Type: rectangle\n"
            + "Min-corner: (30.000000, 30.000000), Width: 10.0 "
            + "Height: 10.0, Color: (1.0, 0.0, 0.0), Opacity: 0.0\n"
            + "Name: square2\n"
            + "Type: rectangle\n"
            + "Min-corner: (30.000000, 30.000000), Width: 10.0 "
            + "Height: 10.0, Color: (1.0, 0.0, 0.0), Opacity: 0.0\n"
            + "Animations:\n"
            + "Shape square appears at t=1\n"
            + "Shape square2 appears at t=1\n"
            + "Shape square moves from (10.000000, 20.000000) to "
            + "(30.000000, 50.000000) from time t=2 to time t=5\n"
            + "Shape square disappears at t=10\n",
        myAnimator.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingSameAnimations() {
    Position2D startPos = new Position2D(10, 20);
    Position2D endPos = new Position2D(30, 50);
    myAnimator.addShape(basicSquare);
    myAnimator.addAnimation("square",
        AnimationFactory.getAppearAnimation(1));
    myAnimator.addAnimation("square",
        AnimationFactory.getMoveAnimation(startPos, endPos, 2, 5));
    myAnimator.addAnimation("square",
        AnimationFactory.getMoveAnimation(startPos, endPos, 3, 6));
  }

  @Test
  public void testOverlappingDifferentAnimations() {
    Position2D startPos = new Position2D(10, 20);
    Position2D endPos = new Position2D(30, 50);
    RGBColor startCol = new RGBColor(1, 0, 0);
    RGBColor endCol = new RGBColor(0, 0, 1);

    myAnimator.addShape(basicSquare);
    myAnimator.addAnimation("square", AnimationFactory.getAppearAnimation(1));
    myAnimator.addAnimation("square", AnimationFactory.getMoveAnimation(startPos, endPos, 2, 5));
    myAnimator.addAnimation("square", AnimationFactory.getColorAnimation(startCol, endCol, 2, 5));
    myAnimator.addAnimation("square", AnimationFactory.getDisappearAnimation(10));

    assertEquals("Shapes:\n"
            + "Name: square\n"
            + "Type: rectangle\n"
            + "Min-corner: (30.000000, 30.000000), "
            + "Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0), Opacity: 0.0\n"
            + "Animations:\n"
            + "Shape square appears at t=1\n"
            + "Shape square moves from (10.000000, 20.000000) to "
            + "(30.000000, 50.000000) from time t=2 to time t=5\n"
            + "Shape square changes color from Color: (1.0, 0.0, 0.0) to "
            + "Color: (0.0, 0.0, 1.0) from time t=2 to time t=5\n"
            + "Shape square disappears at t=10\n",
        myAnimator.toString());

  }

  @Test
  public void testGetState() {
    Position2D startPos = new Position2D(10, 20);
    Position2D endPos = new Position2D(30, 50);
    RGBColor startCol = new RGBColor(1, 0, 0);
    RGBColor endCol = new RGBColor(0, 0, 1);

    myAnimator.addShape(basicSquare);
    myAnimator.addAnimation("square", AnimationFactory.getAppearAnimation(1));
    myAnimator.addAnimation("square", AnimationFactory.getMoveAnimation(startPos, endPos, 2, 5));
    myAnimator.addAnimation("square", AnimationFactory.getColorAnimation(startCol, endCol, 2, 5));
    myAnimator.addAnimation("square", AnimationFactory.getDisappearAnimation(10));

    IAnimatedShape expectedShape = ShapeFactory.getRectangle("square", endPos, endCol, 10, 10);
    expectedShape.setOpacity(1);

    assertEquals(1, myAnimator.getState(5).size());
    assertEquals(expectedShape.toString(), (myAnimator.getState(5)).get(0).toString());
  }

  @Test
  public void testGetFullState() {
    Position2D startPos = new Position2D(10, 20);
    Position2D endPos = new Position2D(30, 50);
    RGBColor startCol = new RGBColor(1, 0, 0);
    RGBColor endCol = new RGBColor(0, 0, 1);

    myAnimator.addShape(basicSquare);
    myAnimator.addAnimation("square", AnimationFactory.getAppearAnimation(1));
    myAnimator.addAnimation("square", AnimationFactory.getMoveAnimation(startPos, endPos, 2, 5));
    myAnimator.addAnimation("square", AnimationFactory.getColorAnimation(startCol, endCol, 2, 5));
    myAnimator.addAnimation("square", AnimationFactory.getDisappearAnimation(10));

    Map<String, IAnimatedShape> expectedMap = new HashMap<>();
    expectedMap.put("square", basicSquare);

    assertNotEquals(null, myAnimator.getFullState());
    assertEquals(expectedMap, myAnimator.getFullState());
  }


}
