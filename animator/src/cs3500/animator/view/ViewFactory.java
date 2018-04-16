package cs3500.animator.view;

import static util.MyUtil.checkNull;

import cs3500.animator.view.interactive.InteractiveView;
import cs3500.animator.view.visual.VisualView;
import java.io.FileWriter;
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
   * @param outputFile where to put the output
   * @return IView instance
   */
  public static IView getView(String name, String outputFile) throws IOException {
    Appendable myAppendable;

    // Handle null case
    checkNull(name);

    // Handle System.out case
    if (outputFile == null || outputFile.equals("out")) {
      myAppendable = System.out;
    } else { // Handle file case
      myAppendable = new FileWriter(outputFile, true); //true tells to append data.
    }

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
   * @param outputFile where to put the output
   * @param loopable is the animation looping or not
   * @return IView instance
   */
  public static IView getView(String name, String outputFile, boolean loopable) throws IOException {
    Appendable myAppendable;

    // Handle null case
    checkNull(name);
    checkNull(loopable);

    // Handle System.out case
    if (outputFile == null || outputFile.equals("out")) {
      myAppendable = System.out;
    } else { // Handle file case
      myAppendable = new FileWriter(outputFile, true); //true tells to append data.
    }

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
