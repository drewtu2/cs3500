import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.AnimatorController.Builder;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
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
    noView = new String[]{"-if", "assignment6_starter/toh-3.txt", "-speed", "20", "-o", "out.txt"};
    badFlag = new String[]{"-id", "assignment6_starter/toh-3.txt", "-iv", "visual", "-speed", "20", "-o", "out.txt"};
    badView = new String[]{"-if", "assignment6_starter/toh-8.txt", "-iv", "vis", "-speed", "20",
            "-o", "out.txt"};
    badSpeed = new String[]{"-if", "assignment6_starter/toh-8.txt", "-iv", "visual", "-speed",
            "-20", "-o", "out.txt"};
    missingArgument = new String[]{"-if", "assignment6_starter/toh-3.txt", "-iv", "visual", "-speed", "20", "-o"};
}

  @Test
  public void testBuilder() {

    IController myController;
    IController testValController;
    try {
      AnimationFileReader mr = new AnimationFileReader();
      AnimatorModel.Builder mb = new AnimatorModel.Builder();
      IAnimatorModel myModel = mr.readFile("assignment6_starter/toh-3.txt", mb);
      IView myView = ViewFactory.getView("visual", "out.txt");
      int speed = 20;
      testValController = new AnimatorController(myModel, myView, speed);
      myController = builder.buildFromInputArgs(goodArgs);
      assertEquals(testValController, myController);
    }
    catch(IOException e) {
      // someting
      //fail("controller not equal");
    }
  }

  @Test(expected = IOException.class)
  public void testInputFileNotExist() throws IOException {
      builder.buildFromInputArgs(badInputFile);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoInputFileParam() {

    try {
      builder.buildFromInputArgs(noInputFile);
      fail("no input file detected");
    } catch (IOException e) {
      //fail("no input file detected");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoView() throws IOException{

      builder.buildFromInputArgs(noView);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadFlag() {

    try {
      builder.buildFromInputArgs(badFlag);
    } catch (IOException e) {
      fail("bad flag detected. unable to read parameters");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadView() {

    try {
      builder.buildFromInputArgs(badView);
    } catch (IOException e) {
      fail("bad view detected. unable to read parameters");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadSpeed() {

    try {
      builder.buildFromInputArgs(badSpeed);
    } catch (IOException e) {
      fail("bad speed detected. unable to read parameters");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMissingArgument() {

    try {
      builder.buildFromInputArgs(missingArgument);
    } catch (IOException e) {
      fail("missing argument from input");
    }
  }
}
