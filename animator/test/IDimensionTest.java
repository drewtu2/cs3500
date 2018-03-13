import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import shape.dimension.IDimension;
import shape.dimension.RectangleDim;

public class IDimensionTest {

  IDimension myDim;

  /**
   * Setup the test.
   */
  @Before
  public void setUp() {
    myDim = new RectangleDim(10, 10);
  }

  @Test
  public void testToString() {
    assertEquals("Width: 10.0 Height: 10.0", myDim.toString());

  }

  @Test
  public void testIntermediate() {
    IDimension dim2 = new RectangleDim(20, 20);
    IDimension result = new RectangleDim(15, 15);
    assertEquals(result, myDim.getIntermediate(dim2, 2, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIntermediateOutOfBound() {
    IDimension dim2 = new RectangleDim(20, 20);
    IDimension result = new RectangleDim(15, 15);
    assertEquals(result, myDim.getIntermediate(dim2, 2, 3));
  }
}
