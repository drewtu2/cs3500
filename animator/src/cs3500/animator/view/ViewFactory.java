package cs3500.animator.view;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Factory class that holds methods to create views on demand.
 */
public class ViewFactory {

  /**
   * Factory method that creates a specific instance of a view dependent on given input.
   * @param name String representing the type of view required
   * @return IView instance
   * @throws IOException
   */
  public static IView getView(String name, String outputFile) throws IOException {
    Appendable myAppendable;

    // Handle null case
    if (outputFile == null) {
      throw new NullPointerException();
    }
    // Handle not out case
    if (outputFile != "out") {
      myAppendable = new FileWriter(outputFile, true); //true tells to append data.
    } else { // Handle out case
      myAppendable = System.out;
    }

    switch(name.toLowerCase()) {
      case "visual":
        return new VisualView();
      case "text":
        return new TextView(myAppendable);
      case "svg":
        return new SVGView(myAppendable);
        default:
          throw new IllegalArgumentException("invalid view type");
    }
  }
}
