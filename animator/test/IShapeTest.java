import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import shape.IShape;
import shape.Position2D;
import shape.RGBColor;
import shape.ShapeFactory;
import shape.ShapeType;
import shape.dimension.IDimension;
import shape.dimension.RectangleDim;

public class IShapeTest {

  IShape rect;
  RGBColor red;
  Position2D pos2;
  IDimension scale1;

  @Before
  public void setUp() {
    pos2 = new Position2D(20, 20);
    red = new RGBColor(1, 0, 0);
    scale1 = new RectangleDim(10, 10);

    rect = ShapeFactory.getRectangle("rect", pos2, red, 10, 10);
  }

  @Test
  public void testSetAndGetColor() {
    RGBColor green = new RGBColor(0, 1, 0);

    assertEquals(red, rect.getColor());
    rect.setColor(green);
    assertEquals(green, rect.getColor());
  }

  @Test
  public void testSetAndGetDimension() {
    RectangleDim scale2 = new RectangleDim(20, 10);

    assertEquals(scale1, rect.getDimension());
    rect.setDimension(scale2);
    assertEquals(scale2, rect.getDimension());

  }

  @Test
  public void testSetAndGetPosition() {
    Position2D pos3 = new Position2D(20, 10);

    assertEquals(pos2, rect.getPosition());
    rect.setPosition(pos3);
    assertEquals(pos3, rect.getPosition());

  }

  @Test
  public void testSetAndGetTransparency() {

    assertEquals((float) 1.0, rect.getTransparency());
    rect.setTransparency((float) 0.0);
    assertEquals((float) 0.0, rect.getTransparency());

  }

  @Test
  public void testToString() {
    assertEquals("Name: rect\n"
            + "Type: rectangle\n"
            + "Lower-left-corner: (20.000000, 20.000000), Width: 10.0 Height: 10.0, Color: (1.0, 0.0, 0.0)",
        rect.toString());
  }

  @Test
  public void testGetType() {
    assertEquals(ShapeType.RECTANGLE, rect.getType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTransparency() {
    rect.setTransparency(-1);
  }

}