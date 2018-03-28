import static junit.framework.TestCase.assertEquals;

import cs3500.animator.shape.IShape;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeFactory;
import cs3500.animator.shape.ShapeType;
import cs3500.animator.shape.dimension.IDimension;
import cs3500.animator.shape.dimension.WidthHeightDim;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the IShape interface.
 */
public class IShapeTest {

  private IShape rect;
  private RGBColor red;
  private Position2D pos2;
  private IDimension scale1;

  /**
   * Set up the test.
   */
  @Before
  public void setUp() {
    pos2 = new Position2D(20, 20);
    red = new RGBColor(1, 0, 0);
    scale1 = new WidthHeightDim(10, 10);

    rect = ShapeFactory.getRectangle("rect", pos2, red, 10, 10);
  }

  @Test
  public void testSetAndGetColor() {
    RGBColor green = new RGBColor(0, 1, 0);

    assertEquals(red, rect.getColor());
    rect.setColor(green);
    assertEquals(green, rect.getColor());
  }

  @Test(expected = NullPointerException.class)
  public void testNullColor() {
    rect.setColor(null);
  }

  @Test
  public void testSetAndGetPosition() {
    Position2D pos3 = new Position2D(20, 10);

    assertEquals(pos2, rect.getPosition());
    rect.setPosition(pos3);
    assertEquals(pos3, rect.getPosition());

  }

  @Test(expected = NullPointerException.class)
  public void testNullPos() {
    rect.setPosition(null);
  }

  @Test
  public void testSetAndGetDimension() {
    IDimension scale2 = new WidthHeightDim(20, 10);

    assertEquals(scale1, rect.getDimension());
    rect.setDimension(scale2);
    assertEquals(scale2, rect.getDimension());
  }

  @Test(expected = NullPointerException.class)
  public void testNullDim() {
    rect.setDimension(null);
  }

  @Test
  public void testSetAndGetOpacity() {
    assertEquals((float) 0.0, rect.getOpacity());
    rect.setOpacity((float) 1.0);
    assertEquals((float) 1.0, rect.getOpacity());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOpacity() {
    rect.setOpacity(-1);
  }

  @Test
  public void testGetType() {
    assertEquals(ShapeType.RECTANGLE, rect.getType());
  }

  @Test
  public void testToString() {
    assertEquals("Name: rect\n"
                    + "Type: rectangle\n"
                    + "Min-corner: (20.000000, 20.000000), "
                    + "Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0), Opacity: 0.0",
            rect.toString());
  }


}