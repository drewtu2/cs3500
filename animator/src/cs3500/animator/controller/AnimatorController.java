package cs3500.animator.controller;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AnimatorController {
  public static final class Builder implements IControllerBuilder {
    private String inputFile;
    private String outputFile;
    private int speed;
    private String vt;

    private Builder() {
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

}
