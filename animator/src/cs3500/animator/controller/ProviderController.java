package cs3500.animator.controller;

import java.io.IOException;

import cs3500.animator.provider.view.HybridView;
import cs3500.animator.provider.view.SVGView;
import cs3500.animator.provider.view.TextualView;
import cs3500.animator.provider.view.VisualView;

public class ProviderController extends AnimatorController {

  private cs3500.animator.provider.view.IView providerView;

  public ProviderController(cs3500.animator.provider.view.IView inputView) {
    super();
    providerView = inputView;
  }

  @Override
  public void playAnimation() {

    try {
      if(providerView instanceof HybridView || providerView instanceof VisualView) {
        providerView.animate();
      }
      else if(providerView instanceof TextualView || providerView instanceof SVGView) {
        providerView.writeAnimatorDescription();
      }
    } catch (IOException e) {
      System.err.println("IOException occured...");
      System.err.println(e.toString());
    }
  }
}
