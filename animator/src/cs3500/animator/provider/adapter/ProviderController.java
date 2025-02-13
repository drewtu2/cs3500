package cs3500.animator.provider.adapter;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.provider.view.HybridView;
import cs3500.animator.provider.view.SVGView;
import cs3500.animator.provider.view.TextualView;
import cs3500.animator.provider.view.VisualView;
import java.io.IOException;

public class ProviderController extends AnimatorController {

  private cs3500.animator.provider.view.IView providerView;

  /**
   * Constructs an controller from an input view.
   *
   * @param inputView the input view to use.
   */
  public ProviderController(cs3500.animator.provider.view.IView inputView) {
    super();
    providerView = inputView;
  }

  /**
   * Constructs a provider controller from an input view and appendable.
   *
   * @param inputView the view to use.
   * @param out the appendable to use.
   */
  public ProviderController(cs3500.animator.provider.view.IView inputView, Appendable out) {
    super();
    providerView = inputView;
    outputAppendable = out;
  }

  @Override
  public void playAnimation() {

    try {
      if (providerView instanceof HybridView || providerView instanceof VisualView) {
        providerView.animate();
      } else if (providerView instanceof TextualView || providerView instanceof SVGView) {
        providerView.writeAnimatorDescription();
      }
    } catch (IOException e) {
      System.err.println("IOException occured...");
      System.err.println(e.toString());
    }
  }
}
