import static junit.framework.TestCase.assertEquals;

import cs3500.animator.shape.dimension.IDimension;
import cs3500.animator.shape.dimension.WidthHeightDim;
import org.junit.Before;
import org.junit.Test;

public class IDimensionTest {

  IDimension myDim;

  /**
   * Setup the test.
   */
  @Before
  public void setUp() {
    myDim = new WidthHeightDim(10, 10);
  }

  @Test
  public void testToString() {
    assertEquals("Width: 10.0 Height: 10.0", myDim.toString());

  }

  @Test
  public void testIntermediate() {
    IDimension dim2 = new WidthHeightDim(20, 20);
    IDimension result = new WidthHeightDim(15, 15);
    assertEquals(result, myDim.getIntermediate(dim2, 0, 2, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIntermediateOutOfBound() {
    IDimension dim2 = new WidthHeightDim(20, 20);
    IDimension result = new WidthHeightDim(15, 15);
    assertEquals(result, myDim.getIntermediate(dim2, 1,2, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIntermediateOutOfBoundBefore() {
    IDimension dim2 = new WidthHeightDim(20, 20);
    IDimension result = new WidthHeightDim(15, 15);
    assertEquals(result, myDim.getIntermediate(dim2, 2,4, 1));
  }
}
