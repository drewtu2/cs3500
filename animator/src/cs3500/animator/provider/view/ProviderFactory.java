package cs3500.animator.provider.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.provider.adapter.ModelAdapter;
import cs3500.animator.provider.object.animation.IAnimation;

import static util.MyUtil.checkNull;

/**
 * Creates the provider's view.
 */
public class ProviderFactory {


  /**
   * Factory method that creates a specific instance of a view dependent on given input.
   *
   * @param model ianimator model with shape and animation info
   * @param view String representing the type of view required
   * @param outputFile where to put the output
   * @param speed int how fast the rate of animation is
   * @return IView instance
   */
  public static cs3500.animator.provider.view.IView getView(IAnimatorModel model, String view,
                                                            String outputFile,
                                                            int speed) throws
          IOException {
    Appendable myAppendable;
    ModelAdapter mAdapt = new ModelAdapter(model);

    // Handle null case
    checkNull(view);

    // Handle System.out case
    if (outputFile == null || outputFile.equals("out")) {
      myAppendable = System.out;
    } else { // Handle file case
      myAppendable = new FileWriter(outputFile, true); //true tells to append data.
    }

    switch (view.toLowerCase()) {
      case "provider":
        return new HybridView(mAdapt.getAnimations(),mAdapt.getShapes(), mAdapt.getShapeOrder(),
                ((double)speed));
      case "providertext":
        return new TextualView(mAdapt.getAnimations(), ((double)speed), myAppendable);
      case "providersvg":
        return new SVGView(mAdapt.getAnimations(), ((double)speed), myAppendable);
      case "providervisual":
        return new VisualView(mAdapt.getAnimations(),mAdapt.getShapes(), mAdapt.getShapeOrder(),
                ((double)speed));
      default:
        throw new IllegalArgumentException("invalid view type");
    }
  }


  /**
   * Factory method that creates a specific instance of a text or svg view dependent on given input.
   *
   * @param animationList list of ianimation from the model
   * @param view String representing the type of view required
   * @param outputFile where to put the output
   * @param speed int how fast the rate of animation is
   * @return IView instance
   */
  public static cs3500.animator.provider.view.IView getView(List<IAnimation> animationList, String
          view, String outputFile, int speed) throws IOException {
    Appendable myAppendable;

    // Handle null case
    checkNull(view);

    // Handle System.out case
    if (outputFile == null || outputFile.equals("out")) {
      myAppendable = System.out;
    } else { // Handle file case
      myAppendable = new FileWriter(outputFile, true); //true tells to append data.
    }

    switch (view.toLowerCase()) {
      case "providertext":
        return new TextualView(animationList, ((double)speed), myAppendable);
      case "providersvg":
        return new SVGView(animationList, ((double)speed), myAppendable);
      default:
        throw new IllegalArgumentException("invalid view type");
    }
  }
}
