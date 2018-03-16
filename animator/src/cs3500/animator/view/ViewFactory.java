package cs3500.animator.view;

/**
 * Factory class that holds methods to create views on demand.
 */
public class ViewFactory {

  /**
   * Factory method that creates a specific instance of a view dependent on given input.
   * @param name String representing the type of view required
   * @return IView instance
   */
  public static IView getAppearAnimation(String name) {
    switch(name) {
      case "text":
        return new TextView();
      case "visual":
        return new VisualView();
      case "svg":
        return new SVGView();
        default:
          throw new IllegalArgumentException("invalid view type");
    }
  }
}
