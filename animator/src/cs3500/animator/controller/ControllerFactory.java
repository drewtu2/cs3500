package cs3500.animator.controller;

import java.io.IOException;

import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.provider.view.ProviderFactory;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;

import static util.MyUtil.checkNull;

public class ControllerFactory {
  public static IController getController(IAnimatorModel model, String view, String outputFile, int
          speed) throws IOException {
    IView myView;
    cs3500.animator.provider.view.IView providerView;

    // Handle null case
    checkNull(view);

    if(view.contains("provider")) {
      providerView = ProviderFactory.getView(model, view, outputFile, speed);
      return new ProviderController(providerView);
    }
    else {
      myView = ViewFactory.getView(view, outputFile);
      return new AnimatorController(model, myView, speed);
    }
  }
}
