import static org.junit.Assert.assertEquals;

import Animation.AnimationFactory;
import java.util.ArrayList;
import java.util.List;
import model.AnimatorModel;
import model.IAnimatorModel;
import org.junit.Before;
import org.junit.Test;
import shape.IAnimatedShape;
import shape.Position2D;
import shape.RGBColor;
import shape.ShapeFactory;


public class AnimatorModelTest {

  IAnimatorModel myAnimator;
  IAnimatedShape basicSquare;
  IAnimatedShape basicSquare2;

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
            + "Lower-left-corner: (30.000000, 30.000000), Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0)\n"
            + "Animations:\n"
            + "Shape square appears at t=1.0\n"
            + "Shape square moves from (10.000000, 20.000000) to (30.000000, 50.000000) from time t=2.0 to time t=5.0\n"
            + "Shape square disappears at t=10.0\n",
        myAnimator.toString());

    myAnimator.addShape(basicSquare2);
    assertEquals("Shapes:\n"
            + "Name: square\n"
            + "Type: rectangle\n"
            + "Lower-left-corner: (30.000000, 30.000000), Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0)\n"
            + "Name: square2\n"
            + "Type: rectangle\n"
            + "Lower-left-corner: (30.000000, 30.000000), Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0)\n"
            + "Animations:\n"
            + "Shape square appears at t=1.0\n"
            + "Shape square moves from (10.000000, 20.000000) to (30.000000, 50.000000) from time t=2.0 to time t=5.0\n"
            + "Shape square disappears at t=10.0\n",
        myAnimator.toString());

    myAnimator.addAnimation("square2", AnimationFactory.getAppearAnimation(1));

    assertEquals("Shapes:\n"
            + "Name: square\n"
            + "Type: rectangle\n"
            + "Lower-left-corner: (30.000000, 30.000000), Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0)\n"
            + "Name: square2\n"
            + "Type: rectangle\n"
            + "Lower-left-corner: (30.000000, 30.000000), Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0)\n"
            + "Animations:\n"
            + "Shape square appears at t=1.0\n"
            + "Shape square2 appears at t=1.0\n"
            + "Shape square moves from (10.000000, 20.000000) to (30.000000, 50.000000) from time t=2.0 to time t=5.0\n"
            + "Shape square disappears at t=10.0\n",
        myAnimator.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingSameAnimations() {
    Position2D startPos = new Position2D(10, 20);
    Position2D endPos = new Position2D(30, 50);
    myAnimator.addShape(basicSquare);
    myAnimator.addAnimation("square", AnimationFactory.getAppearAnimation(1));
    myAnimator.addAnimation("square", AnimationFactory.getMoveAnimation(startPos, endPos, 2, 5));
    myAnimator.addAnimation("square", AnimationFactory.getMoveAnimation(startPos, endPos, 3, 6));
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
            + "Lower-left-corner: (30.000000, 30.000000), Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0)\n"
            + "Animations:\n"
            + "Shape square appears at t=1.0\n"
            + "Shape square changes color from Color: (1.0, 0.0, 0.0) to Color: (0.0, 0.0, 1.0) from time t=2.0 to time t=5.0\n"
            + "Shape square moves from (10.000000, 20.000000) to (30.000000, 50.000000) from time t=2.0 to time t=5.0\n"
            + "Shape square disappears at t=10.0\n",
        myAnimator.toString());

  }

  @Test
  public void testToStringAtTime() {
    Position2D startPos = new Position2D(10, 20);
    Position2D endPos = new Position2D(30, 50);
    RGBColor startCol = new RGBColor(1, 0, 0);
    RGBColor endCol = new RGBColor(0, 0, 1);

    myAnimator.addShape(basicSquare);
    myAnimator.addAnimation("square", AnimationFactory.getAppearAnimation(1));
    myAnimator.addAnimation("square", AnimationFactory.getMoveAnimation(startPos, endPos, 2, 5));
    myAnimator.addAnimation("square", AnimationFactory.getColorAnimation(startCol, endCol, 2, 5));
    myAnimator.addAnimation("square", AnimationFactory.getDisappearAnimation(10));

    assertEquals("", myAnimator.toString(5));
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

    assertEquals("", myAnimator.getState(5));
  }


}
