package cs3500.animator.controller;

import static util.MyUtil.checkNull;

import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.provider.view.ProviderFactory;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import java.io.FileWriter;
import java.io.IOException;

public class ControllerFactory {

  /**
   * Creates a controller based on a set of given inputs.
   *
   * @param model the model to use
   * @param view a string representing the view to use
   * @param outputFile the outputFile to use
   * @param speed the speed to play at
   * @return A controller.
   * @throws IOException passed up from an errors in an appendable object
   */
  public static IController getController(IAnimatorModel model, String view,
      String outputFile, int speed) throws IOException {
    IView myView;
    cs3500.animator.provider.view.IView providerView;
    Appendable myAppendable;

    // Handle System.out case
    if (outputFile == null || outputFile.equals("out")) {
      myAppendable = System.out;
    } else { // Handle file case
      myAppendable = new FileWriter(outputFile, true); //true tells to append data.
    }
    // Handle null case
    checkNull(view);

    if (view.contains("provider")) {
      providerView = ProviderFactory.getView(model, view, myAppendable, speed);
      return new ProviderController(providerView, myAppendable);
    } else {
      myView = ViewFactory.getView(view, myAppendable);
      return new AnimatorController(model, myView, speed, myAppendable);
    }
  }
}
