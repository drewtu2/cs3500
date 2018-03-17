package cs3500.animator.controller;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.model.IModelView;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AnimatorController implements IController{


  public static final class Builder implements IControllerBuilder {
    private String inputFile;
    private String outputFile;
    private int speed;
    private String vt;

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
      this.speed = speed;
    }

    @Override
    public void setView(String type) {
      vt = type;
    }

    @Override
    public IController build() throws FileNotFoundException, IOException {

      AnimationFileReader mr = new AnimationFileReader();
      AnimatorModel.Builder mb = new AnimatorModel.Builder();

      IAnimatorModel myModel = mr.readFile(inputFile, mb);

      IView myView = ViewFactory.getView(vt, outputFile);

      return new AnimatorController(myModel, myView, speed);

    }

    @Override
    public IController buildFromInputArgs(String[] args) throws IOException, FileNotFoundException{
      String id;
      String value;


      // Extract arguments values from the arg array
      for (int i = 0; i < args.length/2; i += 2) {
        id = args[i];
        value = args[i + 1];

        if(id.toLowerCase().equals("-if")) {
          inputFile = value;
        } else if(id.toLowerCase().equals("-iv")) {
          vt = value;
        } else if(id.toLowerCase().equals("-speed")) {
          try {
            speed = Integer.valueOf(value);
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bad Speed");
          }
        } else if(id.toLowerCase().equals("-o")) {
          outputFile = value;
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
    myView.show(myModel, speed);
  }

  public void updatedModel() {
    //TODO
  }
}
