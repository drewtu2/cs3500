package cs3500.animator.controller;

import static util.IUtil.checkNull;

import cs3500.animator.controller.listeners.ButtonListener;
import cs3500.animator.controller.listeners.SliderChangeListener;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.model.IModelView;
import cs3500.animator.provider.adapter.ControllerFactory;
import cs3500.animator.view.IView;
import cs3500.animator.view.interactive.IInteractive;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.ChangeListener;
import util.AnimationFileReader;

/**
 * A controller for our animation.
 */
public class AnimatorController implements IController {

  public AnimatorController() {
    //does nothing
  }

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
      outputFile = "out";
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

      return ControllerFactory.getController(myModel, vt, outputFile, speed);
    }

    @Override
    public IController buildFromInputArgs(String[] args) throws IOException {
      String id;
      String value;

      // Extract arguments values from the arg array
      if (args.length % 2 != 0) {
        throw new IllegalArgumentException("Invalid number of arguments");
      }

      for (int i = 0; i < args.length; i += 2) {
        id = args[i];
        value = args[i + 1];

        if (id.toLowerCase().equals("-if")) {
          if (value.charAt(0) == '-') {
            throw new IllegalArgumentException("bad file input");
          }
          setInputFile(value);
        } else if (id.toLowerCase().equals("-iv")) {
          if (value.charAt(0) == '-') {
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
          if (value.charAt(0) == '-') {
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

  protected IAnimatorModel myModel;
  private IView myView = null;
  protected Appendable outputAppendable;
  protected int speed;

  /**
   * Constructs a controller.
   *
   * @param inputModel the model we're using.
   * @param inputView the view we're using.
   * @param inputSpeed the speed we're using.
   */
  public AnimatorController(IAnimatorModel inputModel, cs3500.animator.view.IView inputView,
      int inputSpeed) {
    myModel = inputModel;
    myView = inputView;
    speed = inputSpeed;

    // Configure the listeners if we're using the interactive view
    if ((myView instanceof IInteractive)) {
      ActionListener buttons = configureButtonListener();
      ChangeListener speedListener = configureSpeedListener();
      ((IInteractive) myView).setListeners(buttons, speedListener);
      ((IInteractive) myView).setSpeed(speed);
    }
  }

  /**
   * Constructs a controller.
   *
   * @param inputModel the model we're using.
   * @param inputView the view we're using.
   * @param inputSpeed the speed we're using.
   */
  public AnimatorController(IAnimatorModel inputModel, cs3500.animator.view.IView inputView,
      int inputSpeed, Appendable output) {
    myModel = inputModel;
    myView = inputView;
    speed = inputSpeed;
    outputAppendable = output;

    // Configure the listeners if we're using the interactive view
    if ((myView instanceof IInteractive)) {
      ActionListener buttons = configureButtonListener();
      ChangeListener speedListener = configureSpeedListener();
      ((IInteractive) myView).setListeners(buttons, speedListener);
      ((IInteractive) myView).setSpeed(speed);
    }
  }


  @Override
  public void playAnimation() {
    IModelView myMV = myModel;

    try {
      myView.show(myMV, speed);

    } catch (IOException e) {
      System.err.println("IOException occured...");
      System.err.println(e.toString());
    }
  }

  @Override
  public void close() {
    if (outputAppendable instanceof FileWriter) {
      try {
        ((FileWriter) outputAppendable).flush();
        ((FileWriter) outputAppendable).close();
      } catch (IOException e) {
        System.err.println(e);
      }
    }
  }

  /**
   * Set up the button listener.
   *
   * @return an action listener for the buttons.
   */
  private ActionListener configureButtonListener() {
    checkNull(myModel);
    checkNull(myView);

    if (!(myView instanceof IInteractive)) {
      throw new IllegalArgumentException("Not interactive view...");
    }

    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    IInteractive interactiveView = (IInteractive) myView;

    buttonClickedMap.put("start", () -> {
      interactiveView.start();
    });
    buttonClickedMap.put("pause", () -> {
      interactiveView.pause();
    });
    buttonClickedMap.put("resume", () -> {
      interactiveView.resume();
    });
    buttonClickedMap.put("reset", () -> {
      interactiveView.reset();
    });
    buttonClickedMap.put("export", () -> {
      interactiveView.export();
    });
    buttonClickedMap.put("dont loop", () -> {
      interactiveView.setLoop(false);
    });
    buttonClickedMap.put("loop", () -> {
      interactiveView.setLoop(true);
    });

    for (String key : myModel.getFullState().keySet()) {
      buttonClickedMap.put(key, () -> interactiveView.toggleShape(key));
    }

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    return buttonListener;
  }

  /**
   * Configure listener for the speed slider.
   *
   * @return the change listener
   */
  private ChangeListener configureSpeedListener() {
    if (!(myView instanceof IInteractive)) {
      throw new IllegalArgumentException("Not interactive view...");
    }
    ChangeListener listener = new SliderChangeListener((IInteractive) myView);
    return listener;
  }

}
