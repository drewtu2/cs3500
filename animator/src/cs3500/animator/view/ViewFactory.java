package cs3500.animator.view;

import static util.IUtil.checkNull;

import cs3500.animator.view.interactive.InteractiveView;
import cs3500.animator.view.visual.VisualView;
import java.io.IOException;

/**
 * Factory class that holds methods to create views on demand.
 */
public class ViewFactory {

  /**
   * Factory method that creates a specific instance of a view dependent on given input.
   *
   * @param name String representing the type of view required
   * @return IView instance
   */
  public static IView getView(String name) throws IOException {
    Appendable myAppendable;

    // Handle null case
    checkNull(name);

    // Handle not out case
    myAppendable = System.out;

    switch (name.toLowerCase()) {
      case "visual":
        return new VisualView();
      case "text":
        return new TextView(myAppendable);
      case "svg":
        return new SVGView(myAppendable, false);
      default:
        throw new IllegalArgumentException("invalid view type");
    }
  }

  /**
   * Factory method that creates a specific instance of a view dependent on given input.
   *
   * @param name String representing the type of view required
   * @return IView instance
   */
  public static IView getView(String name, Appendable myAppendable) throws IOException {

    // Handle null case
    checkNull(name);

    switch (name.toLowerCase()) {
      case "interactive":
        return new InteractiveView();
      case "visual":
        return new VisualView();
      case "text":
        return new TextView(myAppendable);
      case "svg":
        return new SVGView(myAppendable, false);
      default:
        throw new IllegalArgumentException("invalid view type");
    }
  }

  /**
   * Factory method that creates a specific instance of a view dependent on given input.
   *
   * @param name String representing the type of view required
   * @param loopable is the animation looping or not
   * @return IView instance
   */
  public static IView getView(String name, Appendable myAppendable, boolean loopable) throws
      IOException {

    // Handle null case
    checkNull(name);
    checkNull(loopable);

    switch (name.toLowerCase()) {
      case "interactive":
        return new InteractiveView();
      case "visual":
        return new VisualView();
      case "text":
        return new TextView(myAppendable);
      case "svg":
        return new SVGView(myAppendable, loopable);
      default:
        throw new IllegalArgumentException("invalid view type");
    }
  }
}
