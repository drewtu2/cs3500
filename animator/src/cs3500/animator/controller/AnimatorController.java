package cs3500.animator.controller;

import cs3500.animator.view.viewType;
import java.io.FileNotFoundException;

public class AnimatorController {
  public static final class Builder implements IControllerBuilder {
    private Readable inputFile;
    private Appendable outputFile;
    private int speed;
    private viewType vt;

    private Builder() {
      inputFile = null;
      vt = null;
      outputFile = System.out;
      speed = 1;
    }

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
    public void setView(String type) {

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
