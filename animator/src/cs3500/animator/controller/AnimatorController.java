package cs3500.animator.controller;

import java.io.FileNotFoundException;

public class AnimatorController {
  public static final class Builder implements IControllerBuilder {

    @Override
    public void setInputFile(String inputFile) throws FileNotFoundException {

    }

    @Override
    public void setOutputFile(String outputFile) {

    }

    @Override
    public void setSpeed(int speed) {

    }

    @Override
    public IController build() {
      return null;
    }

    @Override
    public IController buildFromInputArgs(String[] args) {
      return null;
    }
  }

}
