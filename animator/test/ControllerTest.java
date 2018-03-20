import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.AnimatorController.Builder;
import cs3500.animator.controller.IController;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

  AnimatorController.Builder builder;

  String[] goodArgs;
  String[] badFlag;
  String[] badInputFile;
  String[] noInputFile;
  String[] noView;
  String[] badView;
  String[] badSpeed;
  String[] missingArgument;

  @Before
  public void setup() {

    builder = new Builder();
    goodArgs = new String[]{"-if", "assignment6_starter/toh-3.txt", "-iv", "visual", "-speed", "20", "-o", "out.txt"};
    badInputFile = new String[]{"-if", "test.tx", "-iv", "visual", "-speed", "20", "-o", "out.txt"};
    noInputFile = new String[]{"-iv", "visual", "-speed", "20", "-o", "out.txt"};
    noView = new String[]{"-if", "test.tx", "-speed", "20", "-o", "out.txt"};
    badFlag = new String[]{"-id", "test.tx", "-iv", "visual", "-speed", "20", "-o", "out.txt"};
    badView = new String[]{"-if", "test.tx", "-iv", "vis", "-speed", "20", "-o", "out.txt"};
    badSpeed = new String[]{"-if", "test.tx", "-iv", "vis", "-speed", "-20", "-o", "out.txt"};
    missingArgument = new String[]{"-if", "test.tx", "-iv", "vis", "-speed", "-20", "-o"};
}

  @Test
  public void testBuilder() {

    IController myController;
    try {
      myController = builder.buildFromInputArgs(goodArgs);
      assertEquals(null, myController);
    } catch (IOException e) {
      // meh..
      fail("controller not equal");
    }
  }

  @Test(expected = FileNotFoundException.class)
  public void testInputFileNotExist() {

    try {
      builder.buildFromInputArgs(badInputFile);
    } catch (IOException e) {
      // meh..
      fail("controller not equal");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoInputFile() {

    try {
      builder.buildFromInputArgs(badInputFile);
    } catch (IOException e) {
      // meh..
      fail("controller not equal");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoView() {

    try {
      builder.buildFromInputArgs(noView);
    } catch (IOException e) {
      // meh..
      fail("controller not equal");
    }
  }

}
