package cs3500.animator.controller;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.model.IModelView;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import java.io.IOException;

/**
 * A controller for our animation.
 */
public class AnimatorController implements IController {

  /**
   * Builder class for our controller.
   */
  public static final class Builder implements IControllerBuilder {

    private String inputFile;
    private String outputFile;
    private int speed;
    private String vt;

    /**
     * Default constructor for builder.
     */
    public Builder() {
      inputFile = null;
      vt = null;
      outputFile = null;
      speed = 1;
    }

    @Override
    public void setInputFile(String inputFile) {
      this.inputFile = inputFile;
    }

    @Override
    public void setOutputFile(String outputFile) {
      this.outputFile = outputFile;
    }

    @Override
    public void setSpeed(int speed) {
      if (speed < 0) {
        System.err.println("exception thrown...");
        throw new IllegalArgumentException("Cannot be negative");
      }
      this.speed = speed;
    }

    @Override
    public void setView(String type) {
      vt = type;
    }

    @Override
    public IController build() throws IOException {
      if (inputFile == null || vt == null) {
        throw new IllegalArgumentException("View type and Inputfile need to be set");
      }

      AnimationFileReader mr = new AnimationFileReader();
      AnimatorModel.Builder mb = new AnimatorModel.Builder();

      IAnimatorModel myModel = mr.readFile(inputFile, mb);

      IView myView = ViewFactory.getView(vt, outputFile);

      return new AnimatorController(myModel, myView, speed);

    }

    @Override
    public IController buildFromInputArgs(String[] args) throws IOException {
      String id;
      String value;

      // Extract arguments values from the arg array
      if(args.length %2 != 0) {
        throw new IllegalArgumentException("Invalid number of arguments");
      }

      for (int i = 0; i < args.length; i += 2) {
        id = args[i];
        value = args[i + 1];

        if (id.toLowerCase().equals("-if")) {
          if(value.charAt(0) == '-') {
            throw new IllegalArgumentException("bad file input");
          }
          setInputFile(value);
        } else if (id.toLowerCase().equals("-iv")) {
          if(value.charAt(0) == '-') {
            throw new IllegalArgumentException("bad view input");
          }
          setView(value);
        } else if (id.toLowerCase().equals("-speed")) {
          try {
            setSpeed(Integer.valueOf(value));
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bad Speed");
          }
        } else if (id.toLowerCase().equals("-o")) {
          if(value.charAt(0) == '-') {
            throw new IllegalArgumentException("bad out input");
          }
          setOutputFile(value);
        } else {
          throw new IllegalArgumentException("Bad flag");
        }
      }

      return build();
    }
  }

  private IAnimatorModel myModel;
  private IView myView;
  private int speed;

  /**
   * Constructs a controller.
   *
   * @param inputModel the model we're using.
   * @param inputView the view we're using.
   * @param inputSpeed the speed we're using.
   */
  public AnimatorController(IAnimatorModel inputModel, IView inputView, int inputSpeed) {
    myModel = inputModel;
    myView = inputView;
    speed = inputSpeed;
  }

  @Override
  public void playAnimation() {
    IModelView myMV = myModel;
    try {
      myView.show(myModel, speed);
    } catch (IOException e) {
      System.err.println("IOException occured...");
      System.err.println(e.toString());
    }
  }
}
